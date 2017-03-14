package com.domo.sdk.datasets.model;

public class Column {
    private ColumnType type;
    private String name;

    public Column(ColumnType type, String name) {
        this.type = type;
        this.name = name;
    }

    public ColumnType getType() {
        return type;
    }

    public void setType(ColumnType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Column{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
