package thinking.cloud.utils.data;

import java.util.List;

import com.google.gson.Gson;

/**
 * Gson的工具类
 * @author think
 *
 */
public class GsonUtils {
	private static final Gson gson = new Gson();
	
	/**
	 * 将目标对象转为json字符串
	 * @param object 目标对象
	 * @return json字符串
	 */
	public static String toJson(Object object) {
		if(object != null) {
			return gson.toJson(object);
		}
		return "null";
	}
}
