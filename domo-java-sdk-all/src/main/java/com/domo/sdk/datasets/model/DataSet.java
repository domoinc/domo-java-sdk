package com.domo.sdk.datasets.model;

public class DataSet {
    private String id;
    private String name;
    private String description;
    private long rows;
    private long columns;
    private Schema schema;
    private Owner owner;
    private String createdAt;
    private String updatedAt;

    public DataSet() {
        this.id = "";
        this.name = "";
        this.description = "";
        this.rows = 0;
        this.columns = 0;
        this.schema = new Schema();
        this.owner = new Owner();
        this.createdAt = "";
        this.updatedAt = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getColumns() {
        return columns;
    }

    public void setColumns(long columns) {
        this.columns = columns;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
