package com.example.vistora.services;

import com.example.vistora.config.DatabaseConfig;
import com.example.vistora.models.ColumnMetaData;
import com.example.vistora.models.TableMetaData;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class MetaDataService {
    private final DatabaseConfig databaseConfig;

    public MetaDataService(DatabaseConfig databaseConfig) {
        this.databaseConfig = databaseConfig;
    }

    public List<TableMetaData> getDatabaseMetadata() {
        List<TableMetaData> tables = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(
                databaseConfig.getUrl(), databaseConfig.getUsername(), databaseConfig.getPassword())) {

            DatabaseMetaData metaData = connection.getMetaData();

            // Get schema name from connection URL
            String schemaName = connection.getCatalog();

            ResultSet tablesResultSet = metaData.getTables(schemaName, null, "%", new String[]{"TABLE"});

            while (tablesResultSet.next()) {
                String tableName = tablesResultSet.getString("TABLE_NAME");

                // Ensure the table belongs to the expected schema
                if (isValidTable(schemaName, tableName, connection)) {
                    List<ColumnMetaData> columns = getColumnMetadata(metaData, schemaName, tableName);
                    tables.add(new TableMetaData(tableName, columns));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tables;
    }

    private List<ColumnMetaData> getColumnMetadata(DatabaseMetaData metaData, String schemaName, String tableName) throws SQLException {
        List<ColumnMetaData> columns = new ArrayList<>();

        ResultSet columnsResultSet = metaData.getColumns(schemaName, null, tableName, "%");

        while (columnsResultSet.next()) {
            String columnName = columnsResultSet.getString("COLUMN_NAME");
            String dataType = columnsResultSet.getString("TYPE_NAME");
            int columnSize = columnsResultSet.getInt("COLUMN_SIZE");
            boolean isNullable = columnsResultSet.getInt("NULLABLE") == DatabaseMetaData.columnNullable;

            columns.add(new ColumnMetaData(columnName, dataType, columnSize, isNullable));
        }

        return columns;
    }

    private boolean isValidTable(String schemaName, String tableName, Connection connection) throws SQLException {
        String query = "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = ? AND table_name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, schemaName);
            stmt.setString(2, tableName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        }
        return false;
    }
}
