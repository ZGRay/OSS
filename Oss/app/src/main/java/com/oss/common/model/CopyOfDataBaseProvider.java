package com.oss.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oss.common.db.table.One2OneInfo;
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

public class CopyOfDataBaseProvider<T extends Object> {
	private static final String TAG = "DbProvider";
	public TableInfo mTableInfo;
	SQLiteDatabase mDb;

	public CopyOfDataBaseProvider(Class<T> tClass) {
		mTableInfo = TableInfo.instance(tClass);
		mDb = DateBaseHelper.getInstance(Gapplication.getInstance()).getReadableDatabase();
		/**
		 * 从 db中获取表的版本 1 是否创建表 2校验数据库字段与类字段是否匹配。增加字段 or
		 * 
		 */
		CheckTableVersionIsUpdate();
	}

	public void CheckTableVersionIsUpdate() {
		CopyOfDataBaseProvider<TableVersion> dbProvider = getDbProvider(TableVersion.class);
		TableVersion tableVer = dbProvider.get(dbProvider.mTableInfo.mPrimaryKey.columnName);
		TableVersion appVersion = OrmUtils.getAppVertion(Gapplication.getInstance());
		// 获取 table的版本，为空，表未创建。
		if (tableVer == null || tableVer.versionCode <= 0) {
			dbProvider.createTableOrUpdate(mDb);
		} else {
			// table的版本 不为空，与 app的版本对比， 不相等。 跟新表。
			if (tableVer.versionCode != appVersion.versionCode) {
				dbProvider.createTableOrUpdate(mDb);
			}
			// 无需跟新
		}
		// 更新表的版本
		dbProvider.insertOrUpdate(appVersion);
	}

	public void insertOrUpdate(T t) {
		// TODO Auto-generated method stub
	}

	private void createTableOrUpdate(SQLiteDatabase db) {
		String sql;
		if (tabIsExist(db, mTableInfo.mTableName)) {
			updateTable(db);
		} else {
			sql = SqlBuilder.getCreateTableSql(mTableInfo);
			db.execSQL(sql);
		}

	}

	private void updateTable(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = SqlBuilder.getTableColumnsSql(mTableInfo);
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<PropertyInfo> tempInfos = new ArrayList<PropertyInfo>();
		int i = -1;
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
		try {
			String sql = "select count(*) as c from " + DateBaseHelper.getDBName() + " where type ='table' and name ='"
					+ tableName.trim() + "' ";
			Cursor cursor = db.rawQuery(sql, null);
			if (cursor.moveToFirst()) {
				int count = cursor.getInt(0);
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public void insert(T t) {
		mDb.beginTransaction();
//		String sql = SqlBuilder.getInsertSql(mTableInfo);
		ArrayList<One2OneInfo> one2OneInfos = mTableInfo.mOne2OneInfos;
		if (one2OneInfos.size() > 0) {
			for (One2OneInfo one2OneInfo : one2OneInfos) {
				
//				DataBaseProvider<one2OneInfo.one> 
			}
		}
		try {
			mDb.insert(mTableInfo.mTableName, mTableInfo.mPropertyInfos.get(0).columnName, toContentValues(t));
			mDb.setTransactionSuccessful();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			mDb.endTransaction();
		}

	}

	public void insert(List<T> list) {

	}

	public void delete(T t) {

	}

	public void deleteAll() {

	}

	public void update(T t) {

	}

	public T get(Object idValue) {
		return null;
	}

	public boolean checkParams(T t, String mn) {
		if (t == null) {
			Log.v(TAG, String.format("%s method has a null parameter.", mn));
			return false;
		}
		return true;
	}

	private static final Map<Class, CopyOfDataBaseProvider> modelProviders = new HashMap<Class, CopyOfDataBaseProvider>();

	public static <T> CopyOfDataBaseProvider<T> getDbProvider(Class<T> clazz) {
		CopyOfDataBaseProvider<T> provider = modelProviders.get(clazz);
		if (provider == null) {
			provider = new CopyOfDataBaseProvider<T>(clazz);
			modelProviders.put(clazz, provider);
		}
		return provider;
	}
	
	public ContentValues toContentValues(T t) {
		ContentValues values = new ContentValues();
		ArrayList<PropertyInfo> pInfos = mTableInfo.mPropertyInfos;
		String type ;
		String columnName;
		for (PropertyInfo propertyInfo : pInfos) {
			type = propertyInfo.dataType;
			columnName = propertyInfo.columnName;
			propertyInfo.field.setAccessible(true);
			try {
				if (type == TableInfo.MFT_STRING) {
					values.put(columnName, (String) propertyInfo.field.get(t));
				} else if (type == TableInfo.MFT_INTEGER) {
					values.put(columnName, (Integer) propertyInfo.field.get(t));
				} else if (type == TableInfo.MFT_FLOAT) {
					values.put(columnName, (Float) propertyInfo.field.get(t));
				} else if (type == TableInfo.MFT_DATE) {
					values.put(columnName, OrmUtils.toString((Date)propertyInfo.field.get(t)));
				} else if (type == TableInfo.MFT_BOOLEAN) {
					values.put(columnName, (Boolean) propertyInfo.field.get(t));
				}
				
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return values;
	}
}
