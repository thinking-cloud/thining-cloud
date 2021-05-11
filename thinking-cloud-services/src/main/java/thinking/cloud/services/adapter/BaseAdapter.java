package thinking.cloud.services.adapter;

import java.io.Serializable;
import java.lang.reflect.Type;

import thinking.cloud.beans.vo.VO;
import thinking.cloud.services.converts.EntityToVoConvert;
import thinking.cloud.services.converts.LimitToPageConvert;
import thinking.cloud.utils.reflect.ReflectUtils;

/**
 * adapter层的基类
 * @author think
 * @param <T> 实体泛型
 * @param <V> VO泛型
 */
public abstract class BaseAdapter<T extends Serializable, V extends VO<T>> {
	/**
	 * 创建默认 实体类转VO类的Convert
	 */
	@SuppressWarnings("unchecked")
	protected  EntityToVoConvert<T,V> entityConvert(){
		Type[] types = ReflectUtils.genericBySuperClass(this);
		return new EntityToVoConvert<T, V>((Class<V>)types[1]);
    }

	/**
	 * 创建默认 Limit类转page类的Convert
	 */
	@SuppressWarnings("unchecked")
	protected  LimitToPageConvert<T,V> limitConvert(){
		Type[] types = ReflectUtils.genericBySuperClass(this);
    	return new LimitToPageConvert<>((Class<V>)types[1]);
	}
}
