package thinking.cloud.core.proxy.adapter;

import org.aspectj.lang.JoinPoint;

import thinking.cloud.core.proxy.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * Adapter异常通知的接口
 * <P>
 * Adapter异常通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface AdapterAfterThrowing extends ProxyHandler{
	public void handler(Model model);
}
