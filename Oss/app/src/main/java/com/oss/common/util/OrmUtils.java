package com.oss.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.oss.common.db.anotation.Column;
import com.oss.common.db.anotation.Id;
import com.oss.common.db.anotation.Many2One;
import com.oss.common.db.anotation.One2Many;
import com.oss.common.db.anotation.One2One;
import com.oss.common.db.anotation.Table;
import com.oss.common.db.anotation.Transient;
import com.oss.common.db.table.Many2OneInfo;
import com.oss.common.db.table.One2ManyInfo;
import com.oss.common.db.table.One2OneInfo;
import com.oss.common.db.table.TableInfo;
import com.oss.common.db.table.TableVersion;
import com.oss.common.exception.DbException;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

public class OrmUtils {
	/**
	 * 转换类名为表名
	 * 
	 * @param lastCName
	 * @return
	 */
	public static String camel2underline(String lastCName) {
		// TODO Auto-generated method stub
		char[] myChars = lastCName.trim().toCharArray();
		char[] result = new char[myChars.length * 2];
		boolean needUnderline = false;
		int idx = 0;
		for (char myChar : myChars) {
			if (myChar >= 'A' && myChar <= 'Z') {
				if (needUnderline) {
					result[idx++] = '_';
					needUnderline = false;
				}
				result[idx++] = (char) (myChar - 'A' + 'a');
			} else {
				result[idx++] = myChar;
				needUnderline = true;
			}
		}
		return new String(result, 0, idx);
	}

	/**
	 * 获取类名
	 * 
	 * @param calssName
	 *            class.getName() java.lang.String
	 * @return
	 */
	public static String getLastCName(String calssName) {
		// TODO Auto-generated method stub
		int idx = calssName.lastIndexOf(".");
		return idx == -1 ? calssName : calssName.substring(idx + 1);
	}
	
	/**
	 * 判断是否是数据库支持的类型
	 * 
	 * @param str
	 * @param array
	 *            类型库
	 * @return
	 */
	public static boolean isInStrArray(String str, String[] array) {
		if (TextUtils.isEmpty(str)) {
			return false;
		}
		for (int i = 0; i < array.length; i++) {
			if (str.equals(array[i])) {
				return true;
			}
		}
		return false;
	}
	
