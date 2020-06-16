package thinking.cloud.core.mapper.simple;

import java.io.Serializable;

import thinking.cloud.core.entity.Entity;
import thinking.cloud.core.mapper.Mapper;

/**
 * 删除数据的接口
 * <p>删除数据的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper 
 */
public interface DeleteMapper <T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK> {
	
	/**
	 * 根据主键删除
	 * @param pk 要删除的主键
	 * @return 影响条数
	 * @throws Exception SQL异常
	 */
	public int delete(PK pk) ;
}
