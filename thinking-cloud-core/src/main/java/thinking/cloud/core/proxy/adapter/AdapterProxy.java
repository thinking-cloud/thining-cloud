package thinking.cloud.core.proxy.adapter;

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
 * Adapter的统一代理类
 * <P>
 * 	Adapter的统一代理类 
 * </P>
 * @author think
 * @date 2020年12月1日
 */
@Aspect
@Component
public class AdapterProxy extends ThinkingProxy{
	@Autowired(required = false)
	private List<AdapterBefore> beforeList;
	@Autowired(required = false)
	private List<AdapterAfter> afterList;
	@Autowired(required = false)
	private List<AdapterAfterReturning> afterRuturningList;
	@Autowired(required = false)
	private List<AdapterAfterThrowing> afterThrowList;
	
	@Pointcut("within(*..adapter..*)")
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
