package com.domo.sdk.pages;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.pages.model.Page;
import org.junit.Test;

import java.io.IOException;

public class DeleteExample extends ExampleBase {

  @Test
  public void pageClient_smokeTest() throws IOException {
    PageClient pageClient = client.pageClient();

    Page page = CreateExample.create(pageClient);

    pageClient.delete(page.getId());
  }

}
