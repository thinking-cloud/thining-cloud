package thinking.cloud.validation;

/**
 * 使model具有验证功能
 * @author think
 */
public interface ValidationModel<T> {
	/**
	 * 获取验证工具类
	 * @return
	 */
	public Validation vaildationObject();
	
	/**
	 * 获取源实体对象
	 * @return 源实体对象
	 */
	public T source();
}
