package com.domo.sdk.streams.model;

import com.domo.sdk.datasets.model.Schema;

/**
 * Created by bobbyswingler on 3/14/17.
 */
public class CreateStreamDataSetRequest {
    private String name;
    private String description;
    private Schema schema;

    public CreateStreamDataSetRequest(){
        this.name="";
        this.description="";
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

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }
}
