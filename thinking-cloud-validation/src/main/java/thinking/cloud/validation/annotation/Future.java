package thinking.cloud.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import thinking.cloud.validation.constants.CalendarUnit;

/**
 * 日期判断
 * 字段值必须晚于或等于指定的日期，如果值为null则不进行判断
 * @author think
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface Future {
	/** 验证失败，提示信息*/
	String value();
	/** 指定的参照日期 */
	String futureDate() ;
	/** 参照日期解析格式 */
	String pattern() default "yyyy-MM-dd HH:mm:ss";
	/** 不固定参照日期，以当前实时日期为准进行计算 */
	int future() default 0;
	/** 计算精度，默认为日*/
	CalendarUnit calendarUnit() default CalendarUnit.DAY;
	/** 计算忽略精度，默认为小时*/
	CalendarUnit ignoreUnit() default CalendarUnit.HOUR;
}
