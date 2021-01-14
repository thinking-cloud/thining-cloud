package thinking.cloud.core.adapter;

import java.io.Serializable;

import thinking.cloud.core.convert.EntityToVoConvert;
import thinking.cloud.core.convert.LimitToPageConvert;
import thinking.cloud.api.entity.Entity;
import thinking.cloud.api.vo.VO;

/**
 * adapter层的基类
 * @author think
 * @param <T> 实体泛型
 * @param <V> VO泛型
 */
public class BaseAdapter<T extends Serializable, V extends VO<T>> {
	/**
	 * 创建默认 实体类转VO类的Convert
	 */
	protected  EntityToVoConvert<T,V> entityConvert(Class<V> voClass){
	    return new EntityToVoConvert<>(voClass);
    }

	/**
	 * 创建默认 Limit类转page类的Convert
	 */
    protected  LimitToPageConvert<T,V> limitConvert(Class<V> voClass){
		return new LimitToPageConvert<>(voClass);
	}
}
