package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非空判断 
 * 字段直不为null和empty，且Size>0。
 * 字符串不进行trim()操作 
 * 
 * @author think
 *
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface NotEmpty  {
	/** 验证失败，提示信息*/
	String value();
}
