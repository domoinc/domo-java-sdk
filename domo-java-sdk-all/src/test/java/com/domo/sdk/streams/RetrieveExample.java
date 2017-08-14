package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.streams.model.Stream;
import org.junit.Test;

import java.io.IOException;

public class RetrieveExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {
    StreamClient sdsClient = client.streamClient();

    Stream sds = CreateExample.createStream(sdsClient);
    System.out.println("Created:" + sds);

    //Get Stream
    Stream retrievedSds = sdsClient.get(sds.getId());
    System.out.println("Retrieved:" + retrievedSds);

  }

}
