package com.oss.common.util;

import android.app.Application;
import android.content.Context;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
public class Gapplication extends Application {
	public Context mAppContext;
	public static Gapplication instance;;
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mAppContext = this.getApplicationContext();
		instance = this;
	}
	
	public  static synchronized Gapplication getInstance(){
		return instance;
	}

}
