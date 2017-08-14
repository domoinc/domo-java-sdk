package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.streams.model.Stream;
import org.junit.Test;

import java.io.IOException;

public class DeleteExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {

    StreamClient sdsClient = client.streamClient();

    Stream sds = CreateExample.createStream(sdsClient);
    System.out.println("Created:" + sds);

    //Delete Stream
    sdsClient.delete(sds.getId());
    System.out.println("Deleting: " + sds);
  }

}
