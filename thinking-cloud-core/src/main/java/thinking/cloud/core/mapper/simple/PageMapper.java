package thinking.cloud.core.mapper.simple;

import java.io.Serializable;
import java.util.List;

import thinking.cloud.core.entity.Entity;
import thinking.cloud.core.mapper.Mapper;
import thinking.cloud.core.page.Limit;

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
	public List<T> queryPage(Limit<T> Entity);
	
}
