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
    private PageVisibility visibility;

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

    public PageVisibility getVisibility() {
        return visibility;
    }

    public void setVisibility(PageVisibility visibility) {
        this.visibility = visibility;
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
            toBuild.setVisibility(pageVisibility);
            return this;
        }

        public Page build() {
            return toBuild;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Page)) return false;

        Page page = (Page) o;

        if (id != page.id) return false;
        if (parentId != page.parentId) return false;
        if (ownerId != page.ownerId) return false;
        if (!name.equals(page.name)) return false;
        if (locked != null ? !locked.equals(page.locked) : page.locked != null) return false;
        if (collectionIds != null ? !collectionIds.equals(page.collectionIds) : page.collectionIds != null)
            return false;
        if (cardIds != null ? !cardIds.equals(page.cardIds) : page.cardIds != null) return false;
        return visibility != null ? visibility.equals(page.visibility) : page.visibility == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + name.hashCode();
        result = 31 * result + (int) (parentId ^ (parentId >>> 32));
        result = 31 * result + (int) (ownerId ^ (ownerId >>> 32));
        result = 31 * result + (locked != null ? locked.hashCode() : 0);
        result = 31 * result + (collectionIds != null ? collectionIds.hashCode() : 0);
        result = 31 * result + (cardIds != null ? cardIds.hashCode() : 0);
        result = 31 * result + (visibility != null ? visibility.hashCode() : 0);
        return result;
    }
}
