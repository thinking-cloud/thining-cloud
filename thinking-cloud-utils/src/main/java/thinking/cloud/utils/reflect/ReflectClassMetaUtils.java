package thinking.cloud.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author admin
 * @date 2021/1/11 11:07
 */
public class ReflectClassMetaUtils {
    /**
     *  获取类中所有属性
     * @param cl 指定類
     * @param isSupper 是否获取所有父类中的属性
     * @param exculdeFieldNames 排除的属性名
     * @return
     */
    public static List<Field> fields(Class<?> cl, boolean isSupper, String... exculdeFieldNames) {
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
     *  获取类中指定的属性名
     * @param cl 指定類
     * @param isSupper 是否获取所有父类中的属性
     * @param fieldName 指定的属性名
     * @return
     */
    public static Field fields(Class<?> cl,boolean isSupper,String fieldName)  {

        while(cl!=null) {
            try {
                return cl.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                if(isSupper) {
                    cl = cl.getSuperclass();
                    if(cl == Object.class){
                        break;
                    }
                }
            }
        }
        return null;
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
