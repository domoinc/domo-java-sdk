package com.domo.sdk.groups.model;


import com.google.gson.annotations.SerializedName;

public class CreateGroupRequest {

    private String name;
    @SerializedName("default")
    private boolean isDefault;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
