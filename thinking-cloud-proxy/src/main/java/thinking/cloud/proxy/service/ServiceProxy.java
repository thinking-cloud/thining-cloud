package thinking.cloud.proxy.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import thinking.cloud.proxy.ThinkingProxy;



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
	private List<ServiceBefore> beforeProxyList;
	@Autowired(required = false)
	private List<ServiceAfter> afterProxyList;
	@Autowired(required = false)
	private List<ServiceAfterReturning> ruturnProxyList;
	@Autowired(required = false)
	private List<ServiceAfterThrowing> throwProxyList;
	
	@Pointcut("within(*..service..*)")
	private void  proxy(){
		
	}
	
	@Around("proxy()")
	public Object around(ProceedingJoinPoint point) {
		return super.around(point, beforeProxyList, ruturnProxyList, throwProxyList, afterProxyList);
	}

}
