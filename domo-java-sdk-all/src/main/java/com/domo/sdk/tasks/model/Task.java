package com.domo.sdk.tasks.model;

import java.util.*;

public class Task {

    private String id;
    private String projectId;
    private String projectListId;
    private String taskName;
    private String description;
    private String createdDate;
    private String updatedDate;
    private String dueDate;
    private Long priority;
    private Long createdBy;
    private Long ownedBy;
    private Set<Long> contributors;
    private Integer attachmentCount;
    private Set<String> tags;
    private Boolean archived;

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getProjectId() { return projectId; }

    public void setProjectId(String projectId) { this.projectId = projectId; }

    public String getProjectListId() { return projectListId; }

    public void setProjectListId(String projectListId) { this.projectListId = projectListId; }

    public String getTaskName() { return taskName; }

    public void setTaskName(String taskName) { this.taskName = taskName; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getCreatedDate() { return createdDate; }

    public void setCreatedDate(String createdDate) { this.createdDate = createdDate; }

    public String getUpdatedDate() { return updatedDate; }

    public void setUpdatedDate(String updatedDate) { this.updatedDate = updatedDate; }

    public String getDueDate() { return dueDate; }

    public void setDueDate(String dueDate) { this.dueDate = dueDate; }

    public Long getPriority() { return priority; }

    public void setPriority(Long priority) { this.priority = priority; }

    public Long getCreatedBy() { return createdBy; }

    public void setCreatedBy(Long createdBy) { this.createdBy = createdBy; }

    public Long getOwnedBy() { return ownedBy; }

    public void setOwnedBy(Long ownedBy) { this.ownedBy = ownedBy; }

    public Set<Long> getContributors() { return contributors; }

    public void setContributors(Set<Long> contributors) { this.contributors = contributors; }

    public Integer getAttachmentCount() { return attachmentCount; }

    public void setAttachmentCount(Integer attachmentCount) { this.attachmentCount = attachmentCount; }

    public Set<String> getTags() { return tags; }

    public void setTags(Set<String> tags) { this.tags = tags; }

    public Boolean getArchived() { return archived; }

    public void setArchived(Boolean archived) { this.archived = archived; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(projectId, task.projectId) &&
                Objects.equals(projectListId, task.projectListId) &&
                Objects.equals(taskName, task.taskName) &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdDate, task.createdDate) &&
                Objects.equals(updatedDate, task.updatedDate) &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(createdBy, task.createdBy) &&
                Objects.equals(ownedBy, task.ownedBy) &&
                Objects.equals(contributors, task.contributors) &&
                Objects.equals(attachmentCount, task.attachmentCount) &&
                Objects.equals(tags, task.tags) &&
                Objects.equals(archived, task.archived);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, projectId, projectListId, taskName, description, createdDate, updatedDate, dueDate,
                priority, createdBy, ownedBy, contributors, attachmentCount, tags, archived);
    }
}
