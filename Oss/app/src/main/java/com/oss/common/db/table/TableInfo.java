package com.oss.common.db.table;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.text.TextUtils;

import com.oss.common.db.anotation.Column;
import com.oss.common.db.anotation.Id;
import com.oss.common.db.anotation.Table;
import com.oss.common.exception.DbException;
import com.oss.common.util.OrmUtils;

/**
 * class反射类文件。可以把它当为一个类。包含了类字段，与数据库字段的绑定。只是通过反射的方式生成的。
 *
 * @author Lei.Zhang
 * @email marvylei@aliyun.com
 * Created by Lei.Zhang on 2015/7/21.
 *
 */
public class TableInfo {

	/**
	 * 类的集合。所有与数据库绑定的类，都放在这个集合里。
	 */
	public static Map<String, TableInfo> schemaService = new HashMap<String, TableInfo>();
	/** 类 映射的表名 */
	public String mTableName;
	/** 类 */
	public Class<?> mClazz;
	/** 主键 */
	public PrimaryKeyInfo mPrimaryKey;
	/**
	 * 类包含的所有字段。这里存放的是所有public的字段。私有的并未获取。
	 */
	public ArrayList<PropertyInfo> mPropertyInfos = new ArrayList<PropertyInfo>();
	/** 一对多集合 */
	public ArrayList<One2ManyInfo> mOne2Manies = new ArrayList<One2ManyInfo>();

	public ArrayList<One2OneInfo> mOne2OneInfos = new ArrayList<One2OneInfo>();

//	public ArrayList<Many2OneInfo> mMany2OneInfos = new ArrayList<Many2OneInfo>();

	/**
	 * 数据模型支持的数据类型
	 */
	public static final String MFT_INTEGER = "Integer";
	public static final String MFT_FLOAT = "Float";
	public static final String MFT_STRING = "String";
	public static final String MFT_BOOLEAN = "Boolean";
	public static final String MFT_DATE = "Date";

	/**
	 * 模型数据类型对应的Java数据类型
	 */
	public static final String[] INTEGER_JAVA_TYPES = { "int", "java.long.Integer", "short", "java.lang.Short", "long",
			"java.lang.Long" };
	public static final String[] STRING_JAVA_TYPES = { "java.lang.String", "char", "java.lang.Character" };
	public static final String[] FLOAT_JAVA_TYPES = { "java.lang.Float", "float", "double", "java.lang.Double" };
	public static final String[] BOOLEAN_JAVA_TYPES = { "boolean", "java.lang.Boolean" };
	public static final String[] DATE_JAVA_TYPES = { "java.util.Date" };
	/**
	 * 模型数据类型对应的SQLite数据类型
	 */
	public final static Map<String, String> Type4SQL = new HashMap<String, String>();
	static {
		Type4SQL.put(MFT_INTEGER, "integer");
		Type4SQL.put(MFT_FLOAT, "real");
		Type4SQL.put(MFT_STRING, "text");
		Type4SQL.put(MFT_BOOLEAN, "text");
		Type4SQL.put(MFT_DATE, "text");
	}

	public TableInfo(Class<?> modelClass) {
		this.mClazz = modelClass;
		buildTableInfo(modelClass);
		bulildFieldInfo(modelClass);
		// TODO build setter getter
	}

	/**
	 * 构建类字段与数据库字段的匹配
	 * 
	 * @param modelClass
	 */
	private void bulildFieldInfo(Class<?> modelClass) {
		// TODO Auto-generated method stub
		this.mPropertyInfos.clear();
		// 获取 声明本类的字段。 不会获取父类的字段
		Field[] fields = modelClass.getDeclaredFields();

		boolean isFindKey = false;
		for (Field field : fields) {
			/** 查看当前字段是否是过滤的字段 */
			if (OrmUtils.isIgnoreFiled(field)) {
				continue;
			}
			/** 查看当前字段是否是主键 todo 循环结束 判断是否找到主键，找不到 throw exception */
			if (!isFindKey) {
				// 获取主键
				if (isPrimaryKey(field,this.mPropertyInfos)) {
					isFindKey = true;
					continue;
				}
			}
			/** 查看当前字段是否是一对多的字段。 */
			if (OrmUtils.isOne2ManyProperty(field, this.mOne2Manies)) {
				continue;
			}
			if (OrmUtils.isOne2OneProperty(field, this.mOne2OneInfos)) {
				continue;
			}
//			if (OrmUtils.isMany2OneProperty(field, this.mMany2OneInfos)) {
//				continue;
//			}

			/** 查看当前字段是否是 表的字段，是，附属到当前表中。 《字段只能是基本类型》 */
			checkProperty(field, this.mPropertyInfos);

		}

		if (!isFindKey) {
			throw new DbException("[" + mClazz + "]'s Primary is null , \n 你可以使用 annotation @Id，或声明为 Id or _Id，标记主键");
		}
	}

