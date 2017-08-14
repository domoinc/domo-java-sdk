package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.DataSetListResult;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListExample extends ExampleBase{


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    //List DS
    String sortBy = "name";
    int limit = 5;
    int offset = 0;
    List<DataSetListResult> list = dsClient.list(sortBy, limit, offset);
    System.out.println(list);

  }

}
