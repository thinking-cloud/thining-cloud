package thinking.cloud.core.mapper.batch;

import java.io.Serializable;
import java.util.List;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;

/**
 * 根据多个id批量删除数据
 * <p>根据条件批量删除数据的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface BatchDeleteMapper <T extends Entity<PK>,PK extends Serializable>  extends Mapper<T, PK> {	
	/**
	 * 根据多个id批量删除数据
	 * @param entity 删除条件
	 * @return 影响条数
	 * @throws Exception SQL异常
	 */
	public int deleteByEntity(T entity) ;
}
