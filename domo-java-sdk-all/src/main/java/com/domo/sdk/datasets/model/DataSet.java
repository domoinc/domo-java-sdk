package com.domo.sdk.datasets.model;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSet dataSet = (DataSet) o;
        return rows == dataSet.rows &&
                columns == dataSet.columns &&
                Objects.equals(id, dataSet.id) &&
                Objects.equals(name, dataSet.name) &&
                Objects.equals(description, dataSet.description) &&
                Objects.equals(schema, dataSet.schema) &&
                Objects.equals(owner, dataSet.owner) &&
                Objects.equals(createdAt, dataSet.createdAt) &&
                Objects.equals(updatedAt, dataSet.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rows, columns, schema, owner, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return "DataSet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                ", schema=" + schema +
                ", owner=" + owner +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
