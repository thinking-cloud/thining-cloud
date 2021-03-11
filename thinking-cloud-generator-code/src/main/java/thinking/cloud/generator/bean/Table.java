package thinking.cloud.generator.bean;

import java.util.List;

import lombok.Data;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * @author zhouxinke
 * @date 2021年3月8日
 */
@Data
public class Table {
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
	private List<Column> columns;
	
	public Table(String tableName, List<Column> columns) {
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
		this.columns = columns;
	}
	
	public Table(String tableName) {
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
