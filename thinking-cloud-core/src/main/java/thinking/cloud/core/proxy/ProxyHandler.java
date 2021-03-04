package thinking.cloud.core.proxy;

import thinking.cloud.api.entity.Model;

/**
 * 
 * <P>
 * </P>
 * @author thinking
 * @date 2021年2月19日
 */
public interface ProxyHandler  {
	public void handler(Model model);
	
	default boolean isHandler(Model model) {
		return true;
	}
}
