package com.domo.sdk.datasets.model;

import java.util.List;

public class Schema {
    private List<Column> columns;

    public Schema() {
    }

    public Schema(List<Column> columns) {
        this.columns = columns;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
