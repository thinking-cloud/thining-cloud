package thinking.cloud.core.proxy.service;

import org.aspectj.lang.JoinPoint;

import io.swagger.models.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * Service最终通知的接口
 * <P>
 * Service最终通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface ServiceAfter extends ProxyHandler{
	public void handler(Model model);
}
