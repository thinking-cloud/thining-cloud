package thinking.cloud.core.proxy;

import thinking.cloud.core.proxy.model.Model;

/**
 * 所有代理的handler的顶级接口
 * @author thinking
 * @date 2021年2月19日
 */
public interface ProxyHandler  {
	public void handler(Model model);
	
	default boolean isHandler(Model model) {
		return true;
	}
}
