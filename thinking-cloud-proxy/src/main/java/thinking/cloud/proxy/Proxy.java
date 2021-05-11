package thinking.cloud.proxy;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * 标识为thinking代理的注解
 * 
 * <p> proxyClass 与 basePackage 必须标识一个 </p> 
 * @author think
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Proxy {
	// 标识哪些类的子类执行被标记的代理
	Class[] proxyClass() default Object.class;
	// 标识类中的哪些方法执行被标记的代理
	String[] proxyMethod() default "";
	// 标识哪些包下的代理类执行被标记的代理
	String[] basePackage() default "";
}
