package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.streams.model.Execution;
import com.domo.sdk.streams.model.Stream;
import org.junit.Test;

import java.io.IOException;

public class ExecutionCreateExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {

    StreamClient sdsClient = client.streamClient();

    Stream sds = CreateExample.createStream(sdsClient);
    System.out.println("Created:" + sds);

    //Create Execution
    Execution execution = sdsClient.createExecution(sds.getId());
    System.out.println("Created Execution: " + execution);
  }

}
