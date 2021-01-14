
package thinking.cloud.api.page;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页对象
 * <p>分页对象</p> 
 * @author think
 * @param <T> 实体泛型
 */
@Data
public class Page<T> {
	/** 当前页码 */
	private int pageNo;
	/** 每页显示的条数 */
	private int pageSize;
	/** 本页记录 */
	private List<T> records;
	/** 总记录数 */
	private long totalRecords;

	/**
	 * 设置页码
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo<=0 ? 1 : pageNo;
	}

	/**
	 * 设置每页显示的条数
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize<=0 ? 10 : pageSize;
	}

	/**
	 * 获取本页记录
	 * @return
	 */
	public List<T> getRecords() {
		if(records == null){
			synchronized (Page.class){
				if(records == null){
					records = new ArrayList<>();
				}
			}
		}
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
	 * @param totalRecords
	 */
	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords<=0 ? 0 : totalRecords;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	public Long getTotalPage() {
		return (getTotalRecords() + getPageSize() -1) /  getPageSize();
	}
}

