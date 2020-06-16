package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 浮点型数字值闭区间判断
 * 字段值必须在指定的区间内，如果值为null则不进行
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface DecimalDigits {
	/** 验证失败，提示信息*/
	String value();
	/** 最小值 */
	double min() default 0;
	/** 最大值 */
	double max() default Integer.MAX_VALUE;
}
