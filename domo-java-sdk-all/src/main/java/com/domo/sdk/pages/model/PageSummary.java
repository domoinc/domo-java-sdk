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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageSummary)) return false;

        PageSummary that = (PageSummary) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;
        return children.equals(that.children);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + children.hashCode();
        return result;
    }
}
