package thinking.cloud.proxy.controller;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
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
	private List<ControllerBefore> beforeProxyList;
	@Autowired(required = false)
	private List<ControllerAfter> afterProxyList;
	@Autowired(required = false)
	private List<ControllerAfterReturning> ruturnProxyList;
	@Autowired(required = false)
	private List<ControllerAfterThrowing> throwProxyList;
	

	@Pointcut("within(*..controller..*)")
	private void  proxy(){

	}
	
	@Around("proxy()")
	public Object around(ProceedingJoinPoint point) {
		return super.around(point, beforeProxyList, ruturnProxyList, throwProxyList, afterProxyList);
	}

}
