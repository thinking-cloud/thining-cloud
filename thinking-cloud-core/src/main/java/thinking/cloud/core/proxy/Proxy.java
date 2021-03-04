package thinking.cloud.core.proxy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 标识为thinking代理的注解
 * <p>标识为thinking代理的注解</p> 
 * @author think
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Proxy {
	
	Class[] proxyClass() default Object.class;
	String[] proxyMethod() default "";
	String[] basePackage() default "";
}
