package thinking.cloud.services.mapper.ext;

import java.io.Serializable;

import thinking.cloud.beans.entity.Entity;
import thinking.cloud.services.mapper.simple.PageMapper;

/**
 * 常用的CRUD及分页查询组合
 * <p>常用的CRUD及分页查询组合</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see SimpleMapper
 * @see PageMapper
 */
public interface NormalMapper<T extends Entity<PK>, PK extends Serializable> 
	extends SimpleMapper<T, PK>, PageMapper<T, PK>{

}
