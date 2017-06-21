package com.domo.sdk;

import com.domo.sdk.datasets.DataSetClient;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.DataSetListResult;
import com.domo.sdk.datasets.model.Schema;
import com.domo.sdk.groups.GroupClient;
import com.domo.sdk.groups.model.Group;
import com.domo.sdk.groups.model.UpdateGroupRequest;
import com.domo.sdk.request.Config;
import com.domo.sdk.streams.StreamClient;
import com.domo.sdk.streams.model.Execution;
import com.domo.sdk.streams.model.Stream;
import com.domo.sdk.streams.model.StreamRequest;
import com.domo.sdk.streams.model.UpdateMethod;
import com.domo.sdk.users.UserClient;
import com.domo.sdk.users.model.CreateUserRequest;
import com.domo.sdk.users.model.User;
import okhttp3.logging.HttpLoggingInterceptor;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.domo.sdk.datasets.model.ColumnType.STRING;
import static com.domo.sdk.request.Scope.DATA;
import static com.domo.sdk.request.Scope.USER;

public class ClientTest {

    private DomoClient client;

    @Before
    public void setup() {
        Config config = Config.with()
                .clientId("MY_CLIENT_ID")
                .clientSecret("MY_CLIENT_SECRET")
                .apiHost("api.domo.com")
                .useHttps(true)
                .scope(USER, DATA)
                .httpLoggingLevel(HttpLoggingInterceptor.Level.BODY)
                .build();

        client = DomoClient.create(config);
    }

    @Test
    public void groupClient_smokeTest() {
        GroupClient gClient = client.groupClient();

        Group testGroup = new Group();
        testGroup.setName("Test Group"+System.currentTimeMillis());

        Group g2 = gClient.create(testGroup);
        System.out.println("create:"+g2);

        Group g3 = gClient.get(g2.getId());
        System.out.println("get:"+g3);

        List<Group> list = gClient.list(10,0);
        System.out.println("list:"+list);

        UpdateGroupRequest ugroup = new UpdateGroupRequest();
        ugroup.setName("DeleteMe"+System.currentTimeMillis());
        ugroup.setActive(true);
        Group g4 = gClient.update(g3.getId(), ugroup);
        System.out.println("Update:"+g4);

        User user = client.userClient().create(false,
                new CreateUserRequest("deleteme.user"+System.currentTimeMillis()+"@domo.com","Participant", "Delete Me - Smoke User"));

        System.out.println("Add user to group");
        gClient.addUserToGroup(g4.getId(), user.getId());

        System.out.println("List users in group");
        gClient.listUsersInGroup(g4.getId());

        System.out.println("Remove User");
        gClient.removeUserFromGroup(g4.getId(), user.getId());

        System.out.println("List Users after removing them");
        gClient.listUsersInGroup(g4.getId());

        gClient.delete(g3.getId());
        client.userClient().delete(user.getId());
    }

    @Test
    public void userClient_smokeTest() throws IOException {
        UserClient userClient = client.userClient();

        // Build a User request
        CreateUserRequest request = new CreateUserRequest();
        request.setName("Leonhard Euler");
        request.setEmail("leonhard.euler@domo.com");
        request.setRole("Privileged");
        boolean sendInvite = true;

        //Create a user
        User user = userClient.create(sendInvite, request);

        //Get a user
        user = userClient.get(user.getId());

        //List Users
        List<User> list = userClient.list(30, 0);
        System.out.println(list);

        //Update a User
        user.setName("Leo Euler");
        user = userClient.update(user.getId(), user);

        //Delete a User
        userClient.delete(user.getId());
    }

