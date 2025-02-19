package com.example.vistora.models;




public class ColumnMetaData
{
    public ColumnMetaData() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(int columnSize) {
        this.columnSize = columnSize;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public ColumnMetaData(String columnName, String dataType, int columnSize, boolean isNullable) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.columnSize = columnSize;
        this.isNullable = isNullable;
    }

    private String columnName;
    private String dataType;
    private int columnSize;
    private boolean isNullable;
}
