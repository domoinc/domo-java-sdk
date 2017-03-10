package com.domo.sdk.request;

import com.google.gson.Gson;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class OAuthInterceptor implements Interceptor {
    private final AtomicReference<String> accessToken = new AtomicReference<String>();

    //Nested client and gson avoid circular dependency with Transport
    private OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private final UrlBuilder urlBuilder;
    private final Config config;

    public OAuthInterceptor(UrlBuilder urlBuilder, Config config) {
        this.urlBuilder = urlBuilder;
        this.config = config;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //Build new request
        Request.Builder builder = request.newBuilder();

        String token = accessToken.get(); //save token of this request for future
        setAuthHeader(builder, token); //write current token to request

        request = builder.build(); //overwrite old request
        Response response = chain.proceed(request); //perform request, here original request will be executed

        if (response.code() == 401) { //if unauthorized
            synchronized (accessToken) { //perform all 401 in sync blocks, to avoid multiply token updates
                String currentToken = accessToken.get(); //get currently stored token

                if(currentToken == null || (currentToken.equals(token))) { //compare current token with token that was stored before, if it was not updated - do update

                    int code = refreshToken() / 100; //refresh token
                    if(code != 2) { //if refresh token failed for some reason
                        if(code == 4) //only if response is 400, 500 might mean that token was not updated
                            logout(); //go to login screen
                        return response; //if token refresh failed - show error to user
                    }
                }

                token = accessToken.get();
                if(token != null) { //retry requires new auth token,
                    setAuthHeader(builder, token); //set auth token to updated
                    request = builder.build();
                    return chain.proceed(request); //repeat request with new token
                }
            }
        }

        return response;
    }

    private void setAuthHeader(Request.Builder builder, String token) {
        if (token != null) //Add Auth token to each request if authorized
            builder.header("Authorization", String.format("Bearer %s", token));
    }

    private int refreshToken() {
        HttpUrl url = urlBuilder.fromPathSegments("oauth/token")
                .build();

        Request request = new Request.Builder()
                .header("Authorization", Credentials.basic(config.getClientId(), config.getSecret()))
                .header("Accept", "application/json")
                .url(url)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),"grant_type=client_credentials&scope=user data"))
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();

            OAuthResponse oauth = gson.fromJson(response.body().charStream(), OAuthResponse.class);
            accessToken.set(oauth.access_token);

            return response.code();
        } catch (IOException e) {
            return 500;
        }
    }

    private int logout() {
        System.out.println("Logout");
        //logout your user
        return 100;
    }
}