	public static TableVersion getAppVertion(Context context) {
	    PackageInfo pi = null;
	 
	    try {
	        PackageManager pm = context.getPackageManager();
	        pi = pm.getPackageInfo(context.getPackageName(),
	                PackageManager.GET_CONFIGURATIONS);
	        TableVersion tVersion = new TableVersion();
	        tVersion.versionName = pi.versionName;
	        tVersion.versionCode = pi.versionCode;
	        return tVersion;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	 
	    return null;
	}
	
	/**
	 * 把 java字段类型 转换为数据库对应的类型
	 * 
	 * @param type
	 * @return
	 */
	public static String transJava2ModelType(String type) {
		if (OrmUtils.isInStrArray(type, TableInfo.INTEGER_JAVA_TYPES)) {
			return TableInfo.MFT_INTEGER;
		} else if (OrmUtils.isInStrArray(type, TableInfo.STRING_JAVA_TYPES)) {
			return TableInfo.MFT_STRING;
		} else if (OrmUtils.isInStrArray(type, TableInfo.FLOAT_JAVA_TYPES)) {
			return TableInfo.MFT_FLOAT;
		} else if (OrmUtils.isInStrArray(type, TableInfo.BOOLEAN_JAVA_TYPES)) {
			return TableInfo.MFT_BOOLEAN;
		} else if (OrmUtils.isInStrArray(type, TableInfo.DATE_JAVA_TYPES)) {
			return TableInfo.MFT_DATE;
		} else {
			return null;
		}
	}
	
	public static String getColumnName(Field field) {
		Column filedAno = field.getAnnotation(Column.class);
		if (filedAno != null && !TextUtils.isEmpty(filedAno.columnName())) {
			return filedAno.columnName();
		}
		Id idAno = field.getAnnotation(Id.class);
		if (idAno != null && !TextUtils.isEmpty(idAno.columnName())) {
			return idAno.columnName();
		}
		One2One o2oAno = field.getAnnotation(One2One.class);
		if (o2oAno != null && !TextUtils.isEmpty(o2oAno.referencedColumnName())) {
			return OrmUtils.camel2underline(o2oAno.referencedColumnName());
		}
		One2Many o2mAno = field.getAnnotation(One2Many.class);
		if (o2mAno != null && !TextUtils.isEmpty(o2mAno.referencedColumnName())) {
			return OrmUtils.camel2underline(o2mAno.referencedColumnName());
		}
		Many2One m2oAno = field.getAnnotation(Many2One.class);
		if (m2oAno != null && !TextUtils.isEmpty(m2oAno.referencedColumnName())) {
			return  OrmUtils.camel2underline(m2oAno.referencedColumnName());
		}
		
		return OrmUtils.camel2underline(field.getName());
	}
	
	public static boolean isOne2OneProperty(Field field, ArrayList<One2OneInfo> one2OneInfos) {
		// TODO Auto-generated method stub
		One2One oMany = field.getAnnotation(One2One.class);
		if (oMany != null) {
			One2OneInfo oneInfo = new One2OneInfo();
			oneInfo.referencedColumnName = OrmUtils.getColumnName(field);
			oneInfo.field = field;
			oneInfo.fieldName = field.getName();
			oneInfo.dataClassTYpe = field.getType();
			oneInfo.oneClass = field.getType();
			one2OneInfos.add(oneInfo);
			return true;
		}
		return false;
	}

	public static boolean isMany2OneProperty(Field field, ArrayList<Many2OneInfo> many2OneInfos) {
		// TODO Auto-generated method stub
		Many2One many2One = field.getAnnotation(Many2One.class);
		if (many2One != null) {
			Many2OneInfo m2oInfo = new Many2OneInfo();
			m2oInfo.referencedColumnName = OrmUtils.getColumnName(field);
			m2oInfo.field = field;
			m2oInfo.fieldName = field.getName();
			m2oInfo.dataClassTYpe = field.getType();
			m2oInfo.oneClass = field.getType();
			many2OneInfos.add(m2oInfo);
			return true;
		}
		return false;
	}

	public static boolean isOne2ManyProperty(Field field, ArrayList<One2ManyInfo> one2Manies) {
		// TODO Auto-generated method stub
		One2Many one2Many = field.getAnnotation(One2Many.class);
		if (one2Many != null) {
			One2ManyInfo oInfo = new One2ManyInfo();
			String fieldName = field.getName();
			oInfo.referencedColumnName = OrmUtils.getColumnName(field);
			oInfo.fieldName = fieldName;
			oInfo.field = field;
			oInfo.dataClassTYpe = field.getType();
			/**
			 * 返回一个 Type 对象，它表示此 Field 对象所表示字段的声明类型。 如果 Type 是一个参数化类型，则返回的 Type
			 * 对象必须准确地反映源代码中使用的    实际类型参数。
			 */
			Type type = field.getGenericType();
			/**是否是参数化类型， List<T>   map<t,t>*/
			if (type instanceof ParameterizedType) {
				ParameterizedType pType = (ParameterizedType) type;
				
				if (pType.getActualTypeArguments().length == 1) {
					Class<?> clazz= (Class<?>) pType.getActualTypeArguments()[0];
					if (clazz != null) {
						oInfo.manyClass = clazz;
					}
				}else{
					Class<?> clazz= (Class<?>) pType.getActualTypeArguments()[1];
					if (clazz != null) {
						oInfo.manyClass = clazz;
					}
				}
				
			}else{
				throw new DbException("One2Many Exception : "+ fieldName +"不是参数化 类型 ");
			}
			
			one2Manies.add(oInfo);
			return true;
		}
		return false;
	}
	
	/**
	 * 是否过滤此字段不生成数据库字段
	 * 
	 * @param field
	 * @return
	 */
	public static boolean isIgnoreFiled(Field field) {
		// TODO Auto-generated method stub
		return field.getAnnotation(Transient.class) == null ? false : true;
	}
	
	public static String toString(Date date) {
		SimpleDateFormat SDF = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ssZ");
		if (date == null) {
			return null;
		}
		return SDF.format(date);
	}
	
}
