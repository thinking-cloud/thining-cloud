package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非空判断: 
 * 只判断字段直不为null，但是可以为empty
 * 
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface NotNull  {
	/** 验证失败，提示信息*/
	String value();
}
