package thinking.cloud.core.service.proxy;

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
public class ServiceProxy {
	@Autowired(required = false)
	private List<ServiceBefore> beforeList;
	@Autowired(required = false)
	private List<ServiceAfter> afterList;
	@Autowired(required = false)
	private List<ServiceAfterReturning> afterRuturningList;
	@Autowired(required = false)
	private List<ServiceAfterThrowing> afterThrowList;
	
	@Pointcut("execution(* *Service.*(*))")
	private void  proxy(){
		
	}
	
	@Before("proxy()")
	public void before(JoinPoint point) {		
		if(beforeList != null && beforeList.size()>0) {
			for (ServiceBefore before : beforeList) {
				before.handler( point);
			}
		}
	}
	

	@AfterReturning(value = "proxy()", returning = "returnVal")
	public void afterRuturning(JoinPoint point, Object returnVal) {
		if(afterRuturningList != null && afterRuturningList.size()>0) {
			for (ServiceAfterReturning runtuning : afterRuturningList) {
				runtuning.handler(point,returnVal);
			}
		}
	}
	@AfterThrowing(value="proxy()", throwing = "throwable")
	public void afterThrow(JoinPoint point,Throwable throwable) {
		if(afterThrowList != null && afterThrowList.size()>0) {
			for (ServiceAfterThrowing throwing : afterThrowList) {
				throwing.handler(point, throwable);
			}
		}
	}
	
	@After("proxy()")
	public void after(JoinPoint point) {
		if(afterList != null && afterList.size()>0) {
			for (ServiceAfter after : afterList) {
				after.handler(point);
			}
		}
	}
}
