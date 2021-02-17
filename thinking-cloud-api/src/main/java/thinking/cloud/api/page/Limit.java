package thinking.cloud.api.page;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModelProperty;
import thinking.cloud.api.annotation.IgnoreSwaggerParameter;

/**
 * 分页查询条件
 * <p>分页查询条件</p> 
 * @author think
 */
public interface Limit extends BaseLimit { 

	
	ThreadLocal<Integer> threadLocal_pageNo =new ThreadLocal<>();
	ThreadLocal<Integer> threadLocal_pageSize =new ThreadLocal<>();
	ThreadLocal<Long> threadLocal_totalRecord = new ThreadLocal<>();
	
	/**
	 * 获取当前页码
	 * @return
	 */
	default Integer getPageNo() {
		Integer pageNo = threadLocal_pageNo.get();
		return pageNo == null || pageNo<=0 ? DEFAULT_PAGE_N0:pageNo;
	}

	/**
	 * 设置当前页码
	 * @param pageNo
	 */
	default void setPageNo(int pageNo) {
		threadLocal_pageNo.set(pageNo<=0 ? DEFAULT_PAGE_N0 : pageNo);
	}

	/**
	 * 获取每页显示的条数
	 * @return
	 */
	default Integer getPageSize() {
		Integer pageSize = threadLocal_pageSize.get();
		return pageSize == null || pageSize <= 0 ? DEFAULT_PAGE_SIZE : pageSize;
	}

	/**
	 * 设置每页显示的条数
	 * @param pageSize
	 */
	default void setPageSize(int pageSize) {
		threadLocal_pageSize.set(pageSize <= 0? DEFAULT_PAGE_SIZE : pageSize);
	}
	
	default void setTotalRecord(long total) {
		threadLocal_totalRecord.set(total);
	}
	
	@IgnoreSwaggerParameter
	@JsonIgnore
	@ApiModelProperty(value="总条数",example = "0")
	default long getTotalRecord() {
		return threadLocal_totalRecord.get();
	}

	/**
	 * 获取数据起始索引
	 * @return
	 */
	@IgnoreSwaggerParameter
	@JsonIgnore
	@ApiModelProperty(value="起始索引",example = "0")
	default int getStartIndex(){
		return (getPageNo() - 1) * getPageSize();
	}

	default void cleanUp() {
		threadLocal_pageNo.remove();
		threadLocal_pageSize.remove();
		threadLocal_totalRecord.remove();
	}
	
	
}
