package thinking.cloud.generator.bean;

import lombok.Data;
import thinking.cloud.generator.config.Config;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
@Data
public class Column {
	private String columnName;
	private String fieldName;
	private String fieldType;
	private boolean fk;
	
	public Column(String columnName) {
		this.columnName = columnName;
		if(columnName.indexOf("-")>=0) {
			fk = true;
			fieldName = ReflectBeanUtils.columnName2FieldName("", "-", columnName);
			fieldType = Config.getFkPrefix()+fieldName.substring(0,1)+fieldName.substring(1);
		}else {
			fieldName = ReflectBeanUtils.columnName2FieldName("", "_", columnName);
		}
	}
}
