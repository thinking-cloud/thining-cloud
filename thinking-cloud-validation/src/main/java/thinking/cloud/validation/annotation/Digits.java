package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 判断整型字段值 
 * 字段值必须在指定的区间内，如果值为null则不进行判断
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Digits {
	/** 验证失败，提示信息*/
	String value();
	/** 最小值 */
	long min() default 0;
	/** 最大值 */
	long max() default Integer.MAX_VALUE;
}
