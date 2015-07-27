package com.oss.common.db.table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/**
 * 类字段 绑定。field. columnName,getter ,setter
 * @author sunby.zhang
 *
 */
public class PropertyInfo {

	public Field field;
	public String fieldName;
	public String columnName;
	public String dataType;
	public Class<?> dataClassType;
	public Method setter;
	public Method getter;
	
	
	public PropertyInfo() {
		super();
	}

	public PropertyInfo(Field field,String fieldName, String columnName, String fieldType) {
		this.field = field;
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.dataType = fieldType;
	}

	public PropertyInfo(Field field, String columnName, String fieldType,
			Method setter, Method getter) {
		this.field = field;
		this.columnName = columnName;
		this.dataType = fieldType;
		this.setter = setter;
		this.getter = getter;
	}
	
	
	
}
