package com.domo.sdk.datasets.model;

import java.util.Map;
import java.util.Objects;

public class QueryNotification {
    public enum Type {
        PROJECTION_TIMEGRAIN(4001, "Projection queries must have a time grain."),
        PROJECTION_DATETIME(4002, "The requested projection has grain date but the joined date column is a date not datetime."),
        PROJECTION_BADRESULT(4003, "Projection query resulted in no data usable."),
        PROJECTION_BADCOLUMN(4004, "Projection can not project on column."),
        PROJECTION_NOGROUPBY(4005, "Group by not supported."),
        PROJECTION_BADSTART(4006, "The requested projection starts before available data."),
        PROJECTION_BADPERIOD(4007, "Projection couldn't get period elements."),
        PROJECTION_MODEL_ERROR(4008, "The projection model encountered an error."),
        PROJECTION_ALLCOLUMNS(4009, "Projection contained all columns."),
        PROJECTION_BADNUMBER(4010, "The projection didn't result in a finite number."),
        PROJECTION_NULLVALUE(4011, "The projection for the column resulted in a null."),
        PROJECTION_DATERANGE(4012, "The projection for the column didn't have correct date range"),
        PROJECTION_NOVALUES(4013, "The projection request didn't generate any projections for the column");

        private int code;
        private String message;

        private Type(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public QueryNotification get(Map<String, String> parameters) {
            return new QueryNotification(code, message, parameters);
        }

        public int getCode() {
            return code;
        }
    }

    private int code;
    private String message;
    private Map<String, String> parameters;

    public QueryNotification() { /* Default constructor to make fasterxml.jackson happy */ }

    public QueryNotification(int code, String message, Map<String, String> parameters) {
        this.code = code;
        this.message = message;
        this.parameters = parameters;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryNotification that = (QueryNotification) o;
        return this.code == that.code &&
                Objects.equals(this.getMessage(), that.getMessage()) &&
                Objects.deepEquals(this.getParameters(), that.getParameters());
    }
}
