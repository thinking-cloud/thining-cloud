package thinking.cloud.core.service.batch;

import java.util.List;

/**
 * 批量insert数据 的Service
 * @author think
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface BatchInsertService<P,R> {
	/**
	 * 批量保存
	 */
	public List<R> batchInsert(List<P> list);
}
