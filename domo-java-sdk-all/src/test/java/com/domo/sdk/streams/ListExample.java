package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.streams.model.Stream;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ListExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {
    StreamClient sdsClient = client.streamClient();

    //List Streams
    int limit = 500;
    int offset = 0;
    List<Stream> listedSds = sdsClient.list(limit, offset);
    System.out.println("Listed Streams: " + listedSds);

  }

}
