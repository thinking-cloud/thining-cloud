package thinking.cloud.generator.config;

import java.io.InputStreamReader;
import java.util.Properties;

import thinking.cloud.generator.exception.GeneratorException;

/**
 * application.properties配置文件加载类
 * 用于程序的配置
 * @author zhouxinke
 * @date 2021年4月8日
 */
public class ApplicationConfig {
	private Properties prop = new Properties();
	
	public ApplicationConfig() {
		try(InputStreamReader in = new InputStreamReader(ClassLoader.getSystemResourceAsStream("application.Properties"),"UTF-8")){
			prop.load(in);
		}catch (Exception e) {
			throw new GeneratorException("读取配置文件失败! ",e);
		}
	}

	
	public String getProperty(String key) {
		return prop.getProperty(key);
	}
}
