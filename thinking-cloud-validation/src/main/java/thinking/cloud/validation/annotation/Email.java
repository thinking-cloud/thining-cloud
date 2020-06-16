package thinking.cloud.validation.annotation;
/**
 * 判断字段值是否为email格式。如果值为null则不进行判断
 * @author think
 */
public @interface Email {
	/** 验证失败，提示信息*/
	String value();
}
