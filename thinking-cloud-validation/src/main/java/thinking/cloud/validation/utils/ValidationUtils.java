package thinking.cloud.validation.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import thinking.cloud.utils.data.DateUtils;
import thinking.cloud.utils.reflect.ReflectUtils;
import thinking.cloud.validation.Validation;
import thinking.cloud.validation.ValidationModel;
import thinking.cloud.validation.annotation.DateTime;
import thinking.cloud.validation.annotation.DecimalDigits;
import thinking.cloud.validation.annotation.DecimalMax;
import thinking.cloud.validation.annotation.DecimalMin;
import thinking.cloud.validation.annotation.Digits;
import thinking.cloud.validation.annotation.FieldLink;
import thinking.cloud.validation.annotation.Max;
import thinking.cloud.validation.annotation.Min;
import thinking.cloud.validation.annotation.NotBlank;
import thinking.cloud.validation.annotation.NotEmpty;
import thinking.cloud.validation.annotation.NotNull;
import thinking.cloud.validation.annotation.Null;
import thinking.cloud.validation.annotation.Number;
import thinking.cloud.validation.annotation.Past;
import thinking.cloud.validation.annotation.Size;
import thinking.cloud.validation.constants.CalendarUnit;

/**
 * 验证相关的工具类
 * @author think
 *
 */
public class ValidationUtils {
	
	public static void vaild(ValidationModel model) {
		// 获取所有验证对象
		Validation validation = model.vaildationObject();
		// 获取所有方法
		Class<? extends ValidationModel> modelClass = model.getClass();
		Method[] methods = modelClass.getMethods();
		for (Method method : methods) {
			// 获取方法上的所有注解
			Annotation[] annos = readAnnotations(method);
			if(annos != null) {
				// 获取属性名，用于获取属性值
				FieldLink fieldLink = method.getAnnotation(FieldLink.class);
				String fieldName = methodName2fieldName(method.getName(), 
											fieldLink!=null ? fieldLink.value() : null);
				// 获取属性名，用于验证失败提示
				String name =  ReflectUtils.methodName2fieldName("set", method.getName());
				//获取属性值
				Object value = readFieldValue(model, fieldName);
				for (Annotation anno : annos) {				
					if(anno instanceof NotNull) {
						validation.notNull(name, value);
					}else if(anno instanceof NotEmpty) { 
						validation.notEmpty(name, value);
					}else if(anno instanceof NotBlank) {
						validation.notBlank(name, value.toString());
					}else if(anno instanceof Size) {
						int min = ((Size) anno).min();
						int max = ((Size) anno).max();
						validation.size(name, value, min, max);
					}else if(anno instanceof Null) {
						validation.isNull(name, value);
					}else if(anno instanceof Number) {
						validation.isNumber(name, value.toString());
					}else if(anno instanceof Min) {
						long min = ((Min) anno).min();
						validation.min(name, value, min);
					}else if(anno instanceof Max) {
						long max = ((Max) anno).max();
						validation.max(name, value, max);
					}else if(anno instanceof Digits) {
						long min = ((Digits) anno).min();
						long max = ((Digits) anno).max();
						validation.digits(name, value, min, max);
					}else if(anno instanceof DecimalMin) {
						double minv = ((DecimalMin) anno).min();
						BigDecimal min = new BigDecimal(minv);
						validation.decimalMin(name, value, min);
					}else if(anno instanceof DecimalMax) {
						double maxv = ((DecimalMax) anno).max();
						BigDecimal max = new BigDecimal(maxv);
						validation.decimalMax(name, value, max);
					}else if(anno instanceof DecimalDigits) {
						double minv = ((DecimalDigits) anno).min();
						double maxv = ((DecimalDigits) anno).max();
						BigDecimal min = new BigDecimal(minv);
						BigDecimal max = new BigDecimal(maxv);
						validation.decimalDigits(name, value, min, max);
					}else if(anno instanceof DateTime) {
						String pattern = ((DateTime) anno).pattern();
						validation.isDateTime(name, value.toString(), pattern);
					}else if(anno instanceof Past) {
						try {
							String pastStr = ((Past) anno).pastDate();
							Date past = null;
							if(pastStr != null) {
								String pattern = ((Past) anno).pattern();
								past = DateUtils.StringPatternDate(pastStr, pattern);
							}else {
								CalendarUnit unit = ((Past) anno).unit();
								CalendarUnit ignoreUnit = ((Past) anno).ignoreUnit();
								int offset = ((Past) anno).past();
								past = date(unit, ignoreUnit, offset);
										
								
							}
							
						}catch (Exception e) {
							
						}
						//validation.past(name, value, past, pattern);
					}
				}
			}
		}
	}
		
