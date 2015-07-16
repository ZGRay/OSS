package com.oss.common.db.table;

import com.oss.common.db.anotation.Id;

public class TableVersion {

	@Id
	public String tableName;
	public int versionCode;
	public String versionName;
	
}
