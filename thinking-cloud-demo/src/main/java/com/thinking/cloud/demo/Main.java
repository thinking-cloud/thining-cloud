package com.thinking.cloud.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2020年12月1日
 */
public class Main {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		
		Type genericSuperclass = new B().getClass().getGenericSuperclass();
    	if(genericSuperclass == Object.class) {
    		throw new RuntimeException("指定对象上找不到父类");
    	}else {
    		((ParameterizedType)genericSuperclass).getActualTypeArguments();
    	}
	}

}
class A<T>{
	
}
class B {
	
}
