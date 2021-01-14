package thinking.cloud.api.page;

import io.swagger.annotations.ApiModelProperty;
import thinking.cloud.api.entity.Entity;

/**
 * 分页查询条件
 * <p>分页查询条件</p> 
 * @author think
 */
public interface Limit  {
	int DEFAULT_PAGE_N0 = 1;
	int DEFAULT_PAGE_SIZE = 25;
	/**
	 * 获取当前页码
	 * @return
	 */
	@ApiModelProperty(value="页码",example = DEFAULT_PAGE_N0+"")
	int getPageNo();

	/**
	 * 设置当前页码
	 * @param pageNo
	 */
	void setPageNo(int pageNo);

	/**
	 * 获取每页显示的条数
	 * @return
	 */
	@ApiModelProperty(value="每页显示条数",example = DEFAULT_PAGE_SIZE+"")
	int getPageSize();

	/**
	 * 设置每页显示的条数
	 * @param pageSize
	 */
	void setPageSize(int pageSize);

	/**
	 * 获取数据起始索引
	 * @return
	 */
	default long getStartIndex(){
		return (getPageNo() - 1) * getPageSize();
	}
}
