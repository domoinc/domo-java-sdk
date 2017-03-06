package com.domo.sdk.request;

import com.domo.sdk.users.User;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Transport {
    private final OkHttpClient httpClient;
    private final Gson gson;

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    public Transport(OkHttpClient httpClient) {
        this.httpClient = httpClient;
        this.gson = new Gson();
    }

    public <T> T getJson(HttpUrl url, Type type) {
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            return gson.fromJson(response.body().charStream(), type);
        } catch (IOException e) {
            throw new RequestException("Error making request url:"+url.toString(), e);
        }
    }

    public <T> T postJson(HttpUrl url, Object body, Class<T> clazz) {
        return methodJson("POST", url, body, clazz);
    }

    public <T> T putJson(HttpUrl url, Object body, Class<T> clazz) {
        return methodJson("PUT", url, body, clazz);
    }

    public <T> T methodJson(String method, HttpUrl url, Object body, Class<T> clazz) {
        RequestBody requestBody = RequestBody.create(JSON, gson.toJson(body));

        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .method(method, requestBody)
                .post(requestBody)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            return gson.fromJson(response.body().charStream(), clazz);
        } catch (IOException e) {
            throw new RequestException("Error making request, url:"+url.toString(), e);
        }
    }

    public void deleteJson(HttpUrl url) {
        Request request = new Request.Builder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .url(url)
                .delete()
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            throw new RequestException("Error deleting, url:"+url.toString(), e);
        }
    }
}
