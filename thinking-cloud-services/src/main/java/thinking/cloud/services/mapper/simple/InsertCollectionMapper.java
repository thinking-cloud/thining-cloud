package thinking.cloud.services.mapper.simple;

import java.io.Serializable;
import java.util.Collection;

import thinking.cloud.beans.entity.Entity;
import thinking.cloud.services.mapper.Mapper;

/**
 * 批量添加数据的接口
 * <p>批量添加数据的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface InsertCollectionMapper<T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK> {
	
	/**
	 * 批量添加
	 * @param list 对量添加的对象
	 * @return 影响条数
	 * @throws Exception SQL异常
	 */
	public int insertCollection(Collection<T> entitys);
}
