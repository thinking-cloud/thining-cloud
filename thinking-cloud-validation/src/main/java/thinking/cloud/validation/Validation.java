package thinking.cloud.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import thinking.cloud.utils.data.DateUtils;
import thinking.cloud.utils.data.GsonUtils;

import lombok.Data;
import thinking.cloud.validation.annotation.Null;
import thinking.cloud.validation.entity.ModelValidationResult;
import thinking.cloud.validation.exception.UnexpectedTypeException;
import thinking.cloud.validation.exception.ValidationFailedException;

/**
 * model类验证数据的工具类
 * @author think
 */
@Data
public class Validation {
	
	// 验证失败，提示信息集合
	private List<ModelValidationResult> failedMsgList = new ArrayList<>();
	
	private String notNullMessage = "%s Cannot be null";
	private String notEmptyMessage = "%s Cannot be empty or null";
	private String notBlankMessage = "%s Cannot be empty or null";
	private String sizeMessage = "%s length must be greater than %s and less than %s";
	private String isNullMessage = "%s must be null";
	private String isNumberMessage = "%s must be a number";
	private String minMessage =  "%s must be greater than or equal to %s";
	private String maxMessage = "%s must be less than or equal to %s";
	private String digitsMessage = "%s must be greater than or equal to %s and less than or equal to %s";
	private String pastMessage = "%s must be before %s";
	private String futureMessage = "%s must be after %s";
	private String patternMessage = "%s format is incorrect";
	
	/**
	 * 非空判断 
	 * 只判断字段值不为null，但是可以为empty
	 * @param name 字段名
	 * @param value 字段值
	 */
	public void notNull(String name,Object value) {
		vaild(value!=null,this.notNullMessage,name);
	}
	
	/**
	 * 非空判断 
	 * 字段值不为null和empty，且Size>0。字符串不进行trim()操作
	 * @param name 字段名 
	 * @param value 字段值
	 */
	public void notEmpty(String name, Object value) {
		boolean status = false;
		if(value!=null) {
			if(value instanceof String) {
				status = !value.toString().isEmpty();
			}else if(value instanceof Collection) {
				status = !((Collection<?>) value).isEmpty();
			}else if(value instanceof Map){
				status = !((Map<?,?>) value).isEmpty();
			}else if(value instanceof Array) {
				status = Array.getLength(value) > 0;
			}else {
				throw new UnexpectedTypeException("Unexpected data type "+value.getClass()+" cannot be processed");
			}
		}
		vaild(status,this.notEmptyMessage,name);
	}
	
	/**
	 * 非空判断
	 * 只用于String,不能为null。且trim()之后size>0
	 * @param name 字段名
	 * @param value 字段值
	 */
	public void notBlank(String name,String value) {
		boolean status = false;
		if(value!=null) {
			if(value instanceof String) {
				status = ((String) value).trim().length() > 0;
			}else {
				throw new UnexpectedTypeException("Unexpected data type "+value.getClass()+" cannot be processed");
			}
			
		}
		vaild(status, this.notBlankMessage, name);
	}
	
	/**
	 * 长度判断
	 * 判断字段值长度要在指定的范围之内。如果字段值为null，则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param min 最小字段值长度
	 * @param max 最大字段值长度
	 */
	public void size(String name, Object value, int min, int max){
		if(value ==null) 
			return;
		int len = 0;
		if(value instanceof String) {
			len = value.toString().length();
		}else if(value instanceof Array) {
			len = Array.getLength(value);
		}else if(value instanceof Collection<?>) {
			len = ((Collection<?>) value).size();
		}
		vaild(len>=min && len<=max, sizeMessage, name,min,max);
	}
		
	/**
	 * 为空判断
	 * 判断值必须为null
	 * @param name 字段名
	 * @param value 字段值
	 */
	public void isNull(String name, Object value) {
		vaild(value==null, this.isNullMessage,name);
	}
	
	/**
	 * 数字类型判断
	 * 字段值必须为数字类型，如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @return 判断结果，如果字段值为null，返回true
	 */
	public boolean isNumber(String name,Object value) {
		if(value ==null) return true;
		try {
			Double.valueOf(value.toString());
			return true;
		}catch (NumberFormatException e) {
			vaild(false, this.isNumberMessage,name);
			return false;
		}
	}

	/**
	 * 整型数字最小值判断
	 * 字段值要大于等于指定值. 如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param min 指定最小值
	 */
	public  void min(String name,Object value,long min) {
		Long v = toLong(name, value);
		if(v==null) return;
		vaild(v>=min, this.minMessage, name, min);
	}
	
	/**
	 * 整型数字最大值判断
	 * 字段值要小于等于指定值. 如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param max 指定最大值
	 */
	public void max(String name,Object value,long max) {
		Long v = toLong(name, value);
		if(v==null) return;
		vaild(v<=max,this.maxMessage,name,max);
		
	}
	
