package thinking.cloud.api.page;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页顶级接口
 * @author zhouxinke
 * @date 2021年2月16日
 */
public interface BaseLimit extends Serializable {
	int DEFAULT_PAGE_N0 = 1;
	int DEFAULT_PAGE_SIZE = 25;
	
	/**
	 * 获取当前页码
	 * @return
	 */
	public Integer getPageNo() ;

	/**
	 * 设置当前页码
	 * @param pageNo
	 */
	public void setPageNo(int pageNo);

	/**
	 * 获取每页显示的条数
	 * @return
	 */
	
	public Integer getPageSize() ;

	/**
	 * 设置每页显示的条数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize);
	
	public void setTotalRecord(long total) ;
	
	public long getTotalRecord() ;
}
