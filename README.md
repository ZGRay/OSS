此orm实现了，以app版本号来控制表的更新。无需更改数据库版本号，无需关心表的更新操作。（目前只支持新增的字段添加，删除字段没有必要实现）
1.实现了联级 增删改查。


 @Table       来标注表名，不标注 默认 类名
 @Id          标注主键，columnName columnType ，默认为空，使用字段名表示数据库字段名。isAutoIncrement，是否自增，默认false，不自增
	          不标注，属性命名必须为 id 或 _id
			  
 @Column      标注字段，columnName columnType 默认为空，使用字段名表示数据库字段名。
			  不标注 使用字段名表示数据库字段名
 @Transient   g过滤此字段，不会生成数据库字段。
 @One2One     标注 一对一的关系，也就是单个实体类。必须标注。referencedColumnName。关联表外键字段名
 @One2Many    标注 一对多的关系，也就是集合类。必须标注。referencedColumnName。关联表外键字段名
 
 
