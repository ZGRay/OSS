package com.oss.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oss.common.db.table.One2ManyInfo;
import com.oss.common.db.table.One2OneInfo;
import com.oss.common.db.table.PrimaryKeyInfo;
import com.oss.common.db.table.PropertyInfo;
import com.oss.common.db.table.TableInfo;
import com.oss.common.db.table.TableVersion;
import com.oss.common.util.Gapplication;
import com.oss.common.util.OrmUtils;
import com.oss.common.util.SqlBuilder;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.util.Log;

public class DataBaseProvider<T> {
    public static final int INSERT = 1;
    public static final int DELETE = 2;
    public static final int UPDATE = 3;
    public static final int QUERY = 4;
    private static final String TAG = "DataBaseProvider";
    public TableInfo mTableInfo;
    public Class<T> mClass;
    SQLiteDatabase mDb;

    public DataBaseProvider(Class<T> tClass) {
        mClass = tClass;
        mTableInfo = TableInfo.instance(tClass);
        mDb = DateBaseHelper.getInstance(Gapplication.getInstance()).getReadableDatabase();
        /**
         * 从 db中获取表的版本 1 是否创建表 2校验数据库字段与类字段是否匹配。增加字段 or
         *
         */
        checkTableVersionIsUpdate();
    }

    private void checkTableVersionIsUpdate() {
        /**如果当前的表 为 版本表，则创建 or 更新*/
        if (mTableInfo.mClazz.getName().equals(TableVersion.class.getName())) {
            managerVersionTable((DataBaseProvider<TableVersion>) this);
            return;
        }
        DataBaseProvider<TableVersion> dbProvider = DataBaseProvider.getDbProvider(TableVersion.class);
        managerVersionTable(dbProvider);
    }

    private void managerVersionTable(DataBaseProvider<TableVersion> dataBaseProvider) {

        TableVersion appVersion = OrmUtils.getAppVertion(Gapplication.getInstance());
        if (tabIsExist(mDb, mTableInfo.mTableName)) {
            //表存在
            TableVersion tableVer = dataBaseProvider.get(mTableInfo.mTableName);
            ///表存在，就会有版本号。 如果没有，记录此异常
            if (tableVer == null) {
                Log.v(TAG, "表存在，但没有版本记录，创建表，插入版本号有异常");
//                updateTable(mDb);
                appVersion.tableName = mTableInfo.mTableName;
                dataBaseProvider.insert(appVersion);
            } else {
                if (tableVer.versionCode != appVersion.versionCode) {
                    updateTable(mDb);
                    appVersion.tableName = mTableInfo.mTableName;
                    dataBaseProvider.update(appVersion);
                }
            }

        } else {
            createTable(mDb);
            // 更新表的版本
            appVersion.tableName = mTableInfo.mTableName;
            dataBaseProvider.insert(appVersion);
        }


        // 获取 table的版本，为空，表未创建。
//        if (tableVer == null ) {
//            createTableOrUpdate(mDb);
//            // 更新表的版本
//            appVersion.tableName = mTableInfo.mTableName;
//            dataBaseProvider.insertOrUpdate(appVersion);
//        } else {
//            // table的版本 不为空，与 app的版本对比， 不相等。 跟新表。
//            if (tableVer.versionCode != appVersion.versionCode) {
//                createTableOrUpdate(mDb);
//                // 更新表的版本
//                appVersion.tableName = mTableInfo.mTableName;
//                dataBaseProvider.insertOrUpdate(appVersion);
//            }
//            // 无需跟新
//        }

    }

    private void createTable(SQLiteDatabase db) {
        String sql = SqlBuilder.getCreateTableSql(mTableInfo);
        db.execSQL(sql);
    }

    private void updateTable(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = SqlBuilder.getTableColumnsSql(mTableInfo);
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<PropertyInfo> tempInfos = new ArrayList<PropertyInfo>();
        int i;
        /** 查询表是否需要更新，添加字段 */
        for (PropertyInfo propertyInfo : mTableInfo.mPropertyInfos) {
            i = cursor.getColumnIndex(propertyInfo.columnName);
            if (i == -1) {
                tempInfos.add(propertyInfo);
            }
        }
        if (tempInfos.size() > 0) {
            db.beginTransaction();
            try {
                for (PropertyInfo propertyInfo : tempInfos) {
                    sql = SqlBuilder.getUpdateTableSql(propertyInfo, mTableInfo);
                    db.execSQL(sql);
                }
                db.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
                db.endTransaction();
            }

        }

    }

