package com.domo.sdk.datasets.model;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    private List<Column> columns;

    public Schema() {
        this.columns = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Schema{" +
                "columns=" + columns.toString() +
                '}';
    }
}
