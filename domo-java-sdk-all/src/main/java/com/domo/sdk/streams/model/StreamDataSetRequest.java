package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.CreateDataSetRequest;

/**
 * Created by bobbyswingler on 3/8/17.
 */
public class StreamDataSetRequest {

    private CreateDataSetRequest dataSet;
    private String updateMethod;

    public StreamDataSetRequest(){
        this.dataSet = new CreateDataSetRequest();
        this.updateMethod = StreamUploadMethod.APPEND;
    }

    public CreateDataSetRequest getDataset() {
        return dataSet;
    }

    public void setDataset( CreateDataSetRequest dataset ) {
        this.dataSet = dataset;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod( String updateMethod ) {
        this.updateMethod = updateMethod;
    }
}
