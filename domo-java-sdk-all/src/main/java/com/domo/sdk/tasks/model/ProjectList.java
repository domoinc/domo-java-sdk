package com.domo.sdk.tasks.model;

import java.util.Objects;

public class ProjectList {
    public enum ListType {
        TODO,
        WORKING_ON,
        COMPLETED
    }

    private String id;
    private String name;
    private ListType type;
    private Long index;
    private String createdDate;
    private String updatedDate;

    public ProjectList() {
    }

    public ProjectList(String id, String name, ListType type, Long index, String createdDate, String updatedDate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.index = index;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
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

    public ListType getType() {
        return type;
    }

    public void setType(ListType type) {
        this.type = type;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectList that = (ProjectList) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                type == that.type &&
                Objects.equals(index, that.index) &&
                Objects.equals(createdDate, that.createdDate) &&
                Objects.equals(updatedDate, that.updatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, type, index, createdDate, updatedDate);
    }
}
