package com.oss.common.db.table;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
public class One2OneInfo extends PropertyInfo {
	/**
	 * 一对多，集合的实际类的 class  List<T> T.class
	 */
	public Class<?> oneClass;
	public String referencedColumnName;
}
