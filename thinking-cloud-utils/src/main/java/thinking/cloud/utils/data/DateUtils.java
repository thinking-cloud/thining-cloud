
package thinking.cloud.utils.data;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期相关工具类
 * <p>日期相关工具类</p> 
 * @author think
 */
public class DateUtils {
	
	/**
	 * 日期类型 转为 指定格式的字符串
	 * @param date 日期
	 * @param pattern 目标字符串格式
	 * @return 字符串类型的日期格式
	 */
	public static String  DateFormatString(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	/**
	 * 日期类型 转为 默认格式的字符串
	 * @param date 日期
	 * @return 字符串类型的日期格式
	 */
	public static String DateFormatString(Date date) {
		return DateFormatString(date,"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 日期类型 转为 默认格式的字符串
	 * @param time 时间戳
	 * @return 字符串类型的日期格式
	 */
	public static String DateFormatString(long time) {
		return DateFormatString(new Date(time),"yyyy-MM-dd HH:mm:ss");
	}
	
	/**
	 * 根据指定的字符串格式 转为 日期对象
	 * @param format 日期格式字符串
	 * @param pattern 格式字符串
	 * @return 日期对象
	 */
	public static Date StringPatternDate(String format,String pattern) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.parse(pattern);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
