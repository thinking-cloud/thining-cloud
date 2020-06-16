package thinking.cloud.validation.entity;

import lombok.Data;

/**
 * 模型验证结果，实体类
 * @author think
 */
@Data
public class ModelValidationResult {
	//属性名
	private String name;
	//验证失败消息
	private String msg;
	
	/**
	 * 构造方法
	 * @param name 书姓名
	 * @param msg 验证失败，提示消息
	 */
	public ModelValidationResult(String name,String msg) {
		this.name = name;
		this.msg = msg;
	}
}