package com.domo.sdk.datasets.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IceBoxQueryRequest {
    private final String sql;

    public IceBoxQueryRequest(@JsonProperty("sql") String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }
}
