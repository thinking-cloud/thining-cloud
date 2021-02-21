package thinking.cloud.core.proxy.service;

import org.aspectj.lang.JoinPoint;

import io.swagger.models.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * service层前置通知的接口
 * <P>
 * service层前置通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ServiceBefore extends ProxyHandler{
	
	public void handler(Model model);
}
