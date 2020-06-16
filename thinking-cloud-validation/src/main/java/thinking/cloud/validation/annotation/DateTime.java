package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 日期格式判断
 * 判断字段值是否为指定的日期格式，如果值为null则不进行判断
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface DateTime {
	/** 验证失败，提示信息*/
	String value();
	/** 日期格式 */
	String pattern();
}
