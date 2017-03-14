package com.domo.sdk.datasets.model;

/**
 * Created by clintchecketts on 3/8/17.
 */
public class CreateDataSetRequest {
    private String name;
    private String description;
    private long rows;
    private Schema schema;

    public CreateDataSetRequest(){
        this.name="";
        this.description="";
        this.rows=0;
        this.schema = new Schema();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}
