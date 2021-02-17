package thinking.cloud.api.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import thinking.cloud.api.annotation.IgnoreSwaggerParameter;

/**
 * 所有model类都要实现的接口
 * <p>所有实体类都要实现的接口</p> 
 * @author think
 * @param <PK> 唯一标识的类型
 */
public interface Entity<PK extends Serializable> extends Serializable {
	/**
	 * 获取唯唯一标识
	 * @return 唯一标识
	 */
    @JsonIgnore
    @IgnoreSwaggerParameter
	public PK getId();
	
	/**
	 * 设置唯一标识
	 * @param id 唯一标识
	 */
	public void setId(PK id);
			
}
