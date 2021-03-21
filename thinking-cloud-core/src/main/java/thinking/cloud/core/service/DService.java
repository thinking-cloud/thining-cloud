package thinking.cloud.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.api.entity.Timestamp;
import thinking.cloud.api.page.Limit;
import thinking.cloud.api.page.Page;
import thinking.cloud.core.mapper.Mapper;
import thinking.cloud.core.mapper.simple.CountMapper;
import thinking.cloud.core.mapper.simple.DeleteMapper;
import thinking.cloud.core.mapper.simple.InsertCollectionMapper;
import thinking.cloud.core.mapper.simple.InsertMapper;
import thinking.cloud.core.mapper.simple.PageMapper;
import thinking.cloud.core.mapper.simple.SelectMapper;
import thinking.cloud.core.mapper.simple.UpdateMapper;
import thinking.cloud.core.service.simple.CountService;
import thinking.cloud.core.service.simple.DeleteService;
import thinking.cloud.core.service.simple.InsertCollectionService;
import thinking.cloud.core.service.simple.InsertService;
import thinking.cloud.core.service.simple.LimitService;
import thinking.cloud.core.service.simple.SelectService;
import thinking.cloud.core.service.simple.UpdateService;


/**
 * 操作数据库的默认Service
 * @author think
 * @param <T> 实体泛型
 * @param <PK> 主键泛型
 */
@Service
public interface DService<T extends Entity<PK>, PK extends Serializable> extends 
																				InsertCollectionService<T,T>,
																				InsertService<T, T>,
																				DeleteService<T, Integer>,
																				UpdateService<T, Integer>,
																				SelectService<PK, T>,
																				LimitService<Limit, Page<T>>,
																				CountService<Limit, Long>{

	/**
	 * 获取mapper接口的实现类
	 * @return
	 */
	Mapper<T, PK>  getBaseMapper();
	
	@Override
	default T insert(T entity){
		if(getBaseMapper() instanceof InsertMapper<?, ?>){
			try {
				int number = ((InsertMapper<T,PK>)getBaseMapper()).insert(entity);
				if(number != 0){
					return entity;
				}
				return null;
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
	@Override
	default Collection<T> insertCollection(Collection<T> entitys){
		if(getBaseMapper() instanceof InsertCollectionMapper<?, ?>){
			try {
				int number = ((InsertCollectionMapper<T,PK>)getBaseMapper()).insertCollection(entitys);
				if(number != entitys.size()){
					return null;
				}
				return entitys;
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
	@Override
	default Integer delete(T entity){
		if(getBaseMapper() instanceof DeleteMapper<?, ?>){
			try {
				return ((DeleteMapper<T,PK>)getBaseMapper()).delete(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not a DeleteMapper implement!");
		}
	}
	
	/**
	 * 修改
	 * @param entity 保存的实体
	 * @return 影响行数
	 */
	@Override
	default Integer update(T entity){
		if(getBaseMapper() instanceof UpdateMapper<?, ?>){
			try {
				if(entity instanceof Timestamp) {
					Date lastUpdateTime = ((Timestamp)entity).getLastUpdateTime();
					if(lastUpdateTime == null) {
						((Timestamp)entity).setLastUpdateTime(new Date());
					}
				}
				return ((UpdateMapper<T, PK>)getBaseMapper()).update(entity);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}else{
			throw new RuntimeException("baseMapper not an UpdateMapper implement!");
		}
	}

	
	/**
	 * 根据主键查询
	 * @param pk 主键
	 * @return 查询结果
	 */
	@Override
	default T selectByPK(PK pk){
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
	@Override
	default Page<T> queryPage(Limit limit){
		if(getBaseMapper() instanceof PageMapper<?, ?>){
			try {
				List<T> list = ((PageMapper<T, PK>)getBaseMapper()).queryPage(limit);
				Page<T> page = new Page<>();
				BeanUtils.copyProperties(limit, page);
				page.setRecords(list);
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
	@Override
	default Long count(Limit limit){
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
