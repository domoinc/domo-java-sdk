package com.domo.sdk.pages.model;

import java.util.List;

public class PageSummary {
    private long id;
    private String name;
    private List<PageSummary> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PageSummary> getChildren() {
        return children;
    }

    public void setChildren(List<PageSummary> children) {
        this.children = children;
    }
}
