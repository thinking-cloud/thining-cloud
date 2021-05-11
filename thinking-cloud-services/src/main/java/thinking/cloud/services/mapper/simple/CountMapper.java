/*
 * All rights Reserved, Designed by www.thinking-cloud.net
 * 文件名:CountMapper.java
 * 作者：think
 * 创建日期:2018年10月25日 下午12:13:00
 */
package thinking.cloud.services.mapper.simple;

import java.io.Serializable;

import thinking.cloud.beans.entity.Entity;
import thinking.cloud.beans.page.Limit;
import thinking.cloud.services.mapper.Mapper;

/**
 * 查询总数的mapper
 * <p>查询总数的mapper</p>
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 * @see Mapper
 */
public interface CountMapper <T extends Entity<PK>,PK extends Serializable> extends Mapper<T, PK>{
	/**
	 * 查询总数
	 * @param limit 查询条件
	 * @return 总数
	 * @throws Exception SQK异常
	 */
	public Long count(Limit limit);
}
