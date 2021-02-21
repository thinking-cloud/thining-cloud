package thinking.cloud.core.proxy.adapter;

import org.aspectj.lang.JoinPoint;

import io.swagger.models.Model;
import thinking.cloud.core.proxy.ProxyHandler;

/**
 * Adapter最终通知的接口
 * <P>
 * Adapter最终通知的接口
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public interface AdapterAfter extends ProxyHandler{

	public void handler(Model model);
}
