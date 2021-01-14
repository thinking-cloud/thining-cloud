
package thinking.cloud.core.convert;

import java.util.List;

import org.springframework.core.convert.converter.Converter;

import thinking.cloud.api.entity.Entity;
import thinking.cloud.api.page.Limit;
import thinking.cloud.api.page.Page;
import thinking.cloud.api.vo.VO;

/**
 * limit对象转为page对象
 * <p>limit对象转为page对象</p> 
 * @author think
 * @param <T> Entity泛型
 * @param <V> vo泛型
 */
public class LimitToPageConvert<T,V extends VO<T>> implements Converter<T, V> {

	private EntityToVoConvert<T, V> EntityToVoConvert;
	public LimitToPageConvert(Class<V> voClass) {
		this.EntityToVoConvert = new EntityToVoConvert<>(voClass);
	}
	
	@Override
	public V convert(T source) {
		return this.EntityToVoConvert.convert(source);
	}
	
	/**
	 * limit对象转为page对象
	 * @param limit limit查询对象
	 * @param list 查询结果
	 * @return page对象
	 */
	public Page<V> limitToPage(Limit limit,List<T> list){
		Page<V> page = new Page<>();
		page.setPageNo(limit.getPageNo());
		page.setPageSize(limit.getPageSize());
		page.setRecords(this.EntityToVoConvert.convert(list));
		return page;
	}

}
