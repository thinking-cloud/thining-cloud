package thinking.cloud.core.proxy.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thinking.cloud.core.proxy.ThinkingProxy;



/**
 * Service的统一代理类
 * <P>
 * 	Service的统一代理类 
 * </P>
 * @author think
 * @date 2020年12月1日
 */
@Aspect
@Component
public class ServiceProxy extends ThinkingProxy{
	@Autowired(required = false)
	private List<ServiceBefore> beforeList;
	@Autowired(required = false)
	private List<ServiceAfter> afterList;
	@Autowired(required = false)
	private List<ServiceAfterReturning> afterRuturningList;
	@Autowired(required = false)
	private List<ServiceAfterThrowing> afterThrowList;
	
	@Pointcut("within(*..service..*)")
	private void  proxy(){
		
	}
	
	@Before("proxy()")
	public void before(JoinPoint point) {
		super.before(point, beforeList);
	}
	
	@AfterReturning(value = "proxy()", returning = "returnVal")
	public void afterRuturning(JoinPoint point, Object returnVal) {
		super.afterRuturning(point, returnVal, afterRuturningList);
	}
	
	@AfterThrowing(value="proxy()", throwing = "throwable")
	public void afterThrow(JoinPoint point,Throwable throwable) {
		super.afterThrow(point, throwable, afterThrowList);
	}
	
	@After("proxy()")
	public void after(JoinPoint point) {
		super.after(point,afterList);
	}

}
