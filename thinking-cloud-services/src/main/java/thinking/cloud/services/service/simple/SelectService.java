package thinking.cloud.services.service.simple;

/**
 * 查询指定一个数据
 * @author zhouxinke
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface SelectService<P,R> {
	/**
	 * 根据主键查询
	 */
	public R selectByPK(P p);
}
