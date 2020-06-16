package thinking.cloud.core.model;

import java.io.Serializable;

import thinking.cloud.core.entity.Entity;
import thinking.cloud.validation.Validation;
import thinking.cloud.validation.ValidationModel;


/**
 * 接受请求参数的model类
 * @author think
 *
 * @param <T> 实体类型
 * @param <PK> 主键类型
 */
public abstract class Model<T extends Entity<PK>, PK extends Serializable> implements Entity<PK>, ValidationModel<T> {
	
	private static final long serialVersionUID = 8546624997191714360L;
	/** 验证model */
	private Validation vaildationUtils = new Validation();
	/** 源实体对象 */
	protected T source;
	
	/**
	 * 构造方法
	 * @param source 源实体对象
	 */
	public Model(T source) {
		this.source = source;
	}
	
	public void setId(PK id) {
		this.source.setId(id);
	}
	
	public PK getId() {
		return this.source.getId();
	}
	

	public T source() {
		return source;
	}
	
	public Validation vaildation() {
		return vaildationUtils;
	}
	
	/**
	 * 判断验证结果
	 * 有验证失败信息，抛出异常
	 */
	public void vaild() {
		
	}
}
