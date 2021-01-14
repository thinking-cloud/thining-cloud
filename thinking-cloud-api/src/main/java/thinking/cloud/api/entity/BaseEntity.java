package thinking.cloud.api.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public abstract class BaseEntity<PK extends Serializable> implements Entity<PK>, Timestamp {
	private static final long serialVersionUID = -5208780711395858867L;
	
	private PK id;
	private Date createTime;
	private Date lastUpdateTime;
}