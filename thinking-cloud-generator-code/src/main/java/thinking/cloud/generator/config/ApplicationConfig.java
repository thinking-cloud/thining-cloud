package thinking.cloud.generator.config;

import java.io.InputStream;
import java.util.Properties;

import thinking.cloud.generator.contant.ConfigConstants;
import thinking.cloud.generator.exception.GeneratorException;

/**
 * application.properties配置文件加载类
 * 用于程序的配置
 * @author zhouxinke
 * @date 2021年4月8日
 */
public class ApplicationConfig extends Config{
	public Properties applicationProp = new Properties();
	
	public ApplicationConfig() {
		try(InputStream in = ClassLoader.getSystemResourceAsStream("application.Properties")){
			applicationProp.load(in);
		}catch (Exception e) {
			throw new GeneratorException("读取配置文件失败! ",e);
		}
	}

	/**
	 * 获取数据库驱动全类名
	 * @return
	 */
	public String getJdbcDriver() {
		return applicationProp.getProperty(ConfigConstants.JDBC_DRIVER);
	}
	
	/**
	 * 获取jdbc连接url
	 * @return
	 */
	public String getJdbcUrl() {
		return applicationProp.getProperty(ConfigConstants.JDBC_URL);
	}
	
	/**
	 * 获取JDBC连接用户名
	 * @return
	 */
	public String getUserName() {
		return applicationProp.getProperty(ConfigConstants.JDBC_USERNAME);
	}
	
	/**
	 * 获取jdbc连接密码
	 * @return
	 */
	public String getJdbcPassword() {
		return applicationProp.getProperty(ConfigConstants.JDBC_PASSWORD);
	}
	
	/**
	 * 获取连接数据库的库名
	 * @return
	 */
	public String getDataBaseName() {
		return applicationProp.getProperty(ConfigConstants.DATABASE_NAME);
	}
	
	/**
	 * 获取生成代码的base包名
	 * @return
	 */
	public String getBasePackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE);
	}
	
	/**
	 * 获取po对象存放的包名
	 * @return
	 */
	public String getBeanPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".api.bean";
	}
	
	/**
	 * 获取bo的包名
	 * @return
	 */
	public String getBoPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".api.bean.bo";
	}
	
	/**
	 * api vo的包名
	 * @return
	 */
	public String getApiVoPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".api.bean.vo";
	}
	
	/**
	 * api 的包名
	 * @return
	 */
	public String getApiPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".api";
	}
	
	/**
	 * server vo的包名
	 * @return
	 */
	public String getVoPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.vo";
	}
	
	/**
	 * controller的包名
	 * @return
	 */
	public String getControllerPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.controller";
	}
	
	/**
	 * adapter的包名
	 * @return
	 */
	public String getAdapterPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.adapter";
	}
	
	/**
	 * 业务类的包名
	 * @return
	 */
	public String getServicePackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.service";
	}
	
	/**
	 * 业务实现类的包名
	 * @return
	 */
	public String getServiceImplPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.service.impl";
	}
	
	/**
	 * mapper接口的包名
	 * @return
	 */
	public String getMapperPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.mapper"; 
	}
	
	/**
	 * mapper xml的包名
	 * @return
	 */
	public String getMapperXmlPackage() {
		return applicationProp.getProperty(ConfigConstants.BASE_PACKAGE)+".server.mapper.xml";
	}
	
	
	/**
	 * 获取要处理的表明
	 * @return 不设置则全部处理,返回null
	 */
	public String[] getTableNames(){
		String tableName = applicationProp.getProperty("table-name");
		if(tableName==null || tableName.trim().equals("")) {
			return null;
		}
		return tableName.split(",");
	}
	
	public String getFkPrefix() {
		return applicationProp.getProperty(ConfigConstants.FK_PREFIX);
	}
}
