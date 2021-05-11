package thinking.cloud.services.service.simple;

/**
 * 分页查询的Service接口
 * @author zhouxinke
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface LimitService<P,R> {
	/**
	 * 分页查询
	 */
	public R queryPage(P p);
}
