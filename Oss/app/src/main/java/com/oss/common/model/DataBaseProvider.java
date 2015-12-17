package com.oss.common.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.oss.common.db.table.One2ManyInfo;
import com.oss.common.db.table.One2ManyLazyLoader;
import com.oss.common.db.table.One2OneInfo;
import com.oss.common.db.table.One2OneLazyLoader;
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

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 * @param <T>
 */
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
        /**  过滤版本表的检查        （无用 -如果当前的表 为 版本表，则创建 or 更新）*/
        if (!mTableInfo.mClazz.getName().equals(TableVersion.class.getName())) {
            checkTableVersionIsUpdate();
        }
    }

    private void checkTableVersionIsUpdate() {
        DataBaseProvider<TableVersion> dbProvider = DataBaseProvider.getDbProvider(TableVersion.class);
        managerVersionTable(dbProvider);
    }

    public void managerVersionTable(DataBaseProvider<TableVersion> dataBaseProvider) {

        TableVersion appVersion = OrmUtils.getAppVertion(Gapplication.getInstance());
        TableVersion tableVersion = dataBaseProvider.get(mTableInfo.mTableName);
        if (tableVersion == null) {
            createTable();
            // 更新表的版本
            appVersion.tableName = mTableInfo.mTableName;
            dataBaseProvider.insert(appVersion);
        } else {
            if (tableVersion.versionCode != appVersion.versionCode) {
                updateTable(mDb);
                appVersion.tableName = mTableInfo.mTableName;
                dataBaseProvider.update(appVersion);
            }
        }
    }

    public void createTable() {
        String sql = SqlBuilder.getCreateTableSql(mTableInfo);
        mDb.execSQL(sql);
    }

    private void updateTable(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String sql = SqlBuilder.getAllSql(mTableInfo);
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

    public long insert(T t) {
        long i = -1;
        if (checkParams(t, "insert")) {
            mDb.beginTransaction();
            try {
                /** 添加类数据到数据库 */
                i = mDb.insert(mTableInfo.mTableName, mTableInfo.mPropertyInfos.get(0).columnName, toContentValues(t));
                if (i != -1) {
                    /** 如果类中有 其他类的引用，添加其他类的数据到数据库中， */
                    executeOne2OneAction(t, mTableInfo.mOne2OneInfos, INSERT, "");
                    executeOne2ManyAction(t, mTableInfo.mOne2Manies, INSERT, "");
                } else {
                    return i;
                }

                mDb.setTransactionSuccessful();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
                return -1;
            } finally {
                mDb.endTransaction();
            }
        }


        return i;
    }

    private void insertReferiendTable(T t) {
        mDb.insert(mTableInfo.mTableName, mTableInfo.mPropertyInfos.get(0).columnName, toContentValues(t));
        /** 如果类中有 其他类的引用，添加其他类的数据到数据库中， */
        executeOne2OneAction(t, mTableInfo.mOne2OneInfos, INSERT, "");
        executeOne2ManyAction(t, mTableInfo.mOne2Manies, INSERT, "");
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
     *
     * @param idValue
     */
    public void delete(Object idValue) {
        if (idValue == null) {
            Log.v(TAG, String.format("%s method has a null parameter.", "deleteById"));
        } else {
            int affectRow = mDb.delete(mTableInfo.mTableName, SqlBuilder.getWhereClause(mTableInfo.mPrimaryKey.columnName), new String[]{idValue.toString()});
            if (affectRow > 0) {
                executeOne2OneAction(null, mTableInfo.mOne2OneInfos, DELETE, idValue.toString());
                executeOne2ManyAction(null, mTableInfo.mOne2Manies, DELETE, idValue.toString());
            }
        }
    }

    /**
     * 根据条件删除数据, 条件为空的时候 将会删除所有的数据
     *
     * @param whereClause
     * @param whereArgs
     */
    public void delete(String whereClause, String[] whereArgs) {
        mDb.beginTransaction();
        Cursor cursor = null;
        try {
            if (mTableInfo.mOne2OneInfos.size() > 0 || mTableInfo.mOne2Manies.size() > 0) {
                String primaryColumn = mTableInfo.mPrimaryKey.columnName;
                cursor = mDb.query(mTableInfo.mTableName, new String[]{primaryColumn}, whereClause, whereArgs, "", "", "");
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        String columnValue = cursor.getString(cursor.getColumnIndex(primaryColumn));
                        executeOne2OneAction(null, mTableInfo.mOne2OneInfos, DELETE, columnValue);
                        executeOne2ManyAction(null, mTableInfo.mOne2Manies, DELETE, columnValue);
                    }
                }
            }
            mDb.delete(mTableInfo.mTableName, whereClause, whereArgs);
            mDb.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            mDb.endTransaction();
        }
    }

    /**
     * 删除所有数据
     */
    public void deleteAll() {
        delete("", new String[0]);
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

    /**
     * 更新数据 （主键ID必须不能为空）
     *
     * @param entity
     * @return
     */
    public int update(T entity) {
        return update(entity, SqlBuilder.getWhereClause(mTableInfo.mPrimaryKey.columnName), new String[]{getPrimrayKeyValue(mTableInfo.mPrimaryKey, entity).toString()});
    }

    /**
     * 根据条件更新数据（条件为空的时候，将会更新所有的数据）
     *
     * @param entity
     * @param whereClause
     * @param whereArgs
     * @return
     */
    public int update(T entity, String whereClause, String[] whereArgs) {
        int i = 0;
        if (checkParams(entity, "update")) {
            mDb.beginTransaction();
            try {
                i = mDb.update(mTableInfo.mTableName, toContentValues(entity), whereClause, whereArgs);
                if (i > 0) {
                    executeOne2OneAction(entity, mTableInfo.mOne2OneInfos, UPDATE, "");
                    executeOne2ManyAction(entity, mTableInfo.mOne2Manies, UPDATE, "");
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

    /**
     * 根据主键查找数据(默认不获取，一对一，一对多的数据)
     *
     * @param idValue
     * @return
     */
    public T get(Object idValue) {
        return get(idValue, 3);
    }

    /**
     * 根据主键查找，同时所有查找“一对一”的数据,不查找一对多的数据
     *
     * @param idValue
     * @return
     */
    public T getWithOne2One(Object idValue) {
        return get(idValue, 1);
    }

    /**
     * 根据主键查找，同时所有查找“一对多”的数据，不查找一对一的数据
     *
     * @param idValue
     * @return
     */
    public T getWithOne2Many(Object idValue) {
        return get(idValue, 2);
    }

    /**
     * 根据主键查找，并获取一对多，一对一的数据
     *
     * @param idValue
     * @return
     */
    public T getWithAll(Object idValue) {
        return get(idValue, 0);
    }

    /**
     * @param idValue
     * @return
     */
    private T get(Object idValue, int action) {
        Cursor cursor = null;
        try {
            SqlValue sqlValue = SqlBuilder.getFindSqlById(mTableInfo, idValue);
            cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
            if (cursor != null && cursor.moveToFirst()) {
                return convertCorToObj(cursor, action);
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


    public List<T> getAll() {
        Cursor cursor = null;
        List<T> result = new ArrayList<T>();
        try {
            String sql = SqlBuilder.getAllSql(mTableInfo);
            cursor = mDb.rawQuery(sql, new String[0]);
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

    private T getOen2ManyData(T result, int action) {
        try {
            ArrayList<One2ManyInfo> o2mList = mTableInfo.mOne2Manies;
            if (o2mList.size() > 0) {
                One2ManyLazyLoader lazyLoader;
                Object id = getPrimrayKeyValue(mTableInfo.mPrimaryKey, result);
                for (One2ManyInfo one2ManyInfo : o2mList) {
                    DataBaseProvider<T> o2mDbPro = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2ManyInfo.manyClass);
                    if (one2ManyInfo.dataClassType == One2ManyLazyLoader.class) {
                        lazyLoader = new One2ManyLazyLoader(id, one2ManyInfo, o2mDbPro);
                        if (action == 0 || action == 2) {
                            List<T> list = o2mDbPro.getListByForeignkey(one2ManyInfo.referencedColumnName, id);
                            lazyLoader.setManys(list);
                        }
                        one2ManyInfo.field.setAccessible(true);
                        one2ManyInfo.field.set(result, lazyLoader);
                    } else {
                        if (action == 0 || action == 2) {
                            List<T> list = o2mDbPro.getListByForeignkey(one2ManyInfo.referencedColumnName, id);
                            if (list != null) {
                                one2ManyInfo.field.setAccessible(true);
                                one2ManyInfo.field.set(result, list);
                            }

                        }
                    }


                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    private T getOen2OneData(T primaryData, int action) {
        try {
            ArrayList<One2OneInfo> o2oList = mTableInfo.mOne2OneInfos;
            if (o2oList.size() > 0) {
                Object id = getPrimrayKeyValue(mTableInfo.mPrimaryKey, primaryData);
                One2OneLazyLoader lazyLoader;
                for (One2OneInfo one2OneInfo : o2oList) {
                    DataBaseProvider<T> o2oDb = (DataBaseProvider<T>) DataBaseProvider.getDbProvider(one2OneInfo.oneClass);
                    if (one2OneInfo.dataClassType == One2OneLazyLoader.class) {
                        lazyLoader = new One2OneLazyLoader(id, one2OneInfo, o2oDb);
                        if (action == 0 || action == 1) {
                            T foreignResult = o2oDb.getByForeignKey(one2OneInfo.referencedColumnName, id);
                            lazyLoader.setForeignEntity(foreignResult);
                        }
                        one2OneInfo.field.setAccessible(true);
                        one2OneInfo.field.set(primaryData, lazyLoader);
                    } else {
                        if (action == 0 || action == 1) {
                            T foreignResult = o2oDb.getByForeignKey(one2OneInfo.referencedColumnName, id);
                            one2OneInfo.field.setAccessible(true);
                            one2OneInfo.field.set(primaryData, foreignResult);
                        }
                    }


                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return primaryData;
    }

    public T getByForeignKey(String referencedColumnName, Object id) {
        Cursor cursor = null;
        SqlValue sqlValue = SqlBuilder.getFindSql(mTableInfo.mTableName, referencedColumnName, id);
        try {
            cursor = mDb.rawQuery(sqlValue.sql, sqlValue.getBindArgsAsStringArray());
            if (cursor != null && cursor.moveToFirst()) {
                return convertCorToObj(cursor, 0);
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

    private T convertCorToObj(Cursor cursor, int action) {
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
            result = getOen2OneData(result, action);
            result = getOen2ManyData(result, action);


            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<T> getListByForeignkey(String foreignKey, Object foreignKeyValue) {
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
                T t = convertCorToObj(cursor, 0);
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

    public static synchronized <T> DataBaseProvider<T> getDbProvider(Class<T> clazz) {
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
                                    o2mDbPro.insertReferiendTable((T) object);
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
                                o2oDbPro.insertReferiendTable((T) iobj);
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
