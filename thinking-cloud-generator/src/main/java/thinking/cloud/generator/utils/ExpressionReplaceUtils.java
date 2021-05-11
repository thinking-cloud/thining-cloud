package thinking.cloud.generator.utils;

import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.enums.PropKey;

/**
 *  表达式替换
 * @author zhouxinke
 * @date 2021年5月7日
 */
public class ExpressionReplaceUtils {
	/**
	 * 替换模板文件中的表达式
	 * @param line
	 * @return
	 */
	public static String replaceAll(String line) {
		for (PropKey templateExpression : PropKey.values()) {
			String express = "#{"+templateExpression.name()+"}";
			while(line.indexOf(express)!=-1) {
				line = line.replace(express, GeneratorContext.applicationConfig.getProperty(templateExpression.propKey()));
			}
		}
		return line;
	}
}
