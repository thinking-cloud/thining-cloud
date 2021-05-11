package thinking.cloud.services.service.simple;

/**
 * 查询总数
 * @author zhouxinke
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface CountService<P,R> {
	/**
	 * 查询总数
	 * @param limit 查询条件
	 * @return 总数
	 */
	public R count(P limit);
}
