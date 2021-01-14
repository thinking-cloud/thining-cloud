package thinking.cloud.core.mapper.simple;

import java.io.Serializable;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;

/**
 * 插入数据的接口
 * <p>插入数据的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper 
 */
public interface InsertMapper<T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK>  {
	/**
	 * 插入数据
	 * @param Entity 插入的数据对象
	 * @return 影响条数
	 * @throws SQL异常
	 */
	public int insert(T Entity);
}