	/**
	 * 整型数字值闭区间判断
	 * 字段值必须在指定的区间内，如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param min 指定的最小值
	 * @param max 指定的最大值
	 */
	public void digits(String name,Object value,long min,long max) {
		Long v = toLong(name, value);
		if(v==null) return;
		vaild(v>=min && v<=max, name, this.digitsMessage,min,max);
	}
	
	/**
	 * 浮点型数字最小值判断
	 * 字段值要大于等于指定值. 如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param min 指定的最小值
	 */
	public void decimalMin(String name, Object value,BigDecimal min) {
		Double v = toDouble(name, value);
		if(v==null) return;
		BigDecimal bdv = new BigDecimal(v);
		vaild(bdv.compareTo(min) >= 0, this.minMessage,name,min);
	}

	/**
	 * 浮点型数字最大值判断
	 * 字段值要小于等于指定值. 如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param max 指定的最大值
	 */
	public  void decimalMax(String name, Object value, BigDecimal max) {
		Double v = toDouble(name, value);
		if(v==null) return;
		BigDecimal bdv = new BigDecimal(v);
		vaild(bdv.compareTo(max) <= 0, this.maxMessage,name,max);
	}
	
	/**
	 * 浮点型数字值区间判断	
	 * 字段值必须在指定的区间内，如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param min 最小值
	 * @param max 最大值
	 */
	public void decimalDigits( String name,Object value,BigDecimal min, BigDecimal max) {
		Double v = toDouble(name, value);
		if(v==null) return;
		BigDecimal bdv = new BigDecimal(v);
		vaild(bdv.compareTo(min)>=0 && bdv.compareTo(max)<=0, name, this.digitsMessage,min,max);
	}
	
	/**
	 * 日期格式判断
	 * 判断字段值是否为指定的日期格式，如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param pattern 日期格式
	 * @return 判断结果，如果字段值为null，返回true
	 */
	public boolean isDateTime(String name,String value,String pattern) {
		if(value==null) return true;
		try {
			new SimpleDateFormat(pattern).parse(value);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 日期判断
	 * 字段值必须早于或等于指定的日期，如果值为null则不进行判断
	 * @param name 字段名	
	 * @param value 字段值
	 * @param past 指定的日期
	 * @param pattern 日期展示时的日期格式
	 */
	public void past(String name, Date value, Date past, String pattern) {
		if(value==null) return ;
		vaild(value.compareTo(past)<=0, this.pastMessage, name, DateUtils.DateFormatString(past, pattern));
	}
	
	/**
	 * 日期判断
	 * 字段值必须晚于等于指定的日期，如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param future 指定日期
	 * @param pattern 日期展示时的日期格式
	 */
	public void future(String name,Date value, Date future,String pattern) {
		if(value==null) return ;
		vaild(value.compareTo(future)>=0, this.pastMessage, name, DateUtils.DateFormatString(future, pattern));
	}
		
	/**
	 * 通过正则表达式，判断字符串格式。如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 * @param expr 正则表达式
	 */
	public void pattern(String name,String value,String expr) {
		if(value!=null) { 
			vaild(Pattern.matches(expr, value),this.patternMessage, name);
		}
	}
	
	/**
	 * 判断字段值是否为email格式。如果值为null则不进行判断
	 * @param name 字段名
	 * @param value 字段值
	 */
	public void isEmail(String name,String value) {
		pattern(name, value, "^.+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");
	}
	
	/**
	 * 生成验证结果，当result为false时，才会抛出验证失败信息
	 * @param result 验证结果
	 * @param message 验证失败，提示信息
     * @param args 验证信息，填充参数。第一个元素必须是属性名
	 */
	private void vaild(boolean result,String message,Object... args) {
		String msg = String.format(message, args);
		if(!result) {
			failedMsgList.add(new ModelValidationResult(args[0].toString(), msg));
		}
	}
	
	/**
	 * 将值转为整数型数值
	 * @param name 属性名
	 * @param value 属性值
	 * @return 整数数值，转换成失败，返回null
	 */
	private Long toLong(String name,Object value) {
		if(value == null) return null;
		if(isNumber(name, value)) {
			return Long.valueOf(value.toString());
		}
		return null;
	}
	
	/**
	 * 将值转为浮点型数值
	 * @param name 属性名
	 * @param value 属性值
	 * @return 浮点型数值，转换成失败，返回null
	 */
	private Double toDouble(String name,Object value) {
		if(value == null) return null;
		if(isNumber(name, value)) {
			return Double.valueOf(value.toString());
		}
		return null;
	}
	
	/**
	 * 验证结果，有验证失败信息，则抛出异常
	 */
	public void result() {
		if(this.failedMsgList.size()>0) {
			String json = GsonUtils.toJson(failedMsgList);
			throw new ValidationFailedException(json);
		}
	}
}