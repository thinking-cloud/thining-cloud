package thinking.cloud.generator.bean;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import lombok.Data;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
@Data
public class TableClassMapping {
	private String tableName;
	private String className;
	private String boName;
	private String limitBoName;
	private String apiVoName;
	private String voName;
	private String apiName;
	private String controllerName;
	private String serviceName;
	private String serviceImplName;
	private String mapperName;
	private String adapterName;
	private List<ColumnFieldMapping> colFieldMappingList=new LinkedList<>();
	private Set<String> importPackageSet = new LinkedHashSet<>();

	
	public TableClassMapping(String tableName) {
		this.tableName = tableName;
		this.className = ReflectBeanUtils.tableName2ClassName(null, "_", tableName);
		this.boName = className+"Bo";
		this.limitBoName = className+"LimitBo";
		this.apiVoName = className+"ApiVo";
		this.voName = className+"Vo";
		this.apiName = className+"Api";
		this.controllerName = className+"Controller";
		this.serviceName = className+"Service";
		this.serviceImplName = className+"ServiceImpl";
		this.mapperName = className+"Mapper";
		this.adapterName = className+"Adapter";
	}
}
