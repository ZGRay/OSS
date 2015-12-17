package com.oss.common.db.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface One2Many {
	/**mappedBy属性指定“Many”方类引用“One”方类的属性名，*/
	public String referencedColumnName();
//	public Class<?> targetEntity() ;
}
