package thinking.cloud.validation.annotation;

/**
 * 数字类型判断
 * 字段值必须为数字类型，如果值为null则不进行判断
 * @author think
 *
 */
public @interface Number {
	/** 验证失败，提示信息*/
	String value();
}
