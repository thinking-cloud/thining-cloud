package thinking.cloud.api.entity;

import java.util.Date;

/**
 * 为model对象添加时间戳属性
 * 
 * @author think
 *
 */
public interface Timestamp {
	/**
	 * 获取创建数据的时间
	 * @return 创建时间
	 */
	public Date getCreateTime();
	
	/**
	 * 最后修改时间
	 * @return 最后修改时间
	 */
	public Date getLastUpdateTime();
	
	public void setLastUpdateTime(Date date);
}
