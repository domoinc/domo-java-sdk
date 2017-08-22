package com.domo.sdk.pages;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.pages.model.Page;
import com.domo.sdk.pages.model.PageCollection;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CollectionActionsExample extends ExampleBase {

  @Test
  public void pageClient_smokeTest() throws IOException {
    PageClient pageClient = client.pageClient();

    Page page = CreateExample.create(pageClient);

    PageCollection col1 = new PageCollection();
    col1.setTitle("Collection");

    //Create collection
    PageCollection col2 = pageClient.createCollection(page.getId(), col1);

    //List collections
    List<PageCollection> pageCollectionList = pageClient.getCollections(page.getId());

    //Update collection
    col2.setTitle("New Title");
    pageClient.updateCollection(page.getId(), col2);

    //Delete collection
    pageClient.deleteCollection(page.getId(), col2.getId());
  }

}