	private void checkProperty(Field field, ArrayList<PropertyInfo> fields2) {
		// TODO Auto-generated method stub
		// Column filedAno = field.getAnnotation(Column.class);
		String fieldType = getFieldType(field);
		/** 为空 不是 基本类型 抛异常 */
		if (TextUtils.isEmpty(fieldType)) {
			throw new DbException(String.format("Cann't translate type: %s", field.getType().getName()));
			// return;
		}

		String fieldName = field.getName();
		String colomnName = OrmUtils.getColumnName(field);
		fields2.add(new PropertyInfo(field, fieldName, colomnName, fieldType));
	}

	public static String getFieldType(Field field) {
		Column colAno = field.getAnnotation(Column.class);
		if (colAno != null && !TextUtils.isEmpty(colAno.columnType())) {
			return colAno.columnType();
		}
		Id idAno = field.getAnnotation(Id.class);
		if (idAno != null && !TextUtils.isEmpty(idAno.columnType())) {
			return idAno.columnType();
		}

		return OrmUtils.transJava2ModelType(field.getType().getName());
	}

	/**
	 * 是否市住建
	 * 
	 * @param field
	 * @param fields
	 * @return
	 */
	private boolean isPrimaryKey(Field field, ArrayList<PropertyInfo> fields) {
		// TODO Auto-generated method stub
		Id idAno = field.getAnnotation(Id.class);
		String fieldName = field.getName();
		if (idAno != null) {
			mPrimaryKey = new PrimaryKeyInfo(field, fieldName, OrmUtils.getColumnName(field), getFieldType(field));
			if (idAno.isAutoIncrement()) {
				if (!mPrimaryKey.dataType.equals(TableInfo.MFT_INTEGER)) {
					throw new DbException("primary key  [" + fieldName + "] type is " + field.getType()
							+ ", not autoIncrement  type");
				}
			}
			mPrimaryKey.isAutoIncrement = idAno.isAutoIncrement();
			return true;
		}
		if ("id".equals(fieldName) || "_id".equals(fieldName)) {
			mPrimaryKey = new PrimaryKeyInfo(field, fieldName, OrmUtils.getColumnName(field), getFieldType(field));
			return true;
		}

		return false;
	}

	/**
	 * 构建表名 注：类映射的表，类名不要相同。不支持。
	 * 
	 * @param modelClass
	 */
	private void buildTableInfo(Class<?> modelClass) {
		// TODO Auto-generated method stub
		Table table = modelClass.getAnnotation(Table.class);
		this.mTableName = ((table == null) || TextUtils.isEmpty(table.name())) ? OrmUtils.camel2underline(OrmUtils
				.getLastCName(modelClass.getName())) : table.name();
		// 如果有类名相同的存在请使用这个方法
		// this.tableName = getTableName(modelClass);

	}

	public static TableInfo instance(Class<?> modelClass) {
		TableInfo modelSchema = null;
		String key = modelClass.getName();
		if (schemaService.containsKey(key)) {
			return schemaService.get(key);
		} else {
			modelSchema = new TableInfo(modelClass);
			schemaService.put(key,modelSchema);
		}
		return modelSchema;
	}

	public String[] getColumns() {
		String[] result = new String[this.mPropertyInfos.size()];
		int idx = 0;
		for (PropertyInfo field : this.mPropertyInfos) {
			result[idx++] = field.columnName;
		}
		return result;
	}

	public String[] getFieldNames() {
		String[] result = new String[this.mPropertyInfos.size()];
		int idx = 0;
		for (PropertyInfo field : this.mPropertyInfos) {
			result[idx++] = field.fieldName;
		}
		return result;
	}

	/**
	 * 获取对应的数控库字段类型。
	 * 
	 * @return
	 */
	public String[] getColumnFieldTypes() {
		// TODO Auto-generated method stub
		String[] result = new String[this.mPropertyInfos.size()];
		int idx = 0;
		for (PropertyInfo field : this.mPropertyInfos) {
			result[idx++] = field.dataType;
		}
		return result;
	}

	public static String getTableName(Class<?> clazz) {
		Table table = clazz.getAnnotation(Table.class);
		String tableName = ((table == null) || TextUtils.isEmpty(table.name())) ? clazz.getName().replace('.', '_')
				: table.name();
		return tableName;
	}
}
