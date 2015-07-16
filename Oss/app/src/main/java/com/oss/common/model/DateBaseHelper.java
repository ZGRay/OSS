package com.oss.common.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DateBaseHelper extends SQLiteOpenHelper {

	public DateBaseHelper(Context context){
		super(context, getDBName(),  new SugarCursorFactory(true), getVersion());
	}
	
	/**
	 * 获取数据库版本号
	 * @return
	 */
	private static int getVersion() {
		// TODO Auto-generated method stub
		return 1;
	}

	/**
	 * 获取数据库名称
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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

	private static DateBaseHelper dateBaseHelper =null;
	/**
	 * 获取数据库实例
	 * @param context
	 * @return
	 */
	public static DateBaseHelper getInstance(Context context){
		if (dateBaseHelper == null){
			synchronized (DateBaseHelper.class){
				if (dateBaseHelper == null){
					dateBaseHelper = new DateBaseHelper(context);
				}
			}

		}
		return dateBaseHelper;
	}


}
