package com.oss.common.db.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
	/**数据库对应的字段名称*/
	public String columnName() default "";
	/**变量类型*/
	public String columnType() ;
	
}
