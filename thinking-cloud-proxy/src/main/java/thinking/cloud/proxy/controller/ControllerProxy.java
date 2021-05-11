package thinking.cloud.proxy.controller;

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

import thinking.cloud.proxy.ThinkingProxy;

/**
 * Controller的统一代理类
 * <P>
 * 	Controller的统一代理类 
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
@Aspect
@Component
public class ControllerProxy extends ThinkingProxy{
	@Autowired(required = false)
	private List<ControllerBefore> beforeList;
	@Autowired(required = false)
	private List<ControllerAfter> afterList;
	@Autowired(required = false)
	private List<ControllerAfterReturning> afterRuturningList;
	@Autowired(required = false)
	private List<ControllerAfterThrowing> afterThrowList;
	

	@Pointcut("within(*..controller..*)")
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
