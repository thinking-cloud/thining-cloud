package thinking.cloud.api.entity;

/**
 * 操作数据的用户信息
 * @author zhouxinke
 * @date 2021年2月24日
 */
public interface AuthorInfo {
	
	/**
	 * 获取创建用户id
	 * @return
	 */
	public String getCreateUserId() ;
	/**
	 * 设置创建用户id
	 * @param author
	 */
	public void setCreateUserId(String createUserId); 
	
	/**
	 * 获取最后修改用户id
	 * @return
	 */
	public String getLastUpdateUserId(); 
	
	/**
	 * 设置最后修改用户id
	 * @param updateUserId
	 */
	public void setLastUpdateUserId(String updateUserId);
	
	
}
