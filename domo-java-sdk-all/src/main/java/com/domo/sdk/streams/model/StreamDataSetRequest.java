package com.domo.sdk.streams.model;

/**
 * Created by bobbyswingler on 3/8/17.
 */
public class StreamDataSetRequest {

    private CreateStreamDataSetRequest dataSet;
    private String updateMethod;

    public StreamDataSetRequest(){
        this.dataSet = new CreateStreamDataSetRequest();
        this.updateMethod = StreamUploadMethod.APPEND;
    }

    public CreateStreamDataSetRequest getDataset() {
        return dataSet;
    }

    public void setDataset( CreateStreamDataSetRequest dataset ) {
        this.dataSet = dataset;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod( String updateMethod ) {
        this.updateMethod = updateMethod;
    }
}
