
package thinking.cloud.api.page;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 分页对象
 * <p>分页对象</p> 
 * @author think
 * @param <T> 实体泛型
 */
@Data
@NoArgsConstructor
public class Page<T> implements BaseLimit {
	private static final long serialVersionUID = 2480254472609326980L;
	/** 当前页码 */
	private Integer pageNo=DEFAULT_PAGE_N0;
	/** 每页显示的条数 */
	private Integer pageSize=DEFAULT_PAGE_SIZE;
	/** 本页记录 */
	private List<T> records;
	/** 总记录数 */
	private Long totalRecord;

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
			synchronized (this){
				if(records == null){
					records = new LinkedList<>();
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
	@ApiModelProperty(value="总记录数",example = "0")
	public long getTotalRecord() {
		if(totalRecord == null) return 0;
		return totalRecord;
	}

	/**
	 * 设置记录总数
	 * @param totalRecord
	 */
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord<=0 ? 0 : totalRecord;
	}

	/**
	 * 获取总页数
	 * @return
	 */
	@ApiModelProperty(value="总页数",example = "0")
	public Long getTotalPage() {
		return (getTotalRecord() + getPageSize() -1) /  getPageSize();
	}
	
	@ApiModelProperty(value="当前页码",example = "0")
	public Integer getPageNo() {
		return pageNo;
	}
	
	@ApiModelProperty(value="每页显示条数",example = "0")
	public Integer getPageSize() {
		return pageSize;
	}
	
}

