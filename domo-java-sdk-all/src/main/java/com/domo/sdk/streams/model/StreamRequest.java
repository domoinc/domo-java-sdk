package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.CreateDataSetRequest;

/**
 * Created by bobbyswingler on 3/8/17.
 */
public class StreamRequest {

    private CreateDataSetRequest dataSet;
    private String updateMethod;
    private String keyColumnName;

    public StreamRequest(){
        this.dataSet = new CreateDataSetRequest();
        this.updateMethod = UpdateMethod.APPEND;
        this.keyColumnName = "";
    }

    public CreateDataSetRequest getDataSet() {
        return dataSet;
    }

    public void setDataSet( CreateDataSetRequest dataSet ) {
        this.dataSet = dataSet;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod( String updateMethod ) {
        this.updateMethod = updateMethod;
    }

    public String getKeyColumnName() {
        return keyColumnName;
    }

    public void setKeyColumnName( String keyColumnName ) {
        this.keyColumnName = keyColumnName;
    }
}
