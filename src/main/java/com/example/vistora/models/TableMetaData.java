package com.example.vistora.models;



import java.util.List;


public class TableMetaData{
    private String tableName;
    private List<ColumnMetaData> columns;

    public TableMetaData() {
    }

    public TableMetaData(String tableName, List<ColumnMetaData> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<ColumnMetaData> getColumns() {
        return columns;
    }

    public void setColumns(List<ColumnMetaData> columns) {
        this.columns = columns;
    }
}
