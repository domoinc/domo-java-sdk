package com.domo.sdk.datasets.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class QueryResultSet {

    private String datasource;
    private String queryUrl;
    //For projections queries this is column name to predicted values map.
    private Map<String, Object[][]> projections;
    private List<QueryNotification> notifications;
    private List<String> columns;
    private List<QueryColumnMetadata> metadata;
    private boolean fromCache;
    private Object[][] rows;
    private int numRows;
    private int numColumns;

    public Map<String, Object[][]> getProjections() {
        return projections;
    }

    public void setProjections(Map<String, Object[][]> projections) {
        this.projections = projections;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public boolean isFromCache() {
        return fromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.fromCache = fromCache;
    }

    public List<QueryColumnMetadata> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<QueryColumnMetadata> metadata) {
        this.metadata = metadata;
    }

    public List<QueryNotification> getNofifications() { return notifications; }

    public void setNotifications(List<QueryNotification> notifications) { this.notifications = notifications; }

    public int getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public String getQueryUrl() {
        return queryUrl;
    }

    public void setQueryUrl(String queryUrl) {
        this.queryUrl = queryUrl;
    }

    public Object[][] getRows() {
        return rows;
    }

    public void setRows(Object[][] rows) {
        this.rows = rows;
    }

    public String getAsString(int row, int column) {
        if(rows[row][column] == null){
            return null;
        }
        ColumnType type = metadata.get(column).getType();
        try {
            switch (type) {
                case DOUBLE:
                    Double temp = ((BigDecimal) rows[row][column]).doubleValue();
                    DecimalFormat doubleFormat = getDoubleFormat(temp);
                    return doubleFormat.format(temp);
                case DECIMAL:
                    return DECIMAL_FORMAT.format(rows[row][column]);
            }
        } catch (Exception e) {
            // ignore
        }
        return rows[row][column].toString();
    }

    public BigDecimal getAsDecimal(int row, int column) {
        if(rows[row][column] == null){
            return null;
        } else if (rows[row][column] instanceof BigDecimal) {
            return (BigDecimal) rows[row][column];
        } else if (rows[row][column] instanceof Integer) {
            return BigDecimal.valueOf((Integer) rows[row][column]);
        } else if (rows[row][column] instanceof Long) {
            return BigDecimal.valueOf((Long) rows[row][column]);
        } else if (rows[row][column] instanceof BigInteger) {
            return BigDecimal.valueOf(((BigInteger) rows[row][column]).longValueExact());
        } else {
            try {
                return new BigDecimal(rows[row][column].toString());
            } catch (Exception e) {
                throw new ClassCastException(getClassCastExceptionMsg("Decimal", row, column));
            }
        }
    }

    public Long getAsLong(int row, int column) {
        if(rows[row][column] == null){
            return null;
        } else if (rows[row][column] instanceof Long) {
            return (long) rows[row][column];
        } else if (rows[row][column] instanceof Integer) {
            return ((Integer) rows[row][column]).longValue();
        } else if (rows[row][column] instanceof BigInteger) {
            return ((BigInteger) rows[row][column]).longValue();
        } else if (rows[row][column] instanceof BigDecimal) {
            return ((BigDecimal) rows[row][column]).longValue();
        } else {
            try {
                return new Long(rows[row][column].toString());
            } catch (Exception e) {
                throw new ClassCastException(getClassCastExceptionMsg("Long", row, column));
            }
        }
    }

    public Double getAsDouble(int row, int column) {
        if(rows[row][column] == null){
            return null;
        } else if (rows[row][column] instanceof Integer) {
            return ((Integer) rows[row][column]).doubleValue();
        } else if(rows[row][column] instanceof Long) {
            return ((Long) rows[row][column]).doubleValue();
        } else if (rows[row][column] instanceof BigInteger) {
            return ((BigInteger) rows[row][column]).doubleValue();
        } else if (rows[row][column] instanceof BigDecimal) {
            return ((BigDecimal) rows[row][column]).doubleValue();
        } else {
            try {
                return new Double(rows[row][column].toString());
            } catch (Exception e) {
                throw new ClassCastException(getClassCastExceptionMsg("Double", row, column));
            }
        }
    }

    public Long getDateAsMilliseconds(int row, int column) throws ParseException {
        if(rows[row][column] == null){
            return null;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parsed = dateFormat.parse(rows[row][column].toString());
        return parsed.getTime();
    }

    public Long getDateTimeAsMilliseconds(int row, int column) throws ParseException {
        if(rows[row][column] == null){
            return null;
        }
        final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date parsed = datetimeFormat.parse(rows[row][column].toString());
        return parsed.getTime();
    }

    private String getClassCastExceptionMsg(String type, int row, int column) {
        return "The object at row " + row + " column " + column + " of the " +
                "result set cannot be cast to an object of type " + type + ". Value: " +
                rows[row][column] == null ? "<null pointer>" : rows[row][column].toString();
    }

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.################################");

    public static DecimalFormat getDoubleFormat(double d) {
        String doubleFormat = "0.0###############################";
        String plainString = BigDecimal.valueOf(d).toPlainString();
        int decimalPos = plainString.indexOf(".");
        DecimalFormat format;
        if (decimalPos != -1 && plainString.length() < doubleFormat.length()) {
            format = new DecimalFormat(doubleFormat.substring(0, plainString.length() - decimalPos + 1));
        } else {
            format = new DecimalFormat(doubleFormat);
        }
        return format;
    }


}
