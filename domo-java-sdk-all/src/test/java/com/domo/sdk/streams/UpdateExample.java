package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.streams.model.Stream;
import com.domo.sdk.streams.model.StreamRequest;
import com.domo.sdk.streams.model.UpdateMethod;
import org.junit.Test;

import java.io.IOException;

public class UpdateExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {

    StreamClient sdsClient = client.streamClient();

    Stream sds = CreateExample.createStream(sdsClient);
    System.out.println("Created:" + sds);

    //Update Stream to REPLACE
    StreamRequest sdsRequest = new StreamRequest();
    sdsRequest.setUpdateMethod(UpdateMethod.REPLACE); //Only the stream metadata fields can be updated, not the dataSet metadata
    Stream updatedSds = sdsClient.update(sds.getId(), sdsRequest);
    System.out.println("Updated Stream: " + updatedSds);

  }

}
