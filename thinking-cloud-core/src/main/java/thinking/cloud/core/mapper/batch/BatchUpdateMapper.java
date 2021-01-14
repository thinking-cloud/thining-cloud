package thinking.cloud.core.mapper.batch;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;
/**
 * 根据条件批量修改数据接口
 * <p>根据条件批量修改数据接口</p>
 * @author zhouxinke
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface BatchUpdateMapper <T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK> {
	
	/**
	 * 根据条件修改数据
	 * @param Entity 数据 
	 * @param condition 修改条件
	 * @return 影响行数
	 * @throws Exception SQL异常
	 */
	public int updateByIds(@Param("Entity") T Entity, @Param("list") List<PK> ids) ;

}
