package thinking.cloud.validation.annotation;

/**
 * 标记真实的属性链
 * @author think
 */
public @interface FieldLink {
	/**属性链
	 * 默认根据set方法名解析source对象的字段
	 * source的普通属性及对象属性，使用"字段名"，"对象属性.字段名"来解析
	 * model中的非source属性的其他属性，使用"_model对象.字段名"、"_model对象.对象属性.字段名"
	 * */
	String value();;
}
