package com.domo.sdk.pages;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.pages.model.Page;
import org.junit.Test;

import java.io.IOException;

public class CreateExample extends ExampleBase {

  @Test
  public void pageClient_smokeTest() throws IOException {
    PageClient pageClient = client.pageClient();

    create(pageClient);
  }

  public static Page create(PageClient pageClient) {

    return pageClient.create(new Page.Builder("SIT").build());
  }
}
