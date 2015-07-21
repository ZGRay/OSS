package com.oss.common.util;

import com.oss.common.db.table.PrimaryKeyInfo;
import com.oss.common.db.table.PropertyInfo;
import com.oss.common.db.table.TableInfo;
import com.oss.common.model.SqlValue;

public class SqlBuilder {
    private static final String SELECT_SQL = "SELECT * FROM %s WHERE %s = ? ";
    private static final String SELECT_SQL_WHERE = "SELECT * FROM %s WHERE %s ;";

    public static String getCreateTableSql(TableInfo tableInfo) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append(tableInfo.mTableName)
                .append(" ( ");
        PrimaryKeyInfo keyInfo = tableInfo.mPrimaryKey;
        builder.append(keyInfo.columnName).append(" ");
        builder.append(TableInfo.Type4SQL.get(keyInfo.dataType)).append(" PRIMARY KEY");
        if (keyInfo.isAutoIncrement) {
            builder.append(" AUTOINCREMENT");
        }

        for (PropertyInfo proInfo : tableInfo.mPropertyInfos) {
            builder.append(", ");
            builder.append(proInfo.columnName)
                    .append(" ")
                    .append(TableInfo.Type4SQL.get(proInfo.dataType));
        }
        builder.append(")");
        return builder.toString();
    }

    public static String getAllSql(TableInfo tableInfo) {
        // TODO Auto-generated method stub

        return "select * from " + tableInfo.mTableName;
    }

    public static String getUpdateTableSql(PropertyInfo propertyInfo, TableInfo tableInfo) {
        // TODO Auto-generated method stub
        return "alter table " + tableInfo.mTableName + " add " + propertyInfo.columnName + " "+TableInfo.Type4SQL.get(propertyInfo.dataType);
    }

    public static String getInsertSql(TableInfo mTableInfo) {
        // TODO Auto-generated method stub
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(mTableInfo.mTableName);
        builder.append(" ( ");

        return null;
    }

    public static SqlValue getFindSqlById(TableInfo mTableInfo, Object primrayKeyValue) {
        return getFindSql(mTableInfo.mTableName, mTableInfo.mPrimaryKey.columnName, primrayKeyValue);
    }

    public static String getWhereClause(String key) {
        return String.format("%s = ? ", key);
    }

    public static String getSearchTableSql(String tableName) {
        return "SELECT COUNT(*) AS c FROM sqlite_master WHERE type ='table' AND name ='" + tableName.trim() + "' ";
    }

    public static SqlValue getFindSql(String tablename, String column, Object Value) {
        String where_clause = getWhereClause(column);
        String sql = String.format(SELECT_SQL_WHERE, tablename, where_clause);
        SqlValue sqlValue = new SqlValue();
        sqlValue.sql = sql;
        sqlValue.addValue(Value);
        return sqlValue;
    }



}
