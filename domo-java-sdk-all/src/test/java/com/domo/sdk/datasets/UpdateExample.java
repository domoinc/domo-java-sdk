package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.DataSet;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;

import static com.domo.sdk.datasets.model.ColumnType.STRING;

public class UpdateExample extends ExampleBase {


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    //Create DS
    DataSet ds = CreateExample.create(dsClient);
    System.out.println("Created:" + ds);

    //Update DS
    ds.setName("Leonhard Euler Party - Update");
    ds.setDescription("Mathematician Guest List - Update");
    ds.getSchema().setColumns(Lists.newArrayList(new Column(STRING, "Friend"), new Column(STRING, "Attending"), new Column(STRING, "Favorite Shape")));
    dsClient.update(ds);
  }

}
