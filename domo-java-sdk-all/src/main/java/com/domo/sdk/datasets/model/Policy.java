package com.domo.sdk.datasets.model;

import java.util.List;

public class Policy {
    private long id;
    private String type;
    private String name;
    private List<PolicyFilter> filters;
    private List<Long> users;
    private List<Long> groups;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PolicyFilter> getFilters() {
        return filters;
    }

    public void setFilters(List<PolicyFilter> filters) {
        this.filters = filters;
    }

    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
    }

    public List<Long> getGroups() {
        return groups;
    }

    public void setGroups(List<Long> groups) {
        this.groups = groups;
    }
}
