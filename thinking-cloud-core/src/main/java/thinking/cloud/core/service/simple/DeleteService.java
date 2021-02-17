package thinking.cloud.core.service.simple;

import java.io.Serializable;

/**
 * 删除指定数据
 * @author zhouxinke
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface DeleteService<P,R> {
	/**
	 * 删除指定数据
	 */
	public R delete(P p);
}
