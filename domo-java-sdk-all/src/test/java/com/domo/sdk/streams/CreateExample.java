package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.Schema;
import com.domo.sdk.streams.model.Stream;
import com.domo.sdk.streams.model.StreamRequest;
import com.domo.sdk.streams.model.UpdateMethod;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;

import static com.domo.sdk.datasets.model.ColumnType.STRING;

public class CreateExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {
    StreamClient sdsClient = client.streamClient();

    createStream(sdsClient);
  }

  public static Stream createStream(StreamClient sdsClient) {
    //Build DataSet to populate the create stream request
    CreateDataSetRequest ds = new CreateDataSetRequest();
    ds.setName("Leonhard Euler Party");
    ds.setDescription("Mathematician Guest List");
    ds.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "Friend"), new Column(STRING, "Attending"))));

    //Create Stream
    StreamRequest sdsRequest = new StreamRequest();
    sdsRequest.setDataSet(ds);
    sdsRequest.setUpdateMethod(UpdateMethod.APPEND);
    return sdsClient.create(sdsRequest);
  }

}
