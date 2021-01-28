package thinking.cloud.core.proxy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * Controller的统一代理类
 * <P>
 * 	Controller的统一代理类 
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
@Aspect
@Component
public class ControllerProxy {
	@Autowired(required = false)
	private List<ControllerBefore> beforeList;
	@Autowired(required = false)
	private List<ControllerAfter> afterList;
	@Autowired(required = false)
	private List<ControllerAfterReturning> afterRuturningList;
	@Autowired(required = false)
	private List<ControllerAfterThrowing> afterThrowList;
	
	public final static ThreadLocal<HttpServletRequest> httpRequest = new ThreadLocal<>();
	public final static ThreadLocal<HttpServletResponse> httpResponse = new ThreadLocal<>();

	@Pointcut("execution(* *..*Controller..*(*))")
	private void  proxy(){
		
	}
	
	@Before("proxy()")
	public void before(JoinPoint point) {
		if(beforeList != null && beforeList.size()>0) {
			for (
ControllerBefore before : beforeList) {
				before.handler(httpRequest.get(), httpResponse.get(), point);
			}
		}
	}
	

	@AfterReturning(value = "proxy()", returning = "returnVal")
	public void afterRuturning(JoinPoint point, Object returnVal) {
		if(afterRuturningList != null && afterRuturningList.size()>0) {
			for (
ControllerAfterReturning runtuning : afterRuturningList) {
				runtuning.handler(httpRequest.get(), httpResponse.get(), point,returnVal);
			}
		}
	}
	@AfterThrowing(value="proxy()", throwing = "throwable")
	public void afterThrow(JoinPoint point,Throwable throwable) {
		if(afterThrowList != null && afterThrowList.size()>0) {
			for (
ControllerAfterThrowing throwing : afterThrowList) {
				throwing.handler(httpRequest.get(), httpResponse.get(), point, throwable);
			}
		}
	}
	
	@After("proxy()")
	public void after(JoinPoint point) {
		if(afterList != null && afterList.size()>0) {
			for (
ControllerAfter after : afterList) {
				after.handler(httpRequest.get(), httpResponse.get(), point);
			}
		}
		httpRequest.remove();
		httpRequest.remove();
	}
}
