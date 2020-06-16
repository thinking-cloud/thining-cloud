package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 长度判断
 * 判断字段值长度要在指定的范围之内。
 * 如果字段值为null，则不进行判断
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Size {
	/** 验证失败，提示信息*/
	String value();
	/** 最小长度值*/
	int min() default 0;
	/** 最大长度直*/
	int max() default Integer.MAX_VALUE;
}
