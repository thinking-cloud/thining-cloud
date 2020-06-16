package thinking.cloud.core.adapter;

import java.io.Serializable;

import thinking.cloud.core.entity.Entity;

/**
 * adapter层的基类
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 */
public class BaseAdapter<T extends Entity<PK>, PK extends Serializable> {
	
}
