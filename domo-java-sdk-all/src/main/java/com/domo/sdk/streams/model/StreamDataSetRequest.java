package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by bobbyswingler on 3/8/17.
 */
public class StreamDataSetRequest {

    private static final Logger logger = LoggerFactory.getLogger(StreamDataSetRequest.class);
    private DataSet dataset;
    private String updateMethod;

    public StreamDataSetRequest(){
        this.dataset = new DataSet();
        this.updateMethod = StreamUploadMethod.APPEND;
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
}
