package thinking.cloud.beans.page;

import java.io.Serializable;

/**
 * 分页顶级接口
 * @author zhouxinke
 * @date 2021年2月16日
 */
public interface Limit extends Serializable {
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
	
	/**
	 * 设置总页数 
	 * - 内部插件使用
	 * @param total
	 */
	public void setTotalRecord(long total) ;
	
	/**
	 * 获取总页数 
	 * - 内部插件使用
	 * @return
	 */
	public Long getTotalRecord() ;
}
