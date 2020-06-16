package thinking.cloud.core.page;

import thinking.cloud.core.entity.Entity;

/**
 * 分页查询条件
 * <p>分页查询条件</p> 
 * @author think
 * @param <T> 查询条件泛型
 * @param <PK> 主键泛型
 */
public class Limit<T extends Entity<?>> extends BasePage {
	//查询条件
	private T condition;
	/**
	 * 获取查询条件
	 * @return 查询条件
	 */
	public T getCondition() {
		return condition;
	}
	/**
	 * 设置查询条件
	 * @param condition 查询条件
	 */
	public void setCondition(T condition) {
		this.condition = condition;
	}

	/**
	 * 设置当前页码
	 * @param pageIndex
	 */
	public void setPageNo(int pageNo) {
		if(pageNo<=0){
			pageNo=1;
		}
		super.setPageNo(pageNo);
	}

	/**
	 * 设置每页显示的条数， 默认每页5条
	 * @return
	 */
	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			pageSize = 5;
		}
		super.setPageSize(pageSize);
	}
	
	/**
	 * 获取每页第一条，在数据库中的位置
	 * @return
	 */
	public long getStartIndex() {
		return (getPageNo() - 1) * getPageSize();
	}
}
