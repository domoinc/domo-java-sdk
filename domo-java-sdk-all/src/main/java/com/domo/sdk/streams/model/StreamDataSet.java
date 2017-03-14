package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.DataSet;

/**
 * Created by bobbyswingler on 3/10/17.
 */
public class StreamDataSet {

    private String createdAt;
    private DataSet dataSet;
    private long id;
    private String modifiedAt;
    private String updateMethod;

    public StreamDataSet(){
        this.id = 0;
        this.dataSet = new DataSet();
        this.createdAt = "";
        this.modifiedAt = "";
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public DataSet getDataset() {
        return dataSet;
    }

    public void setDataset( DataSet dataset ) {
        this.dataSet = dataset;
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

    @Override
    public boolean equals( Object o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreamDataSet that = (StreamDataSet) o;

        if (getId() != that.getId()) {
            return false;
        }
        if (getDataset() != null ? !getDataset().equals(that.getDataset()) : that.getDataset() != null) {
            return false;
        }
        if (getUpdateMethod() != null ? !getUpdateMethod().equals(that.getUpdateMethod()) : that.getUpdateMethod() != null) {
            return false;
        }
        if (getCreatedAt() != null ? !getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() != null) {
            return false;
        }
        return getModifiedAt() != null ? getModifiedAt().equals(that.getModifiedAt()) : that.getModifiedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) ( getId() ^ ( getId() >>> 32 ) );
        result = 31 * result + ( getDataset() != null ? getDataset().hashCode() : 0 );
        result = 31 * result + ( getUpdateMethod() != null ? getUpdateMethod().hashCode() : 0 );
        result = 31 * result + ( getCreatedAt() != null ? getCreatedAt().hashCode() : 0 );
        result = 31 * result + ( getModifiedAt() != null ? getModifiedAt().hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "StreamDataSet{" +
                "id='" + id + '\'' +
                ", dataset=" + dataSet.toString() +
                ", updateMethod='" + updateMethod + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                '}';
    }
}
