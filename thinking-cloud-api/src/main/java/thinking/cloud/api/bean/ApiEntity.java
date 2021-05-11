package thinking.cloud.api.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import thinking.cloud.api.annotation.IgnoreSwaggerParameter;
import thinking.cloud.beans.entity.AuthorInfo;
import thinking.cloud.beans.entity.Entity;
import thinking.cloud.beans.entity.Timestamp;

/**
 * 基本的Entity
 * @author zhouxinke
 *
 * @param <PK> 唯一标识泛型
 * @param <U> 操作用户的ID泛型
 */
@Data
public abstract class ApiEntity<PK extends Serializable,U extends Serializable> implements Entity<PK>, Timestamp,AuthorInfo<U> {

	private static final long serialVersionUID = -5208780711395858867L;
	
    @JsonIgnore
    @IgnoreSwaggerParameter
	private PK id;

	@IgnoreSwaggerParameter
	@JsonIgnore
	private U createUserId ;
    @JsonIgnore
    @IgnoreSwaggerParameter
	private Date createTime;
    
	@IgnoreSwaggerParameter
	@JsonIgnore
	private U lastUpdateUserId;
    @JsonIgnore
    @IgnoreSwaggerParameter
	private Date lastUpdateTime;
    
	public ApiEntity() { }
	public ApiEntity(PK id) {
		this.id = id;
	}	
	public ApiEntity(PK id, Date lastUpdateTime) {
		this.id = id;
		this.lastUpdateTime = lastUpdateTime;
	}
	public ApiEntity(PK id, Date createTime, Date lastUpdateTime) {
		this.id = id;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}
}