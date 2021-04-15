package thinking.cloud.generator.contant;

/**
 * 
 * @author zhouxinke
 * @date 2021年4月8日
 */
public class TemplateFileContants {
	// TableClassMapping 表达式对应的常量
	public final static String TABLE_NAME = "getTableName";
	public final static String ClASS_NAME = "getClassName";
	public final static String BO_NAME = "getBoName";
	public final static String LIMIT_BO_NAME = "getLimitBoName";
	public final static String API_VO_NAME = "getApiVoName";
	public final static String VO_NAME = "getVoName";
	public final static String API_NAME = "getApiName";
	public final static String CONTROLLER_NAME = "getControllerName";
	public final static String SERVICE_NAME = "getServiceName";
	public final static String SERVICE_IMPL_NAME = "getServiceImplName";
	public final static String MAPPER_NAME = "getMapperName";
	public final static String ADAPTER_NAME = "getAdapterName";
	
	// ColumnFieldMapping 表达式对应的常量
	public final static String COLUMN_NAME = "getColumnName";
	public final static String COLUMN_TYPE = "getColumnType";
	public final static String FIELD_NAME = "getFieldName";
	public final static String FIELD_TYPE = "getFieldType";
	public final static String FK_MAPPER = "fkMapper";
	
	// mapper-template type的常量
	public final static String MAPPER_RESULT= "result";
	public final static String MAPPER_ASSOCIATION= "association";
	public final static String MAPPER_COLUMNS_ALL= "columns_all";
	public final static String MAPPER_IF= "if";
}
