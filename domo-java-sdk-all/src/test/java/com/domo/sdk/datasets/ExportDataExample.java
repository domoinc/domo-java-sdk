package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.DataSet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ExportDataExample extends ExampleBase{


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    String dataSetId = setupDataSet(dsClient);

    //Export DS
    InputStream stream = dsClient.exportData(dataSetId,true);
    String data = convertStreamToString(stream);
    stream.close();
    System.out.println(data);

    //Export to file
    File f = File.createTempFile("sample-export", ".csv");
    dsClient.exportDataToFile(dataSetId,true, f);
    System.out.println("Wrote out file:"+f.getAbsolutePath());

  }

  private String setupDataSet(DataSetClient dsClient) {
    //Create DS
    DataSet ds = CreateExample.create(dsClient);
    System.out.println("Created:"+ds);

    //Import DS
    String csvInput = "\"Pythagoras\",\"FALSE\"\n\"Alan Turing\",\"TRUE\"\n\"George Boole\",\"TRUE\"";
    dsClient.importData(ds.getId(), csvInput);

    return ds.getId();
  }

}