    @Test
    public void dataSetClient_smokeTest() throws IOException {
        DataSetClient dsClient = client.dataSetClient();

        //Create DS
        CreateDataSetRequest createRequest = new CreateDataSetRequest();
        createRequest.setName("Leonhard Euler Party");
        createRequest.setDescription("Mathematician Guest List");
        createRequest.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "Friend"))));
        DataSet ds = dsClient.create(createRequest);
        System.out.println("Created:"+ds);

        //Get DS
        DataSet ds2 = dsClient.get(ds.getId());
        System.out.println("Get:"+ds2);

        //Update DS
        ds.setName("Leonhard Euler Party - Update");
        ds.setDescription("Mathematician Guest List - Update");
        ds.getSchema().setColumns(Lists.newArrayList(new Column(STRING, "Friend"), new Column(STRING, "Attending")));
        dsClient.update(ds);

        //Import DS
        String csvInput = "\"Pythagoras\",\"FALSE\"\n\"Alan Turing\",\"TRUE\"\n\"George Boole\",\"TRUE\"";
        dsClient.importData(ds.getId(), csvInput);

        //Export DS
        InputStream stream = dsClient.exportData(ds.getId(),true);
        String data = convertStreamToString(stream);
        stream.close();
        System.out.println(data);

        //Export to file
        File f = File.createTempFile("sample-export", ".csv");
        dsClient.exportDataToFile(ds.getId(),true, f);
        System.out.println("Wrote out file:"+f.getAbsolutePath());

        //Policies

        //List DS
        String sortBy = "name";
        int limit = 5;
        int offset = 0;
        List<DataSetListResult> list = dsClient.list(sortBy, limit, offset);
        System.out.println(list);

        //Delete DS
        dsClient.delete(ds.getId());
    }

    @Test
    public void streamClient_smokeTest() throws IOException {

        StreamClient streamClient = client.streamClient();

        //Build DataSet to populate the create stream request
        CreateDataSetRequest ds = new CreateDataSetRequest();
        ds.setName("Leonhard Euler Party");
        ds.setDescription("Mathematician Guest List");
        ds.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "Friend"), new Column(STRING, "Attending"))));

        //Create Stream
        StreamRequest sdsRequest = new StreamRequest();
        sdsRequest.setDataSet(ds);
        sdsRequest.setUpdateMethod(UpdateMethod.APPEND);
        Stream sds = streamClient.create(sdsRequest);
        System.out.println("Created:" + sds);

        //Get Stream
        Stream retrievedSds = streamClient.get(sds.getId());
        System.out.println("Retrieved:" + retrievedSds);

        //List Streams
        int limit = 500;
        int offset = 0;
        List<Stream> listedSds = streamClient.list(limit, offset);
        System.out.println("Listed Streams: " + listedSds);

        //Search Streams
        List<Stream> searchedSds = streamClient.search("dataSource.name:" + ds.getName());
        System.out.println("Searched Streams: " + searchedSds);

        //Update Stream to REPLACE
        sdsRequest.setUpdateMethod(UpdateMethod.REPLACE); //Only the stream metadata fields can be updated, not the dataSet metadata
        Stream updatedSds = streamClient.update(sds.getId(), sdsRequest);
        System.out.println("Updated Stream: " + updatedSds);

        //Create Execution
        Execution execution = streamClient.createExecution(sds.getId());
        System.out.println("Created Execution: " + execution);

        //Get Execution
        Execution retrievedExecution = streamClient.getExecution(sds.getId(), execution.getId());
        System.out.println("Retrieved Execution: " + retrievedExecution);

        //List Executions
        List<Execution> listedExecutions = streamClient.listExecutions(sds.getId(), 50, 0);
        System.out.println("Listed Executions: " + listedExecutions);

        //Upload Parts
        String csvInput = "\"Pythagoras\",\"FALSE\"\n\"Alan Turing\",\"TRUE\"\n\"George Boole\",\"TRUE\"";
        int partNum = 1;
        streamClient.uploadDataPart(sds.getId(), execution.getId(), partNum, csvInput);

        //Commit Execution
        Execution committedExecution = streamClient.commitExecution(sds.getId(), execution.getId());
        System.out.println("Committed Execution: " + committedExecution);

        //Delete Stream
        streamClient.delete(sds.getId());
        System.out.println("Deleting Dataset: " + sds);
    }

    private static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