	/**
	 * 去尾法计算时间
	 * @param calendarUnit 计算精度
	 * @param ignoreUnit 去尾法保留的精度
	 * @param number 日期偏移量
	 * @return
	 */
	private static Date date(CalendarUnit calendarUnit,CalendarUnit ignoreUnit,int offset) {
		// past参照时间
		Calendar date = Calendar.getInstance();
		date.setTime(ignoreDate(ignoreUnit));
		// 单位判断
		int code = calendarUnit.code() - ignoreUnit.code();
		if(code >= 0) {
			throw new RuntimeException("calendarUnit must be greater than ignoreUnit！");
		}
		// 计算时间
		switch (calendarUnit) {
			case YEAR:
				date.add(Calendar.YEAR, offset);
				break;
			case MONTH:
				date.add(Calendar.MONTH, offset);
				break;
			case DAY:
				date.add(Calendar.DAY_OF_MONTH, offset);
				break;
			case HOUR:
				date.add(Calendar.HOUR_OF_DAY, offset);
				break;
			case MINUTE:
				date.add(Calendar.MINUTE, offset);
				break;
			case SECOND:
				date.add(Calendar.SECOND, offset);
		}
		return date.getTime();
	} 
	
	/**
	 * 去尾法日期计算
	 * @param ignoreUnit 忽略精度
	 * @return 计算后的日期
	 */
	private static Date ignoreDate(CalendarUnit ignoreUnit) {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		
		// 忽略单位计算
		switch (ignoreUnit) {
			case YEAR:
				calendar.set(year, 0, 0, 0, 0, 0);
				break;
			case MONTH:
				calendar.set(year, month, 0, 0, 0, 0);
				break;
			case DAY:
				calendar.set(year, month, day, 0, 0, 0);
				break;
			case HOUR:
				calendar.set(year, month, day, hour, 0, 0);
			case MINUTE:
				calendar.set(year, month, day, hour, minute, 0);
			case SECOND:
				calendar.set(year, month, day, hour, minute, second);
		}
		return calendar.getTime();
	}
	
	/**
	 * 获取带有set前缀方法上的所有注解
	 * 若不是set前缀的方法，则返回null
	 * 
	 * @param method 方法对象
	 * @return method上的注解集合
	 */
	public static Annotation[] readAnnotations(Method method) {
		String methodName = method.getName();
		if(methodName.startsWith("set")) {
			return method.getAnnotations();
		}
		return null;
	}
	
	/**
	 * 获取属性名
	 * @param methodName 方法名
	 * @param annoValue 注解中设置的名字
	 * @return 属性名
	 */
	public static <T> String methodName2fieldName(String methodName,String annoValue){
		if(annoValue != null) {
			return annoValue;
		}else {
			return ReflectUtils.methodName2fieldName("set", methodName);
		}
	}
	
	/**
	 * 获取属性值
	 * @param vm 验证模型
	 * @param fieldName 属性名
	 * @param clazz 属性值类型
	 * @return 属性值类型
	 */
	public static Object readFieldValue(ValidationModel<?> vm,String fieldName) {
		try {
			String sourceName = "source";
			Object fieldValue = null;
			if(fieldName.indexOf("_") >= 0) {
				sourceName = fieldName.replaceFirst("_", "").split("\\.")[0];
			} 
			// 获取model中的源实体及其他实体
			Method method = vm.getClass().getMethod(sourceName);
			fieldValue = method.invoke(vm);
			// 获取entity中的属性值
			String[] nameLink = fieldName.split("\\.");
			String methodName = null;
			Method getMethod = null; 
			for (String name : nameLink) {
				methodName = ReflectUtils.fieldName2MethodName("get", name);
				getMethod = fieldValue.getClass().getMethod(methodName);
				fieldValue = getMethod.invoke(fieldValue);
			}
			return fieldValue;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}