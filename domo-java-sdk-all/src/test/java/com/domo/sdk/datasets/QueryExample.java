package com.domo.sdk.datasets;

import com.domo.sdk.ExampleBase;
import com.domo.sdk.datasets.model.*;
import org.junit.Test;

import java.io.IOException;

public class QueryExample extends ExampleBase {
    @Test
    public void dataSetClient_smokeTest() throws IOException {
        DataSetClient dsClient = client.dataSetClient();

        QueryResultSet ds = query(dsClient);
        System.out.println("Queried:"+ds);

    }

    public static QueryResultSet query(DataSetClient dsClient){
        //Create DS
        DataSet ds = CreateExample.create(dsClient);

        IceBoxQueryRequest queryRequest = new IceBoxQueryRequest("select * from \\\""+ds.getId()+"\\\"");

        return dsClient.query(ds.getId(), queryRequest);
    }
}
