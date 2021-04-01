package thinking.cloud.api.entity.abs;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import thinking.cloud.api.annotation.IgnoreSwaggerParameter;
import thinking.cloud.api.constant.STATE;
import thinking.cloud.api.entity.AuthorInfo;
import thinking.cloud.api.entity.Entity;
import thinking.cloud.api.entity.LogicalDeletion;
import thinking.cloud.api.entity.Timestamp;
@Data
public abstract class BaseEntity<PK extends Serializable> implements Entity<PK>, Timestamp,AuthorInfo {

	private static final long serialVersionUID = -5208780711395858867L;
	
    @JsonIgnore
    @IgnoreSwaggerParameter
	private PK id;

	@IgnoreSwaggerParameter
	@JsonIgnore
	private String createUserId ;
    @JsonIgnore
    @IgnoreSwaggerParameter
	private Date createTime;
    
	@IgnoreSwaggerParameter
	@JsonIgnore
	private String lastUpdateUserId;
    @JsonIgnore
    @IgnoreSwaggerParameter
	private Date lastUpdateTime;
    
	public BaseEntity() { }
	public BaseEntity(PK id) {
		this.id = id;
	}	
	public BaseEntity(PK id, Date lastUpdateTime) {
		this.id = id;
		this.lastUpdateTime = lastUpdateTime;
	}
	public BaseEntity(PK id, Date createTime, Date lastUpdateTime) {
		this.id = id;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}
}