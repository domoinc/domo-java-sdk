package com.domo.sdk.pages;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.pages.model.Page;
import com.domo.sdk.pages.model.PageSummary;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListExample extends ExampleBase {

  @Test
  public void pageClient_smokeTest() throws IOException {
    PageClient pageClient = client.pageClient();

    CreateExample.create(pageClient);

    List<PageSummary> pages = pageClient.list(10, 0);
  }

}
