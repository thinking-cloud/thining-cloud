package thinking.cloud.core.service.simple;

/**
 * 修改指定数据
 * @author zhouxinke
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface UpdateService<P,R> {
	/**
	 * 修改
	 */
	public R update(P p);
}
