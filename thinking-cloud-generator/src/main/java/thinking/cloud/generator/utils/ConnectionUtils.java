package thinking.cloud.generator.utils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Data;
import thinking.cloud.generator.config.ApplicationConfig;
import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.enums.PropKey;
import thinking.cloud.generator.exception.GeneratorException;

/**
 * 数据类连接类
 * @author zhouxinke
 * @date 2021年4月8日
 */
@Data
public class ConnectionUtils implements AutoCloseable{
	private Connection connection;
	private DatabaseMetaData metaData;
	
	public ConnectionUtils() {
		init();
	}
	
	/**
	 * 初始化连接
	 */
	public void init() {
		ApplicationConfig config = GeneratorContext.applicationConfig;
		if(connection == null) {
			String url = config.getProperty(PropKey.JDBC_ADDRESS.propKey());
			String username = config.getProperty(PropKey.JDBC_USERNAME.propKey());
			String pwd = config.getProperty(PropKey.JDBC_PASSWORD.propKey());
			try {
				connection = DriverManager.getConnection(url, username, pwd);
				metaData = connection.getMetaData();
				System.out.println("----------连接成功,连接信息----------");
				System.out.println("url: " + metaData.getURL());
				System.out.println("username: " + metaData.getUserName());
				System.out.println("DBMS: " + metaData.getDatabaseProductName());
				System.out.println("version: " + metaData.getDatabaseProductVersion());
				System.out.println("-----------------------------------");	
			} catch (SQLException e) {
				throw new GeneratorException("创建连接失败, url:" + url + " username:" + username + " pwd:" + pwd, e);
			}
		}
	}
	
	/**
	 * 关闭连接
	 */
	public void close() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
				metaData = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
