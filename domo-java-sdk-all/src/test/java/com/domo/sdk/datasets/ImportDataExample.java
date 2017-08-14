package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.DataSet;
import org.junit.Test;

import java.io.IOException;

public class ImportDataExample extends ExampleBase{


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    //Create DS
    DataSet ds = CreateExample.create(dsClient);

    //Import DS
    String csvInput = "\"Pythagoras\",\"FALSE\"\n\"Alan Turing\",\"TRUE\"\n\"George Boole\",\"TRUE\"";
    dsClient.importData(ds.getId(), csvInput);

  }

}
