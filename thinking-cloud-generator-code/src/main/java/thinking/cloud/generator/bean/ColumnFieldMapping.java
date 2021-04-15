package thinking.cloud.generator.bean;

import java.util.Set;

import lombok.Data;
import thinking.cloud.generator.config.Config;
import thinking.cloud.generator.exception.GeneratorException;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
@Data
public class ColumnFieldMapping {
	private String columnName;
	private String columnType;
	private String fieldName;
	private String fieldType;
	private boolean fk;
	
	public ColumnFieldMapping(String columnType,String columnName,Set<String> packages) {
		this.columnName = columnName;
		this.columnType = columnType;
		this.fieldType = Config.typeMappingConfig.getJavaType(columnType);
		
		if(columnName.indexOf("-")>=0) {
			fk = true;
			this.fieldName = ReflectBeanUtils.columnName2FieldName("", "-", columnName);
			this.fieldType = Config.applicationConfig.getFkPrefix()+fieldName.substring(0,1)+fieldName.substring(1);
		}else if(columnName.indexOf("_")>=0) {
			this.fieldName = ReflectBeanUtils.columnName2FieldName("", "_", columnName);
		}else {
			this.fieldName = columnName;
		}
		
		if(this.fieldType==null) {
			throw new GeneratorException("没有与database类型["+this.columnType+"]匹配的java类型"); 
		}
		if(this.fieldType.indexOf(".")!=-1) {
			packages.add(this.fieldType);
			this.fieldType=this.fieldType.substring(this.fieldType.lastIndexOf(".")+1);
		}
	}
}
