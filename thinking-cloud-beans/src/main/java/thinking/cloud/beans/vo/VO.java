package thinking.cloud.beans.vo;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

/**
 * 响应给调用者vo
 * <p>响应给调用者vo</p> 
 * @author think
 * @param <T> 实体类型 
 */
public abstract class VO<T> implements Serializable {
	private static final long serialVersionUID = -5665832561306399527L;
	
	protected T source;

	@SuppressWarnings("unchecked")
	public <V extends VO<T>> V convert(T source) {
		if(source != null) { 
			this.source=source;
			convert();
			return (V)this;
		}else {
			return null;
		}
	}
	
	protected void convert() {
		BeanUtils.copyProperties(source, this);
	}
}