package com.domo.sdk.streams;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.Schema;
import com.domo.sdk.streams.model.Execution;
import com.domo.sdk.streams.model.Stream;
import com.domo.sdk.streams.model.StreamRequest;
import com.domo.sdk.streams.model.UpdateMethod;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.domo.sdk.datasets.model.ColumnType.STRING;

public class ExecutionRetrieveExample extends ExampleBase {


  @Test
  public void streamClient_smokeTest() throws IOException {

    StreamClient sdsClient = client.streamClient();

    //Build DataSet to populate the create stream request
    CreateDataSetRequest ds = new CreateDataSetRequest();
    ds.setName("Leonhard Euler Party");
    ds.setDescription("Mathematician Guest List");
    ds.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "Friend"), new Column(STRING, "Attending"))));

    //Create Stream
    StreamRequest sdsRequest = new StreamRequest();
    sdsRequest.setDataSet(ds);
    sdsRequest.setUpdateMethod(UpdateMethod.APPEND);
    Stream sds = sdsClient.create(sdsRequest);
    System.out.println("Created:" + sds);

    //Get Stream
    Stream retrievedSds = sdsClient.get(sds.getId());
    System.out.println("Retrieved:" + retrievedSds);

    //List Streams
    int limit = 500;
    int offset = 0;
    List<Stream> listedSds = sdsClient.list(limit, offset);
    System.out.println("Listed Streams: " + listedSds);

    //Search Streams
    List<Stream> searchedSds = sdsClient.search("dataSource.name:" + ds.getName());
    System.out.println("Searched Streams: " + searchedSds);

    //Update Stream to REPLACE
    sdsRequest.setUpdateMethod(UpdateMethod.REPLACE); //Only the stream metadata fields can be updated, not the dataSet metadata
    Stream updatedSds = sdsClient.update(sds.getId(), sdsRequest);
    System.out.println("Updated Stream: " + updatedSds);

    //Create Execution
    Execution execution = sdsClient.createExecution(sds.getId());
    System.out.println("Created Execution: " + execution);

    //Get Execution
    Execution retrievedExecution = sdsClient.getExecution(sds.getId(), execution.getId());
    System.out.println("Retrieved Execution: " + retrievedExecution);

    //List Executions
    List<Execution> listedExecutions = sdsClient.listExecutions(sds.getId(), 50, 0);
    System.out.println("Listed Executions: " + listedExecutions);

    //Upload Parts
    String csvInput = "\"Pythagoras\",\"FALSE\"\n\"Alan Turing\",\"TRUE\"\n\"George Boole\",\"TRUE\"";
    int partNum = 1;
    sdsClient.uploadDataPart(sds.getId(), execution.getId(), partNum, csvInput);

    //Commit Execution
    Execution committedExecution = sdsClient.commitExecution(sds.getId(), execution.getId());
    System.out.println("Committed Execution: " + committedExecution);

    //Delete Stream
    sdsClient.delete(sds.getId());
    System.out.println("Deleting Dataset: " + sds);
  }

}
