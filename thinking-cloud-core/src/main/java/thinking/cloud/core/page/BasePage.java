package thinking.cloud.core.page;

import lombok.Data;

/**
 * 分页对象的基本类型
 * <p>分页对象的基本类型</p> 
 * @author think
 */
@Data
public abstract class BasePage {
	// 页码
	private int pageNo =1;
	// 每页显示的条数
	private int pageSize =5;
}
