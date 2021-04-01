package thinking.cloud.api.entity;

/**
 * 标识这个对象是一个逻辑删除
 * @author thinking
 */
public interface LogicalDeletion {
	/**
	 * 获取数据删除状态
	 * @return
	 */
	public String getDelState() ;
	
	/**
	 * 设置数据删除状态
	 * @param state
	 */
	public void setDelState(String state);
}
