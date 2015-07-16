package com.oss.common.db.table;

import java.lang.reflect.Field;

public class PrimaryKeyInfo extends PropertyInfo {

	public PrimaryKeyInfo(Field field,String fieldName, String columnName, String fieldType) {
		this.field = field;
		this.fieldName = fieldName;
		this.columnName = columnName;
		this.dataType = fieldType;
	}

	public boolean isAutoIncrement;
}
