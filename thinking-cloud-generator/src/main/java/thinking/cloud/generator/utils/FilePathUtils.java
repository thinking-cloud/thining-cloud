package thinking.cloud.generator.utils;

import java.io.File;

import thinking.cloud.generator.config.ApplicationConfig;
import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.enums.PropKey;

/**
 * 文件路径处理类
 * @author zhouxinke
 * @date 2021年5月8日
 */
public class FilePathUtils {
	public static final String MAIN_JAVA_PATH = "src/main/java";
	public static final String MAIN_RESOURCES_PATH = "src/main/resources";
	public static final String TEST_JAVA_PATH = "src/test/java";
	public static final String TEST_RESOURCES_PATH = "src/test/resources";
	public static final String TARGET_PATH = "target";
	
	/**
	 * Maven项目路径
	 * @return
	 */
	public static File projectPath() {
		ApplicationConfig config = GeneratorContext.applicationConfig;
		String projectPath = config.getProperty(PropKey.PROJECT_PATH.propKey());
		String projectName =  config.getProperty(PropKey.PROJECT_NAME.propKey());
		return new File(projectPath,projectName);
	}
	
	/**
	 * api moudle路径
	 * @return
	 */
	public static File apiPath() {
		ApplicationConfig config = GeneratorContext.applicationConfig;
		String projectName =  config.getProperty(PropKey.PROJECT_NAME.propKey());
		return new File(projectPath(),projectName+"-api");
	}
	
	/**
	 * beans moudle路径
	 * @return
	 */
	public static File beansPath() {
		ApplicationConfig config = GeneratorContext.applicationConfig;
		String projectName =  config.getProperty(PropKey.PROJECT_NAME.propKey());
		return new File(projectPath(),projectName+"-beans");
	}
	
	/**
	 * server moudle路径
	 * @return
	 */
	public static File serverPath() {
		ApplicationConfig config = GeneratorContext.applicationConfig;
		String projectName =  config.getProperty(PropKey.PROJECT_NAME.propKey());
		return new File(projectPath(),projectName+"-server");
	}
}
