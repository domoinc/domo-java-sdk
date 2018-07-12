package com.domo.sdk;

import com.domo.sdk.request.Config;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Before;

import static com.domo.sdk.request.Scope.DATA;
import static com.domo.sdk.request.Scope.USER;
import static com.domo.sdk.request.Scope.WORKFLOW;

public class ExampleBase {

  protected DomoClient client;

  @Before
  public void setup() {
    Config config = Config.with()
            .clientId("MY_CLIENT_ID")
            .clientSecret("MY_CLIENT_SECRET")
            .apiHost("api.domo.com")
            .useHttps(true)
            .scope(USER, DATA, WORKFLOW)
            .httpLoggingLevel(HttpLoggingInterceptor.Level.BODY)
            .build();

    client = DomoClient.create(config);
  }


  public static String convertStreamToString(java.io.InputStream is) {
    java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
    return s.hasNext() ? s.next() : "";
  }

}
