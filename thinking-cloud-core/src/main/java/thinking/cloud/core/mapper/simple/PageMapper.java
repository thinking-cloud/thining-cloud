package thinking.cloud.core.mapper.simple;

import java.io.Serializable;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;
import thinking.cloud.api.page.Limit;
import thinking.cloud.api.page.Page;

/**
 * 分页查询的Mapper
 * <p>分页查询的Mapper</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 */
public interface PageMapper <T extends Entity<PK>, PK extends Serializable> extends Mapper<T, PK>{
	/**
	 * 分页查询
	 * @param Entity 分页查询对象
	 * @return 分页数据
	 * @throws Exception SQL异常
	 */
	public Page<T> queryPage(Limit Entity);
	
}
