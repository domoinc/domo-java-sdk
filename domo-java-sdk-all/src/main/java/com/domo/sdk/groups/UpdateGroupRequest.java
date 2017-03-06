package com.domo.sdk.groups;


import com.google.gson.annotations.SerializedName;

public class UpdateGroupRequest {

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
