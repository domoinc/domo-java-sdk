package com.domo.sdk.tasks.model;

import com.google.gson.annotations.SerializedName;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

public class Project {
    private String id;
    private String name;
    private String description;
    @SerializedName("public")
    private Boolean isPublic;
    private Collection<Long> members;
    private Long createdBy;
    private String dueDate;
    private String createdDate;
    private String updatedDate;

    public Project() {
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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Collection<Long> getMembers() {
        return members;
    }

    public void setMembers(Collection<Long> members) {
        this.members = members;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
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
        Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name) &&
                Objects.equals(description, project.description) &&
                Objects.equals(isPublic, project.isPublic) &&
                Objects.equals(members, project.members) &&
                Objects.equals(createdBy, project.createdBy) &&
                Objects.equals(dueDate, project.dueDate) &&
                Objects.equals(createdDate, project.createdDate) &&
                Objects.equals(updatedDate, project.updatedDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, description, isPublic, members, createdBy, dueDate, createdDate, updatedDate);
    }
}
