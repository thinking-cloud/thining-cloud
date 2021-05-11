package thinking.cloud.services.mapper.simple;

import java.io.Serializable;

import thinking.cloud.beans.entity.Entity;
import thinking.cloud.services.mapper.Mapper;
/**
 * 修改数据的接口
 * <p>修改数据的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface UpdateMapper <T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK> {
	/**
	 * 根据主键修改数据
	 * @param t 查询条件与修改的值
	 * @return 影响行数
	 * @throws Exception SQL异常
	 */
	public int update(T t);

}
