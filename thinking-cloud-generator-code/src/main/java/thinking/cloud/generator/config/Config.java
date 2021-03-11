package thinking.cloud.generator.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
public class Config {
	public static final String JDBC_DRIVER = "jdbc-driver";
	public static final String JDBC_URL = "jdbc-url";
	public static final String JDBC_USERNAME = "jdbc-username";
	public static final String JDBC_PASSWORD = "jdbc-password";
	public static final String BASE_PACKAGE = "base-package";
	public static final String DATABASE_NAME = "database-name";
	public static final String TABLE_NAME = "table-name";
	public static final String FK_PREFIX = "fk-prefix";
	
	public static Properties prop = new Properties();
	static {
		InputStream in = null;
		try {
			in = ClassLoader.getSystemResourceAsStream("application.properties");
			prop.load(in);
		} catch (Exception e) {
			System.out.println("读取配置文件失败! 系统停止");
			e.printStackTrace();
			System.exit(0);
		}finally {
			if(in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 获取数据库驱动全类名
	 * @return
	 */
	public static String getJdbcDriver() {
		return prop.getProperty(JDBC_DRIVER);
	}
	
	/**
	 * 获取jdbc连接url
	 * @return
	 */
	public static String getJdbcUrl() {
		return prop.getProperty(JDBC_URL);
	}
	
	/**
	 * 获取JDBC连接用户名
	 * @return
	 */
	public static String getUserName() {
		return prop.getProperty(JDBC_USERNAME);
	}
	
	/**
	 * 获取jdbc连接密码
	 * @return
	 */
	public static String getJdbcPassword() {
		return prop.getProperty(JDBC_PASSWORD);
	}
	
	/**
	 * 获取连接数据库的库名
	 * @return
	 */
	public static String getDataBaseName() {
		return prop.getProperty(DATABASE_NAME);
	}
	
	/**
	 * 获取生成代码的base包名
	 * @return
	 */
	public static String getBasePackage() {
		return prop.getProperty(BASE_PACKAGE);
	}
	
	/**
	 * 获取po对象存放的包名
	 * @return
	 */
	public static String getBeanPackage() {
		return prop.getProperty(BASE_PACKAGE)+".api.bean";
	}
	
	/**
	 * 获取bo的包名
	 * @return
	 */
	public static String getBoPackage() {
		return prop.getProperty(BASE_PACKAGE)+".api.bean.bo";
	}
	
	/**
	 * api vo的包名
	 * @return
	 */
	public static String getApiVoPackage() {
		return prop.getProperty(BASE_PACKAGE)+".api.bean.vo";
	}
	
	/**
	 * api 的包名
	 * @return
	 */
	public static String getApiPackage() {
		return prop.getProperty(BASE_PACKAGE)+".api";
	}
	
	/**
	 * server vo的包名
	 * @return
	 */
	public static String getVoPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.vo";
	}
	
	/**
	 * controller的包名
	 * @return
	 */
	public static String getControllerPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.controller";
	}
	
	/**
	 * adapter的包名
	 * @return
	 */
	public static String getAdapterPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.adapter";
	}
	
	/**
	 * 业务类的包名
	 * @return
	 */
	public static String getServicePackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.service";
	}
	
	/**
	 * 业务实现类的包名
	 * @return
	 */
	public static String getServiceImplPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.service.impl";
	}
	
	/**
	 * mapper接口的包名
	 * @return
	 */
	public static String getMapperPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.mapper"; 
	}
	
	/**
	 * mapper xml的包名
	 * @return
	 */
	public static String getMapperXmlPackage() {
		return prop.getProperty(BASE_PACKAGE)+".server.mapper.xml";
	}
	
	
	/**
	 * 获取要处理的表明
	 * @return 不设置则全部处理,返回null
	 */
	public static String[] getTableNames(){
		String tableName = prop.getProperty("table-name");
		if(tableName==null || tableName.trim().equals("")) {
			return null;
		}
		return tableName.split(",");
	}
	
	public static String getFkPrefix() {
		return prop.getProperty(FK_PREFIX);
	}
}
