package com.oss.common.db.table;


public class One2OneInfo extends PropertyInfo {
	/**
	 * 一对多，集合的实际类的 class  List<T> T.class
	 */
	public Class<?> oneClass;
	public String referencedColumnName;
}
