package thinking.cloud.utils.reflect;

/**
 * @author admin
 * @date 2021/1/11 11:05
 */
public class ReflectBeanUtils {
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
     * 表名转类名
     * @param prefix 前置忽略字符
     * @param split 分隔字符
     * @param tableName 表明
     * @return
     */
    public static String tableName2ClassName(String prefix, String split,String tableName) {
    	if(prefix != null && !prefix.trim().equals("")) {
    		tableName = tableName.replace(prefix, "");
    	}
    	String[] tableNameSplit = tableName.split(split);
    	StringBuilder className = new StringBuilder();
    	String fisrt = null;
    	String other = null;
    	for (String tns : tableNameSplit) {
			fisrt = tns.substring(0,1).toUpperCase();
			other = tns.substring(1);
			className.append(fisrt).append(other);
		}
    	return className.toString();
    }
    
    /**
     * 列名转属性名
     * <p>属-性-名-id, 其中-id不忽略</p>
     * @return
     */
    public static String columnName2FieldName(String prefix, String split, String fieldName) {
    	if(prefix != null && !prefix.trim().equals("")) {
    		fieldName = fieldName.replace(prefix, "");
    	}
    	String[] splitFieldName = fieldName.split(split);
    	StringBuilder className = new StringBuilder();
    	String fisrt = null;
    	String other = null;
    	for (int i = 0; i < splitFieldName.length-1; i++) {
    		String tfn = splitFieldName[i];
			if(i==0) {
				className.append(tfn);
			}else {
				fisrt = tfn.substring(0,1).toUpperCase();
				other = tfn.substring(1);
				className.append(fisrt).append(other);
			}
		}
    	return className.toString();
    }
}
