package com.domo.sdk.datasets.model;

public class UpdateDataSetRequest {
    private long id;
    private String name;
    private String description;
    private boolean pdpEnabled;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPdpEnabled() {
        return pdpEnabled;
    }

    public void setPdpEnabled(boolean pdpEnabled) {
        this.pdpEnabled = pdpEnabled;
    }
}
