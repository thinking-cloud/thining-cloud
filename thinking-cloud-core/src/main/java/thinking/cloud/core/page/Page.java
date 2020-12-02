
package thinking.cloud.core.page;

import java.util.List;

/**
 * 分页对象
 * <p>分页对象</p> 
 * @author think
 * @param <T> 实体泛型
 */
public class Page<T> extends BasePage{
	/** 本页记录 */
	private List<T> records;
	/** 总记录数 */
	private long totalRecords;
	
	/**
	 * 获取本页记录
	 * @return 
	 */
	public List<T> getRecords() {
		return records;
	}
	/**
	 * 设置本页记录
	 * @param records
	 */
	public void setRecords(List<T> records) {
		this.records = records;
	}
	
	/**
	 * 获取记录总数
	 * @return
	 */
	public long getTotalRecords() {
		return totalRecords;
	}
	
	/**
	 * 设置记录总数
	 * @param totalRecordNum
	 */
	public void setTotalRecords(long totalRecords) {
		if(totalRecords <= 0){
			totalRecords = 0;
		}
		this.totalRecords = totalRecords;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public Long getTotalPage() {
		return (getTotalRecords() + getPageSize() -1) /  getPageSize();
	}
}

