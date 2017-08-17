package com.domo.sdk.request;

import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class ConfigTest {

  @Test
  public void apiHost_shouldRemoveScheme() {
    Config c = newConfig("hostwithhttp.com:1234");
    assertThat(c.getApiHost()).isEqualTo("hostwithhttp.com:1234");

    assertThat( newConfig("http://example.com").getApiHost()).isEqualTo("example.com");
    assertThat( newConfig("https://other.com:1234").getApiHost()).isEqualTo("other.com:1234");

  }

  private Config newConfig(String host) {
    return Config.with().clientId("a").clientSecret("b").apiHost(host).scope(Scope.values()).build();
  }

}
