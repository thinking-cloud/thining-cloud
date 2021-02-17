package thinking.cloud.core.service.batch;

import java.io.Serializable;

/**
 * 批量删除
 * @author zhouxinke
 * 
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface BatchDeleteService<P,R> {
	/**
	 * 批量删除
	 */
	public R batchdelete(P p);
}
