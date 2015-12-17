package com.oss.common.db.table;

import com.oss.common.db.anotation.Id;

/**
 * * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
public class TableVersion {

	@Id
	public String tableName;
	public int versionCode;
	public String versionName;
	
}
