package thinking.cloud.core.mapper.simple;

import java.io.Serializable;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;

/**
 * 根据主键查询数据
 * <p>根据主键查询数据</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface SelectMapper<T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK> {
	/**
	 * 根据主键查询
	 * @param pk 主键
	 * @return 查询到的对象
	 */
	public T select(PK id);
}

