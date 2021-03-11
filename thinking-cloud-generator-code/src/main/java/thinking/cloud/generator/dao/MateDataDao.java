package thinking.cloud.generator.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

import thinking.cloud.generator.config.Config;
import thinking.cloud.generator.exception.GeneratorException;

/**
 * 
 * <P>
 * </P>
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
public abstract class MateDataDao {
	protected Connection connection;
	protected DatabaseMetaData metaData;

	public void init() {
		String url = Config.getJdbcUrl();
		String username = Config.getUserName();
		String pwd = Config.getJdbcPassword();

		try {
			Class.forName(Config.getJdbcDriver());
			connection = DriverManager.getConnection(url, username, pwd);
			metaData = connection.getMetaData();

			System.out.println("----------连接成功,连接信息:");
			System.out.println("url: " + metaData.getURL());
			System.out.println("username: " + metaData.getUserName());
			System.out.println("DBMS: " + metaData.getDatabaseProductName());
			System.out.println("version: " + metaData.getDatabaseProductVersion());
			System.out.println("--------------------------");	
		} catch (ClassNotFoundException e) {
			throw new GeneratorException("类加载失败:" + Config.getJdbcDriver(), e);
		} catch (SQLException e) {
			throw new GeneratorException("创建连接失败, url:" + url + " username:" + username + " pwd:" + pwd, e);
		}
	}

	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
