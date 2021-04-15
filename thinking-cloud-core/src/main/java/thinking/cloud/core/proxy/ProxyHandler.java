package thinking.cloud.core.proxy;

import thinking.cloud.core.proxy.model.Model;

/**
 * 所有代理的handler的顶级接口
 * @author thinking
 * @date 2021年2月19日
 */
public interface ProxyHandler extends Comparable<ProxyHandler>{
	
	/**
	 * 值越小,越靠前
	 * @return
	 */
	default int getSort() {
		return 10;
	}
	
	public void handler(Model model);
	
	default boolean isHandler(Model model) {
		return true;
	}
	
	default int compareTo(ProxyHandler o) {
		return this.getSort() - o.getSort() == 0?1:this.getSort() - o.getSort();
	}
}
