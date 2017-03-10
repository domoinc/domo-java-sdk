package com.domo.sdk;

import com.domo.sdk.datasets.DataSetClient;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.ColumnType;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.DataSetListResult;
import com.domo.sdk.datasets.model.Schema;
import com.domo.sdk.request.Config;
import com.domo.sdk.users.UserClient;
import com.domo.sdk.users.model.CreateUserRequest;
import com.domo.sdk.users.model.User;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

import static com.domo.sdk.datasets.model.ColumnType.STRING;
import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest {

    private Client client;

    @Before
    public void setup() {
        Config conf = new Config("8cc8ee6f-4b3c-4d60-86fe-0a0db0e7ba16",
                "4fb05bb0e2416f4bbbffbd6033876e84bed6b2ad79db9d617f58b9f79f305a65", "localhost:19999", false);

        client = Client.create(conf);
    }


    @Test
    public void userClient_smokeTest() throws IOException {
        UserClient userClient = client.userClient();

        User user = userClient.create(false, new CreateUserRequest("api.smoke.test@domo.com", "Admin", "API Smoke Test"));
        System.out.println("Created user:"+user);

        User user2 = userClient.get(user.getId());
        System.out.println("Get user:"+user2);

        user2.setName("Important Title");
        User user3 = userClient.update(user.getId(), user2);
        System.out.println("Updated user:"+user3);

        //Patch user

        userClient.delete(user.getId());

        List<User> list = userClient.list(30, 0);
        System.out.println(list);
    }


    @Test
    public void dataSetClient_smokeTest() throws IOException {
        DataSetClient dsClient = client.dataSetClient();

        //Create DS
        CreateDataSetRequest createRequest = new CreateDataSetRequest();
        createRequest.setName("Smoke Test DataSet");
        createRequest.setDescription("FTW!");
        createRequest.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "name"))));

        DataSet ds = dsClient.create(createRequest);
        System.out.println("Created:"+ds);

        //Get DS
        DataSet ds2 = dsClient.get(ds.getId());
        System.out.println("Get:"+ds2);

        //Update DS
        ds.setName("Smoke Test DataSet Update");
        ds.setDescription("FTW! FTW! Updated");
        ds.getSchema().setColumns(Lists.newArrayList(new Column(STRING, "1st Letters"),
                new Column(STRING, "2nd Letters"),
                new Column(STRING, "3rd Letters")));
        dsClient.update(ds);

        //Import DS
        String input = "\"a\",\"b\",\"c\"\\n\"d\",\"e\",\"f\"\\n\"g\",\"h\",\"i\"\\n\"j\",\"k\",\"l\"\\n\"m\",\"n\",\"o\"\\n\"p\",\"q\",\"r\"";
        dsClient.importData(ds.getId(),new ByteArrayInputStream(input.getBytes(Charset.forName("UTF-8"))));

        //Export DS
        InputStream stream = dsClient.exportData(ds.getId(),true);
        String data = convertStreamToString(stream);
        stream.close();
        System.out.println(data);

        //Export to file
        File f = File.createTempFile("sample-export", ".csv");
        dsClient.exportData(ds.getId(),true, f);

        //Policies

        //List DS
        List<DataSetListResult> list = dsClient.list(5,0);
        System.out.println(list);

        //Delete DS
        dsClient.delete(ds.getId());
    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
