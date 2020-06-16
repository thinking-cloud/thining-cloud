package thinking.cloud.core.mapper.ext;

import java.io.Serializable;

import thinking.cloud.core.entity.Entity;
import thinking.cloud.core.mapper.simple.DeleteMapper;
import thinking.cloud.core.mapper.simple.InsertMapper;
import thinking.cloud.core.mapper.simple.SelectMapper;
import thinking.cloud.core.mapper.simple.UpdateMapper;

/**
 * 含有简单CRUD的接口
 * <p>含有简单CRUD的接口</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see InsertMapper
 * @see DeleteMapper
 * @see UpdateMapper
 * @see SelectMapper
 */
public interface SimpleMapper<T extends Entity<PK>, PK extends Serializable> 
		extends InsertMapper<T, PK>, DeleteMapper<T, PK> , UpdateMapper<T, PK>,SelectMapper<T, PK>{

}
