package com.domo.sdk.datasets.model;

import java.util.Objects;

public class DataSetListResult {
    private String id;
    private String name;
    private String description;
    private long rows;
    private long columns;
    private Owner owner;
    private String dataCurrentAt;
    private String createdAt;
    private String updatedAt;
    private boolean pdpEnabled;

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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getDataCurrentAt() {
        return dataCurrentAt;
    }

    public void setDataCurrentAt(String dataCurrentAt) {
        this.dataCurrentAt = dataCurrentAt;
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

    public boolean isPdpEnabled() {
        return pdpEnabled;
    }

    public void setPdpEnabled(boolean pdpEnabled) {
        this.pdpEnabled = pdpEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataSetListResult that = (DataSetListResult) o;
        return rows == that.rows &&
                columns == that.columns &&
                pdpEnabled == that.pdpEnabled &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(owner, that.owner) &&
                Objects.equals(dataCurrentAt, that.dataCurrentAt) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, rows, columns, owner, dataCurrentAt, createdAt, updatedAt, pdpEnabled);
    }

    @Override
    public String toString() {
        return "DataSetListResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", rows=" + rows +
                ", columns=" + columns +
                ", owner=" + owner +
                ", dataCurrentAt='" + dataCurrentAt + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", pdpEnabled=" + pdpEnabled +
                '}';
    }
}
