package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.Schema;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;

import static com.domo.sdk.datasets.model.ColumnType.STRING;

public class CreateExample extends ExampleBase{


  @Test
  public void dataSetClient_smokeTest() throws IOException {
    DataSetClient dsClient = client.dataSetClient();

    DataSet ds = create(dsClient);
    System.out.println("Created:"+ds);

  }

  public static DataSet create(DataSetClient dsClient){
    //Create DS
    CreateDataSetRequest createRequest = new CreateDataSetRequest();
    createRequest.setName("Leonhard Euler Party");
    createRequest.setDescription("Mathematician Guest List");
    createRequest.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "Friend"))));

    return dsClient.create(createRequest);
  }

}
