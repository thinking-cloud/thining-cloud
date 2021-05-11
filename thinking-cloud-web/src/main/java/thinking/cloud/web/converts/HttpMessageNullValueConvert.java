package thinking.cloud.web.converts;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import thinking.cloud.utils.reflect.ReflectBeanUtils;
import thinking.cloud.utils.reflect.ReflectUtils;

/**
 * 序列化时处理null的Convert
 * @author thinking
 */
public class HttpMessageNullValueConvert extends JsonSerializer<Object> {

	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider serializers) throws IOException,
			JsonProcessingException {
		try {
			Object current = jgen.getCurrentValue();
			Class clazz = current.getClass();
			String currentName = jgen.getOutputContext().getCurrentName();
			Method method = clazz.getMethod(ReflectBeanUtils.fieldName2MethodName("get", currentName));
			Class<?> returnType = method.getReturnType();
			
			if(String.class.isAssignableFrom(returnType)){// 字符串null值处理
				jgen.writeObject("");
			}else if(Collection.class.isAssignableFrom(returnType)){ //集合null值处理
				jgen.writeObject(new ArrayList<Object>(0));
			}else if(returnType.getName().indexOf("[")>=0){ // 数组类型null处理
				jgen.writeObject(new Object[0]);
			}else if(Number.class.isAssignableFrom(returnType)) { //基本数据类型
				jgen.writeNumber(0);
			}else if(Boolean.class.isAssignableFrom(returnType)) { //boolean类型处理
				jgen.writeBoolean(false);
			}
			else if(Object.class.isAssignableFrom(returnType)) { //对象类型
				jgen.writeObject(new Serializable() {});
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
