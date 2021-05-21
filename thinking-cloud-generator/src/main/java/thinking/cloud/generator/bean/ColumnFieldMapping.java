package thinking.cloud.generator.bean;

import java.util.Set;

import lombok.Data;
import thinking.cloud.generator.config.ApplicationConfig;
import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.enums.PropKey;
import thinking.cloud.generator.exception.GeneratorException;
import thinking.cloud.generator.utils.PackageNameUtils;
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
	private String fullFieldType;
	private String fieldType;
	private boolean fk;
	private String fkMapper;
	
	
	public ColumnFieldMapping(String columnType,String columnName,Set<String> packages) {
		this.columnName = columnName;
		this.columnType = columnType;
		ApplicationConfig config = GeneratorContext.applicationConfig;		
		if(columnName.startsWith("fk")) {
			// 外键处理
			fk = true;
			String classNamePrefix = config.getProperty(PropKey.CLASS_NAME_PREFIX.propKey());
			this.fieldName = ReflectBeanUtils.snakeCase2CamelCase("fk_", true, "_", classNamePrefix+"_"+columnName);
			this.fieldType = fieldName.substring(0,1).toUpperCase()+fieldName.substring(1);
			this.fullFieldType = PackageNameUtils.entityPackage()+"."+this.fieldType;
			this.fkMapper =  PackageNameUtils.mapperPackage()+fieldType+"Mapper.select";
		}else {
			// 普通列
			this.fullFieldType = GeneratorContext.typeMappingConfig.getJavaType(columnType);
			this.fieldType=this.fullFieldType.substring(this.fullFieldType.lastIndexOf(".")+1);
			this.fieldName = ReflectBeanUtils.snakeCase2CamelCase(true, columnName);
		}
		
		
		if(this.fullFieldType==null) {
			throw new GeneratorException("没有与database类型["+this.columnType+"]匹配的java类型"); 
		}
		
		if(this.fullFieldType.indexOf(".")!=-1) {
			packages.add(this.fullFieldType);
		}
		

		
		

	}
}
