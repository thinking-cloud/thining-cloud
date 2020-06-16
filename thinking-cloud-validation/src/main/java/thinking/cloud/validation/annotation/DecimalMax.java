package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 浮点型数字最大值判断
 * 字段值要小于等于指定值. 如果值为null则不进行判断
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface DecimalMax {
	/** 验证失败，提示信息*/
	String value();
	/** 指定的最大值 */
	double max()  ;
}
