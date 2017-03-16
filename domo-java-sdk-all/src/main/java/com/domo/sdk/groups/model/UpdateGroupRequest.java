package com.domo.sdk.groups.model;


import com.google.gson.annotations.SerializedName;

public class UpdateGroupRequest {

    private String name;
    private boolean active = true;

    @SerializedName("default")
    private boolean isDefault;
    private long memberCount;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(long memberCount) {
        this.memberCount = memberCount;
    }
}
