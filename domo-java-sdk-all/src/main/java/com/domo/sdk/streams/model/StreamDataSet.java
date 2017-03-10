package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.DataSet;

/**
 * Created by bobbyswingler on 3/10/17.
 */
public class StreamDataSet {
    private String id;
    private DataSet dataset;
    private String updateMethod;
    private String createdAt;
    private String modifiedAt;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

    public DataSet getDataset() {
        return dataset;
    }

    public void setDataset( DataSet dataset ) {
        this.dataset = dataset;
    }

    public String getUpdateMethod() {
        return updateMethod;
    }

    public void setUpdateMethod( String updateMethod ) {
        this.updateMethod = updateMethod;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt( String createdAt ) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt( String modifiedAt ) {
        this.modifiedAt = modifiedAt;
    }
}
