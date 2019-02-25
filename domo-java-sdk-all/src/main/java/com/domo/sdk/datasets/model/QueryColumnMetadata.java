package com.domo.sdk.datasets.model;

import com.google.common.base.MoreObjects;

public class QueryColumnMetadata {
    private ColumnType type;
    private String dataSourceId;
    private int maxLength;
    private int minLength;
    private int periodIndex;

    public int getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getMinLength() {
        return minLength;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public int getPeriodIndex() { return this.periodIndex; }

    public void setPeriodIndex(int periodIndex) { this.periodIndex = periodIndex; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("type", type)
                .add("dataSourceId", dataSourceId)
                .add("maxLength", maxLength)
                .add("minLength", minLength)
                .add("periodIndex", periodIndex)
                .toString();
    }
}
