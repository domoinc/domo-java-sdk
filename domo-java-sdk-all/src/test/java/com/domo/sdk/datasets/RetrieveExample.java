package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.DataSet;
import org.junit.Test;

import java.io.IOException;

public class RetrieveExample extends ExampleBase {


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    //Create DS
    DataSet ds = CreateExample.create(dsClient);
    System.out.println("Created:" + ds);

    //Get DS
    DataSet ds2 = dsClient.get(ds.getId());
    System.out.println("Get:" + ds2);
  }

}
