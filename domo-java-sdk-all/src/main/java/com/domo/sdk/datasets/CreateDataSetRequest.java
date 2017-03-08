package com.domo.sdk.datasets;

import java.util.List;

/**
 * Created by clintchecketts on 3/8/17.
 */
public class CreateDataSetRequest {
    private String name;
    private String description;
    private long rows;
    private List<Column> schema;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getRows() {
        return rows;
    }

    public void setRows(long rows) {
        this.rows = rows;
    }

    public List<Column> getSchema() {
        return schema;
    }

    public void setSchema(List<Column> schema) {
        this.schema = schema;
    }
}
