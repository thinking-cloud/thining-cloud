package thinking.cloud.core.service.simple;

import java.util.Collection;
import java.util.List;

/**
 * 批量insert数据 的Service
 * @author think
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface InsertCollectionService<P,R> {
	/**
	 * 批量保存
	 */
	public Collection<R> insertCollection(Collection<P> list);
}
