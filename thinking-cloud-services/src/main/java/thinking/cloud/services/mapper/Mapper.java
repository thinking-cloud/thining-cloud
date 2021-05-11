
package thinking.cloud.services.mapper;

import java.io.Serializable;

import thinking.cloud.beans.entity.Entity;

/**
 * 所有mapper接口的基类
 * <p>所有mapper接口的基类</p> 
 * @author think
 * @param <T> 实体的泛型 //thinking.cloud.beans.entity
 * @param <PK> 主键的泛型
 */
public interface Mapper<T extends Entity<PK>,PK extends Serializable> {

}
