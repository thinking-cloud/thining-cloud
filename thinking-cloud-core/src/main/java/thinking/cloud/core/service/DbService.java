package thinking.cloud.core.service;

import java.io.Serializable;
import java.util.List;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.core.mapper.Mapper;
import thinking.cloud.core.mapper.batch.BatchDeleteMapper;
import thinking.cloud.core.mapper.batch.BatchInsertMapper;
import thinking.cloud.core.mapper.batch.BatchUpdateMapper;
import thinking.cloud.core.mapper.simple.CountMapper;
import thinking.cloud.core.mapper.simple.DeleteMapper;
import thinking.cloud.core.mapper.simple.InsertMapper;
import thinking.cloud.core.mapper.simple.PageMapper;
import thinking.cloud.core.mapper.simple.SelectMapper;
import thinking.cloud.core.mapper.simple.UpdateMapper;
import thinking.cloud.api.page.Limit;
import thinking.cloud.api.page.Page;


/**
 * Service层的基类
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 */
public abstract class DbService<T extends Entity<PK>, PK extends Serializable>  {
	
	/**
	 * 获取mapper接口的实现类
	 * @return
	 */
	protected abstract Mapper<T, PK>  getBaseMapper();
	
	/**
	 * 保存
	 * @param Entity 保存的实体
	 * @return 操作是否成功，影响行数为0，则返回false。
	 */
	public boolean insert(T Entity){
		if(getBaseMapper() instanceof InsertMapper<?, ?>){
			try {
				int number = ((InsertMapper<T,PK>)getBaseMapper()).insert(Entity);
				if(number != 0){
					return false;
				}
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not an InserMapper implement!");
		}
	}
	
	/**
	 * 批量保存
	 * @param list 保存的实体的集合
	 * @return 操作是否成功，影响行数若与list集合的长度不一致，则返回false。
	 */
	public boolean batchInsert(List<T> list){
		if(getBaseMapper() instanceof BatchInsertMapper<?, ?>){
			try {
				int number = ((BatchInsertMapper<T,PK>)getBaseMapper()).batchInsert(list);
				if(number != list.size()){
					return false;
				}
				return true;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{				
			throw new RuntimeException("baseMapper not a BatchInsertMapper implement!");
		}
	}
	
	/**
	 * 删除
	 * @param pk 删除的主键
	 * @return 影响行数
	 */
	public int delete(PK pk){
		if(getBaseMapper() instanceof DeleteMapper<?, ?>){
			try {
				return ((DeleteMapper<T,PK>)getBaseMapper()).delete(pk);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a DeleteMapper implement!");
		}
	}
	
	/**
	 * 批量删除
	 * @param ids 要删除的id列表
	 * @return 影响行数
	 */
	public int batchdelete(List<PK> ids){
		if(getBaseMapper() instanceof BatchDeleteMapper<?, ?>){
			try {
				return ((BatchDeleteMapper<T,PK>)getBaseMapper()).deleteByIds(ids);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a BatchDeleteMapper implement!");
		}
	}
	
	/**
	 * 修改
	 * @param Entity 保存的实体
	 * @return 影响行数
	 */
	public int update(T Entity){
		if(getBaseMapper() instanceof UpdateMapper<?, ?>){
			try {
				return ((UpdateMapper<T, PK>)getBaseMapper()).update(Entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not an UpdateMapper implement!");
		}
	}
	
	/**
	 * 批量修改
	 * @param Entity 修改的实体值
	 * @param ids 要修改的id列表
	 * @return 影响行数
	 */
	public int batchUpdate(T Entity, List<PK> ids){
		if(getBaseMapper() instanceof BatchUpdateMapper<?, ?>){
			try {
				return ((BatchUpdateMapper<T, PK>)getBaseMapper()).updateByIds(Entity, ids);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a BatchUpdateMapper implement!");
		}
	}
	
	/**
	 * 根据主键查询
	 * @param pk 主键
	 * @return 查询结果
	 */
	public T selectByPK(PK pk){
		if(getBaseMapper() instanceof SelectMapper<?, ?>){
			try {
				T Entity = ((SelectMapper<T, PK>)getBaseMapper()).select(pk);
				return Entity;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a SelectMapper implement!");
		}
	}
	
	/**
	 * 分页查询
	 * @param limit 查询件
	 * @return 分页对象
	 */
	public Page<T> queryPage(Limit limit){
		if(getBaseMapper() instanceof PageMapper<?, ?>){
			try {
				Page<T> page = ((PageMapper<T, PK>)getBaseMapper()).queryPage(limit);
				return page;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a PageMapper implement!");
		}
	}
	
	/**
	 * 查询总数
	 * @param limit 查询条件
	 * @return 总数
	 */
	public Long count(Limit limit){
		if(getBaseMapper() instanceof CountMapper<?, ?>){
			try {
				Long count = ((CountMapper<T, PK>)getBaseMapper()).count(limit);
				return count;
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a QueryCountMapper implement!");
		}
	}
}
