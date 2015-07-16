package com.oss.common.util;

import android.app.Application;
import android.content.Context;

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
