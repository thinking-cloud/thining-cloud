package thinking.cloud.beans.entity;

/**
 * 操作数据的用户信息
 * @author zhouxinke
 * @date 2021年2月24日
 */
public interface AuthorInfo<U> {
	
	/**
	 * 获取创建用户id
	 * @return
	 */
	public U getCreateUserId() ;
	/**
	 * 设置创建用户id
	 * @param author
	 */
	public void setCreateUserId(U createUserId); 
	
	/**
	 * 获取最后修改用户id
	 * @return
	 */
	public U getLastUpdateUserId(); 
	
	/**
	 * 设置最后修改用户id
	 * @param updateUserId
	 */
	public void setLastUpdateUserId(U updateUserId);
	
	
}
