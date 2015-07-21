package com.oss.common.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.oss.common.db.table.TableInfo;
import com.oss.common.db.table.TableVersion;
import com.oss.common.util.Gapplication;
import com.oss.common.util.OrmUtils;
import com.oss.common.util.SqlBuilder;

public class DateBaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase mDefaultWritableDatabase = null;
    public DateBaseHelper(Context context) {
        super(context, getDBName(), new SugarCursorFactory(true), getVersion());
    }

    /**
     * 获取数据库版本号
     *
     * @return
     */
    private static int getVersion() {
        // TODO Auto-generated method stub
        return 1;
    }

    /**
     * 获取数据库名称
     *
     * @return
     */
    public static String getDBName() {
        // TODO Auto-generated method stub
        return "ddd";
    }

    public DateBaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        /**创建数据库表的版本表*/
        this.mDefaultWritableDatabase = db;
        createTableVersion();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        this.mDefaultWritableDatabase = db;
        DataBaseProvider<TableVersion> dbProvider = DataBaseProvider.getDbProvider(TableVersion.class);
        dbProvider.managerVersionTable(dbProvider);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        this.mDefaultWritableDatabase = db;
    }

    private static DateBaseHelper dateBaseHelper = null;

    /**
     * 获取数据库实例
     *
     * @param context
     * @return
     */
    public static DateBaseHelper getInstance(Context context) {
        if (dateBaseHelper == null) {
            synchronized (DateBaseHelper.class) {
                if (dateBaseHelper == null) {
                    dateBaseHelper = new DateBaseHelper(context);
                }
            }

        }
        return dateBaseHelper;
    }

    public void createTableVersion() {
        DataBaseProvider<TableVersion> dbProvider = DataBaseProvider.getDbProvider(TableVersion.class);
        dbProvider.createTable();
        TableVersion appVersion = OrmUtils.getAppVertion(Gapplication.getInstance());
        appVersion.tableName = dbProvider.mTableInfo.mTableName;
        dbProvider.insert(appVersion);
    }

    @Override
    public SQLiteDatabase getReadableDatabase() {
        if (mDefaultWritableDatabase !=null){
            return  mDefaultWritableDatabase;
        }else {
            return super.getReadableDatabase();
        }
    }
}
