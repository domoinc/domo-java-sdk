package com.domo.sdk;

import com.domo.sdk.datasets.DataSetClient;
import com.domo.sdk.datasets.model.Column;
import com.domo.sdk.datasets.model.CreateDataSetRequest;
import com.domo.sdk.datasets.model.DataSet;
import com.domo.sdk.datasets.model.Schema;
import com.domo.sdk.request.Config;
import com.domo.sdk.users.UserClient;
import com.domo.sdk.users.model.User;
import okhttp3.logging.HttpLoggingInterceptor;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.io.IOException;

import static com.domo.sdk.datasets.model.ColumnType.STRING;
import static com.domo.sdk.request.Scope.DATA;
import static com.domo.sdk.request.Scope.USER;

public class HandsOnWorkshop {

    @Test
    public void handsOnWorkShop() throws IOException {
        //Create client (step 1 & 2)
        DomoClient client = DomoClient.create(Config.with()
                .scope(DATA)
                .clientId("dd2ccdab-4120-4885-be37-d24088de03c2")
                .clientSecret("72befda1108f59c94e0c91bdcc53f4018e45f8b47e8ef1fa9aa769999a46483b")
                .scope(USER, DATA)
                .httpLoggingLevel(HttpLoggingInterceptor.Level.BODY)
                .build());

        //Step 3
        UserClient userClient = client.userClient();
        User clint = userClient.get(669096686);
        System.out.println(clint);

        DataSetClient dsClient = client.dataSetClient();

        //Step 4 - Create DS
        CreateDataSetRequest createRequest = new CreateDataSetRequest();
        createRequest.setName("Sample DataSet");
        createRequest.setDescription("Just some data");
        createRequest.setSchema(new Schema(Lists.newArrayList(new Column(STRING, "name"))));

        DataSet ds = dsClient.create(createRequest);
        System.out.println("Created:"+ds);

        //Step 5 - Get DS
        DataSet ds2 = dsClient.get(ds.getId());
        System.out.println("Get:"+ds2);

        //Step 6 - Update DS
        ds2.getSchema().setColumns(Lists.newArrayList(
                new Column(STRING, "1st Letters"),
                new Column(STRING, "2nd Letters"),
                new Column(STRING, "3rd Letters")));
        dsClient.update(ds2);

        //Step 7 - Import data
        String input = "\"a\",\"b\",\"c\"\n\"d\",\"e\",\"f\"\n\"g\",\"h\",\"i\"\n\"j\",\"k\",\"l\"\n\"m\",\"n\",\"o\"\n\"p\",\"q\",\"r\"";
        dsClient.importData(ds.getId(),input);

    }
}