    /**
     * 检查表是否存在
     *
     * @param db
     * @param tableName
     * @return
     */
    private boolean tabIsExist(SQLiteDatabase db, String tableName) {
        if (TextUtils.isEmpty(tableName)) {
            return false;
        }
        Cursor cursor = null;
        try {
            String sql = SqlBuilder.getSearchTableSql(tableName);
            cursor = db.rawQuery(sql, null);
            if (cursor.moveToFirst()) {
                int count = cursor.getInt(0);
                if (count > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return false;
    }

    public void insert(T t) {
        if (checkParams(t, "insert")) {
            mDb.beginTransaction();
            try {
                /** 添加类数据到数据库 */
                mDb.insert(mTableInfo.mTableName, mTableInfo.mPropertyInfos.get(0).columnName, toContentValues(t));
                /** 如果类中有 其他类的引用，添加其他类的数据到数据库中， */
                executeOne2OneAction(t, mTableInfo.mOne2OneInfos, INSERT, "");
                executeOne2ManyAction(t, mTableInfo.mOne2Manies, INSERT, "");
                mDb.setTransactionSuccessful();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            } finally {
                mDb.endTransaction();
            }
        }


    }

    public void insert(List<T> list) {
        mDb.beginTransaction();
        try {
            for (T t : list) {
                /** 添加类数据到数据库 */
                mDb.insert(mTableInfo.mTableName, mTableInfo.mPropertyInfos.get(0).columnName, toContentValues(t));
                /** 如果类中有 其他类的引用，添加其他类的数据到数据库中， */
                executeOne2OneAction(t, mTableInfo.mOne2OneInfos, INSERT, "");
                executeOne2ManyAction(t, mTableInfo.mOne2Manies, INSERT, "");
            }

            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            mDb.endTransaction();
        }
    }


    public void insertOrUpdate(T t) {
        // TODO Auto-generated method stub
        if (checkParams(t, "replace")) {
            Cursor cursor = null;
            SqlValue sqlValue = SqlBuilder.getFindSqlById(mTableInfo, getPrimrayKeyValue(mTableInfo.mPrimaryKey, t));

            try {
                cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
                if (cursor != null && cursor.moveToFirst()) {
                    update(t);
                } else {
                    insert(t);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

        }
    }


    /**
     * 根据主键删除id
     * @param idValue
     */
    public void deleteById(Object idValue) {
        if (idValue == null){
            Log.v(TAG, String.format("%s method has a null parameter.", "deleteById"));
        }else{
            int affectRow = mDb.delete(mTableInfo.mTableName, SqlBuilder.getWhereClause(mTableInfo.mPrimaryKey.columnName), new String[]{idValue.toString()});
            if (affectRow > 0) {
                executeOne2OneAction(null, mTableInfo.mOne2OneInfos, DELETE, idValue.toString());
                executeOne2ManyAction(null, mTableInfo.mOne2Manies, DELETE, idValue.toString());
            }
        }
    }

    public void deleteAll() {
        mDb.beginTransaction();
        Cursor cursor = null;
        try {
            if (mTableInfo.mOne2OneInfos.size() > 0 || mTableInfo.mOne2Manies.size() > 0) {

                String primaryColumn = mTableInfo.mPrimaryKey.columnName;
                cursor = mDb.query(mTableInfo.mTableName, new String[]{primaryColumn}, "", new String[0], "", "", "");

                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String columnValue = cursor.getString(cursor.getColumnIndex(primaryColumn));
                        executeOne2OneAction(null, mTableInfo.mOne2OneInfos, DELETE, columnValue);
                        executeOne2ManyAction(null, mTableInfo.mOne2Manies, DELETE, columnValue);
                    }
                }
            }
            mDb.delete(mTableInfo.mTableName, null, new String[0]);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            mDb.endTransaction();

        }
    }

    /**
     * 删除子表数据
     *
     * @param foreignKey   字表关联主表的外键。
     * @param foreignValue 外键的值，也就是主表的主键的值
     */
    private void deleteReferendTableData(String foreignKey, String foreignValue) {
        String primaryColumn;
        Cursor cursor = null;
        try {
            //如果相关的此表，有关联的其他的表。先查询主键，删除此表关联的其他表数据，在删除相关此表数据。
            if (mTableInfo.mOne2OneInfos.size() > 0 || mTableInfo.mOne2Manies.size() > 0) {
                primaryColumn = mTableInfo.mPrimaryKey.columnName;
                cursor = mDb.query(mTableInfo.mTableName, new String[]{primaryColumn}, SqlBuilder.getWhereClause(foreignKey), new String[]{foreignValue}, "", "", "");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String columnValue = cursor.getString(cursor.getColumnIndex(primaryColumn));
                        executeOne2OneAction(null, mTableInfo.mOne2OneInfos, DELETE, columnValue);
                        executeOne2ManyAction(null, mTableInfo.mOne2Manies, DELETE, columnValue);
                    }
                }
            }
            //删除本表数据
            mDb.delete(mTableInfo.mTableName, SqlBuilder.getWhereClause(foreignKey), new String[]{foreignValue});
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }


    }

    public int update(T t) {
        int i = 0;
        if (checkParams(t, "update")) {
            mDb.beginTransaction();
            try {
                i = mDb.update(mTableInfo.mTableName, toContentValues(t), SqlBuilder.getWhereClause(mTableInfo.mPrimaryKey.columnName), new String[]{getPrimrayKeyValue(mTableInfo.mPrimaryKey, t).toString()});
                if (i > 0) {
                    executeOne2OneAction(t, mTableInfo.mOne2OneInfos, UPDATE, "");
                    executeOne2ManyAction(t, mTableInfo.mOne2Manies, UPDATE, "");
                }

                mDb.setTransactionSuccessful();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                mDb.endTransaction();
            }


        }
        return i;
    }

    private void updateReferendTable(T t) {
        int i = mDb.update(mTableInfo.mTableName, toContentValues(t), SqlBuilder.getWhereClause(mTableInfo.mPrimaryKey.columnName), new String[]{getPrimrayKeyValue(mTableInfo.mPrimaryKey, t).toString()});
        if (i > 0) {
            executeOne2OneAction(t, mTableInfo.mOne2OneInfos, UPDATE, "");
            executeOne2ManyAction(t, mTableInfo.mOne2Manies, UPDATE, "");
        }

    }

    public T get(Object idValue) {
        Cursor cursor = null;
        try {
            SqlValue sqlValue = SqlBuilder.getFindSqlById(mTableInfo, idValue);
            cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
            if (cursor != null && cursor.moveToFirst()) {
                return convertCorToObj(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    private T getOen2ManyData(T result) {
        try {
            ArrayList<One2ManyInfo> o2mList = mTableInfo.mOne2Manies;
            if (o2mList.size() > 0) {
                Object id = getPrimrayKeyValue(mTableInfo.mPrimaryKey, result);
                for (One2ManyInfo one2ManyInfo : o2mList) {
                    DataBaseProvider<T> o2mDbPro = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2ManyInfo.manyClass);
                    List<T> list = o2mDbPro.getListByForeignkey(one2ManyInfo.referencedColumnName, id);
                    if (list != null) {
                        one2ManyInfo.field.setAccessible(true);
                        one2ManyInfo.field.set(result, list);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private T getOen2OneData(T primaryData) {
        try {
            ArrayList<One2OneInfo> o2oList = mTableInfo.mOne2OneInfos;
            if (o2oList.size() > 0) {
                Object id = getPrimrayKeyValue(mTableInfo.mPrimaryKey, primaryData);
                for (One2OneInfo one2OneInfo : o2oList) {
                    DataBaseProvider<T> o2oDb = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2OneInfo.oneClass);
                    T foreignResult = o2oDb.getByForeignKey(one2OneInfo.referencedColumnName, id);
                    one2OneInfo.field.set(primaryData, foreignResult);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return primaryData;
    }

    private T getByForeignKey(String referencedColumnName, Object id) {
        Cursor cursor = null;
        SqlValue sqlValue = SqlBuilder.getFindSql(mTableInfo.mTableName, referencedColumnName, id);
        try {
            cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
            if (cursor != null && cursor.moveToFirst()) {
                return convertCorToObj(cursor);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return null;
    }

    private T convertCorToObj(Cursor cursor) {
        try {
            T result = mClass.newInstance();
            ArrayList<PropertyInfo> propertyInfos = mTableInfo.mPropertyInfos;
            for (PropertyInfo propertyInfo : propertyInfos) {
                propertyInfo.field.setAccessible(true);
                if (propertyInfo.dataType == TableInfo.MFT_STRING) {
                    propertyInfo.field.set(result, cursor.getString(cursor.getColumnIndex(propertyInfo.columnName)));
                } else if (propertyInfo.dataType == TableInfo.MFT_INTEGER) {
                    propertyInfo.field.setInt(result, cursor.getInt(cursor.getColumnIndex(propertyInfo.columnName)));
                } else if (propertyInfo.dataType == TableInfo.MFT_FLOAT) {
                    propertyInfo.field.setFloat(result, cursor.getFloat(cursor.getColumnIndex(propertyInfo.columnName)));
                } else if (propertyInfo.dataType == TableInfo.MFT_DATE) {

                } else if (propertyInfo.dataType == TableInfo.MFT_BOOLEAN) {
                    propertyInfo.field.setBoolean(result, Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(propertyInfo.columnName))));
                }
            }

            PrimaryKeyInfo primaryKeyInfo = mTableInfo.mPrimaryKey;
            primaryKeyInfo.field.setAccessible(true);
            if (primaryKeyInfo.dataType == TableInfo.MFT_STRING) {
                primaryKeyInfo.field.set(result, cursor.getString(cursor.getColumnIndex(primaryKeyInfo.columnName)));
            } else if (primaryKeyInfo.dataType == TableInfo.MFT_INTEGER) {
                primaryKeyInfo.field.setInt(result, cursor.getInt(cursor.getColumnIndex(primaryKeyInfo.columnName)));
            } else if (primaryKeyInfo.dataType == TableInfo.MFT_FLOAT) {
                primaryKeyInfo.field.setFloat(result, cursor.getFloat(cursor.getColumnIndex(primaryKeyInfo.columnName)));
            } else if (primaryKeyInfo.dataType == TableInfo.MFT_DATE) {

            } else if (primaryKeyInfo.dataType == TableInfo.MFT_BOOLEAN) {
                primaryKeyInfo.field.setBoolean(result, Boolean.parseBoolean(cursor.getString(cursor.getColumnIndex(primaryKeyInfo.columnName))));
            }
            result = getOen2OneData(result);
            result = getOen2ManyData(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<T> getListByForeignkey(String foreignKey, Object foreignKeyValue) {
        Cursor cursor = null;
        List<T> result = new ArrayList<T>();
        try {
            SqlValue sqlValue = SqlBuilder.getFindSql(mTableInfo.mTableName, foreignKey, foreignKeyValue);
            cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
            result = readObjectFromCursor(cursor);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return result;
    }

    private List<T> readObjectFromCursor(Cursor cursor) {
        List<T> result = new ArrayList<T>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                T t = convertCorToObj(cursor);
                result.add(t);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public boolean checkParams(T t, String mn) {
        if (t == null) {
            Log.v(TAG, String.format("%s method has a null parameter.", mn));
            return false;
        }
        return true;
    }

    private static final Map<Class, DataBaseProvider> modelProviders = new HashMap<Class, DataBaseProvider>();

    public static <T> DataBaseProvider<T> getDbProvider(Class<T> clazz) {
        DataBaseProvider<T> provider = modelProviders.get(clazz);
        if (provider == null) {
            provider = new DataBaseProvider<T>(clazz);
            modelProviders.put(clazz, provider);
        }
        return provider;
    }

    public ContentValues toContentValues(T t) {
        ContentValues values = new ContentValues();
        String type;
        String columnName;
        /** 判断主键是否是自增，是自赠，不能手动添加 */
        try {
            PrimaryKeyInfo kInfo = mTableInfo.mPrimaryKey;
            if (!kInfo.isAutoIncrement) {
                type = kInfo.dataType;
                columnName = kInfo.columnName;
                kInfo.field.setAccessible(true);

                if (type == TableInfo.MFT_STRING) {
                    values.put(columnName, (String) kInfo.field.get(t));
                } else if (type == TableInfo.MFT_INTEGER) {
                    values.put(columnName, (Integer) kInfo.field.get(t));
                } else if (type == TableInfo.MFT_FLOAT) {
                    values.put(columnName, (Float) kInfo.field.get(t));
                } else if (type == TableInfo.MFT_DATE) {
                    values.put(columnName, OrmUtils.toString((Date) kInfo.field.get(t)));
                } else if (type == TableInfo.MFT_BOOLEAN) {
                    values.put(columnName, (Boolean) kInfo.field.get(t));
                }
            }

            ArrayList<PropertyInfo> pInfos = mTableInfo.mPropertyInfos;

            for (PropertyInfo propertyInfo : pInfos) {
                type = propertyInfo.dataType;
                columnName = propertyInfo.columnName;
                propertyInfo.field.setAccessible(true);

                if (type == TableInfo.MFT_STRING) {
                    values.put(columnName, (String) propertyInfo.field.get(t));
                } else if (type == TableInfo.MFT_INTEGER) {
                    values.put(columnName, (Integer) propertyInfo.field.get(t));
                } else if (type == TableInfo.MFT_FLOAT) {
                    values.put(columnName, (Float) propertyInfo.field.get(t));
                } else if (type == TableInfo.MFT_DATE) {
                    values.put(columnName, OrmUtils.toString((Date) propertyInfo.field.get(t)));
                } else if (type == TableInfo.MFT_BOOLEAN) {
                    values.put(columnName, (Boolean) propertyInfo.field.get(t));
                }

            }
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return values;
    }

    public Object getPrimrayKeyValue(PrimaryKeyInfo kInfo, T t) {
        kInfo.field.setAccessible(true);
        try {
            return kInfo.field.get(t);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void executeOne2ManyAction(T t, ArrayList<One2ManyInfo> one2ManyInfos, int action, String deleteIdvalue) {
        Collection col;
        Iterator iterator;
        try {
            if (one2ManyInfos.size() > 0) {
                for (One2ManyInfo one2ManyInfo : one2ManyInfos) {
                    DataBaseProvider<T> o2mDbPro = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2ManyInfo.manyClass);
                    if (action == DELETE) {
                        o2mDbPro.deleteReferendTableData(one2ManyInfo.referencedColumnName, deleteIdvalue);
                        continue;
                    }
                    if (one2ManyInfo.field.get(t) instanceof Collection) {
                        one2ManyInfo.field.setAccessible(true);
                        col = (Collection) one2ManyInfo.field.get(t);
                        iterator = col.iterator();
                        while (iterator.hasNext()) {
                            Object object = iterator.next();
                            switch (action) {
                                case INSERT:
                                    o2mDbPro.insert((T) object);
                                    break;
                                case UPDATE:
                                    o2mDbPro.updateReferendTable((T) object);
                                    break;
                            }

                        }
                    }

                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param t
     * @param one2OneInfos
     * @param action       删改查 以外键进行
     */
    private void executeOne2OneAction(T t, ArrayList<One2OneInfo> one2OneInfos, int action, String deleteIdvalue) {

//        for (One2OneInfo oneInfo : oneInfos) {
//            DataBaseProvider<T> o2oDb = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(oneInfo.oneClass);
//            o2oDb.deleteReferendTableData(oneInfo.referencedColumnName, idValue.toString());
//        }
        if (one2OneInfos != null && one2OneInfos.size() > 0) {
            for (One2OneInfo one2OneInfo : one2OneInfos) {
                DataBaseProvider<T> o2oDbPro = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2OneInfo.oneClass);
                try {

                    switch (action) {
                        case INSERT:
                            one2OneInfo.field.setAccessible(true);
                            Object iobj = one2OneInfo.field.get(t);
                            if (iobj != null) {
                                o2oDbPro.insert((T) iobj);
                            }
                            break;
                        case DELETE:
//                              /**以 外键 删除 关联的 表*/
                            o2oDbPro.deleteReferendTableData(one2OneInfo.referencedColumnName, deleteIdvalue);
                            break;
                        case UPDATE:
                            one2OneInfo.field.setAccessible(true);
                            Object uobj = one2OneInfo.field.get(t);
                            if (uobj != null) {
                                o2oDbPro.updateReferendTable((T) uobj);
                            }
                            break;
                        case QUERY:

                            break;

                        default:
                            break;

                    }


                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
