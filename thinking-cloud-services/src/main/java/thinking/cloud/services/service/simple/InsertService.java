package thinking.cloud.services.service.simple;

/**
 * 插入数据的Service 接口
 * @author zhouxinke
 *
 * @param <P> 形参泛型
 * @param <R> 返回值泛型
 */
public interface InsertService<P,R> {
	/**
	 * 保存
	 * @param entity 保存的实体
	 * @return 保存的实体
	 */
	public R insert(P entity);
}
