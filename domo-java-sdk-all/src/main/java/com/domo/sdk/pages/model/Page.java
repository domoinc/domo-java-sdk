package com.domo.sdk.pages.model;

import java.util.List;

public class Page {
    private long id;
    private String name;
    private long parentId;
    private long ownerId;
    private Boolean locked;
    private List<Long> collectionIds;
    private List<Long> cardIds;
    private PageVisibility pageVisibility;

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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<Long> getCollectionIds() {
        return collectionIds;
    }

    public void setCollectionIds(List<Long> collectionIds) {
        this.collectionIds = collectionIds;
    }

    public List<Long> getCardIds() {
        return cardIds;
    }

    public void setCardIds(List<Long> cardIds) {
        this.cardIds = cardIds;
    }

    public PageVisibility getPageVisibility() {
        return pageVisibility;
    }

    public void setPageVisibility(PageVisibility pageVisibility) {
        this.pageVisibility = pageVisibility;
    }

    /**
     * Helper for building new pages
     */
    public static class Builder {
        private Page toBuild;

        public Builder(String pageName) {
            toBuild = new Page();
            toBuild.setName(pageName);
        }

        public Builder setParentId(long parentId) {
            toBuild.setParentId(parentId);
            return this;
        }

        public Builder setOwnerId(long ownerId) {
            toBuild.setOwnerId(ownerId);
            return this;
        }

        public Builder setLocked(boolean locked) {
            toBuild.setLocked(locked);
            return this;
        }

        public Builder setCardIds(List<Long> cardIds) {
            toBuild.setCardIds(cardIds);
            return this;
        }

        public Builder setPageVisibility(PageVisibility pageVisibility) {
            toBuild.setPageVisibility(pageVisibility);
            return this;
        }

        public Page build() {
            return toBuild;
        }
    }
}
