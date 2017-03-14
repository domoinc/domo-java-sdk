package com.domo.sdk.streams.model;

/**
 * Created by bobbyswingler on 3/8/17.
 */
public class StreamExecution
{
    private long id;
    private String startedAt;
    private String currentState;
    private String createdAt;
    private String modifiedAt;

    public StreamExecution(){
        this.id = 0;
        this.startedAt = "";
        this.currentState = "";
        this.createdAt = "";
        this.modifiedAt = "";
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt( String startedAt ) {
        this.startedAt = startedAt;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState( String currentState ) {
        this.currentState = currentState;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt( String createdAt ) {
        this.createdAt = createdAt;
    }

    public String getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt( String modifiedAt ) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public boolean equals( Object o ) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StreamExecution that = (StreamExecution) o;

        if (getId() != that.getId()) {
            return false;
        }
        if (getStartedAt() != null ? !getStartedAt().equals(that.getStartedAt()) : that.getStartedAt() != null) {
            return false;
        }
        if (getCurrentState() != null ? !getCurrentState().equals(that.getCurrentState()) : that.getCurrentState() != null) {
            return false;
        }
        if (getCreatedAt() != null ? !getCreatedAt().equals(that.getCreatedAt()) : that.getCreatedAt() != null) {
            return false;
        }
        return getModifiedAt() != null ? getModifiedAt().equals(that.getModifiedAt()) : that.getModifiedAt() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) ( getId() ^ ( getId() >>> 32 ) );
        result = 31 * result + ( getStartedAt() != null ? getStartedAt().hashCode() : 0 );
        result = 31 * result + ( getCurrentState() != null ? getCurrentState().hashCode() : 0 );
        result = 31 * result + ( getCreatedAt() != null ? getCreatedAt().hashCode() : 0 );
        result = 31 * result + ( getModifiedAt() != null ? getModifiedAt().hashCode() : 0 );
        return result;
    }

    @Override
    public String toString() {
        return "StreamExecution{" +
                "id=" + id +
                ", startedAt='" + startedAt + '\'' +
                ", currentState='" + currentState + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", modifiedAt='" + modifiedAt + '\'' +
                '}';
    }
}
