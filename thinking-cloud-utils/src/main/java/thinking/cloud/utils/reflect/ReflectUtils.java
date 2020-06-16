package thinking.cloud.utils.reflect;

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
}
