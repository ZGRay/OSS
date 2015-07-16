package com.oss.common.db.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * 标记主键
 * @author sunby.zhang
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
	/**数据库对应的字段名称*/
	public String columnName() default "";
	/**变量类型*/
	public String columnType() default "" ;
	
	public boolean isAutoIncrement() default false; 
}
