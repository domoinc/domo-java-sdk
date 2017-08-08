package com.domo.sdk.pages.model;

import java.util.List;

public class PageVisibility {
    private List<Long> userIds;
    private List<Long> groupIds;

    public PageVisibility() {
    }

    /**
     * Create a new PageVisibility object.
     * @param userIds ids of users who should have access to the page. If null,
     *                the users with access will not be updated.
     * @param groupIds ids of groups that should have access to the page. If null,
     *                the groups with access will not be updated.
     */
    public PageVisibility(List<Long> userIds, List<Long> groupIds) {
        this.userIds = userIds;
        this.groupIds = groupIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public List<Long> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(List<Long> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PageVisibility)) return false;

        PageVisibility that = (PageVisibility) o;

        if (userIds != null ? !userIds.equals(that.userIds) : that.userIds != null) return false;
        return groupIds != null ? groupIds.equals(that.groupIds) : that.groupIds == null;
    }

    @Override
    public int hashCode() {
        int result = userIds != null ? userIds.hashCode() : 0;
        result = 31 * result + (groupIds != null ? groupIds.hashCode() : 0);
        return result;
    }
}
