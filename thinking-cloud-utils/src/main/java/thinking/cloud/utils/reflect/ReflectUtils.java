package thinking.cloud.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectUtils {
	/**
	 * 属性名转方法名
	 * @param prefix 方法名前缀
	 * @param fieldName 属性名
	 * @return 转换后的方法名
	 */
	public static String fieldName2MethodName(String prefix,String fieldName) {
		String firstChar = fieldName.substring(0, 1);
		return prefix+firstChar.toUpperCase()+ fieldName.substring(1);
	}
	
	/**
	 * 方法名转属性名
	 * @param prefix 方法名前缀
	 * @param methodName 方法名
	 * @return 转换后的属性名
	 */
	public static String methodName2fieldName(String prefix,String methodName) {
		methodName = methodName.replaceFirst(prefix, "");
		String firstChar = methodName.substring(0, 1);
		return firstChar.toLowerCase()+methodName.substring(1);
	}
	
    /**
     *  获取类中所有属性
     * @param cl 指定類
     * @param isSupper 是否获取所有父类中的属性
     * @param exculdeFieldNames 排除的属性名
     * @return
     */
    public static List<Field> fields(Class<?> cl,boolean isSupper,String... exculdeFieldNames) {
    	Field[] fields = null;
    	List<Field> resultlist = new ArrayList<>();
    	List<String> exculdeFieldNameList = null;
    	if(exculdeFieldNames!=null && exculdeFieldNames.length > 0) {
    		exculdeFieldNameList = Arrays.asList(exculdeFieldNames);
    	}
    	while(cl!=null) {
    		if(isSupper) {
    			fields = cl.getDeclaredFields();
        		cl = cl.getSuperclass();
    		}else {
    			fields = cl.getDeclaredFields();
    		}
    		for (Field field : fields) {
    			if(exculdeFieldNameList == null || !exculdeFieldNameList.contains(field.getName())) {
    				resultlist.add(field);
        		}
			}
    	}
    	return resultlist;
    }
    
    /**
     * 获取指定对象上父类的泛型
     * @param obj
     * @return
     */
    public static Type[] genericBySuperClass(Object obj) {
    	Type genericSuperclass = obj.getClass().getGenericSuperclass();
    	if(genericSuperclass == Object.class) {
    		throw new RuntimeException("指定对象上找不到有效父类");
    	}else {
    		return ((ParameterizedType)genericSuperclass).getActualTypeArguments();
    	}
    }
    
    
    /**
     * 获取指定对象上父接口的泛型
     * @param obj
     * @return
     */
    @SuppressWarnings({ "rawtypes"})
	public static Type[] genericBySuperClass(Object obj,Class interfaceClass) {
    	Type[] interfaces = obj.getClass().getGenericInterfaces();
    	if(interfaces != null && interfaces.length > 0) {
	    	for (Type type : interfaces) {
				if(type.getClass() == interfaceClass) {
					return ((ParameterizedType)type).getActualTypeArguments();
				}
			}
    	}
    	throw new RuntimeException("指定对象上找不到指定的接口");
    }
}
