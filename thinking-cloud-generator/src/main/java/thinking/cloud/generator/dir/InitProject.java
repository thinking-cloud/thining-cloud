package thinking.cloud.generator.dir;

/**
 * 初始化项目的接口
 * @author zhouxinke
 * @date 2021年5月6日
 */
public abstract class InitProject {
	
	/**
	 * 初始化核心方法
	 */
	public void init() {
		createProjectDir();
		createConfig();
	}
	
	/**
	 * 创建项目目录
	 */
	public abstract void createProjectDir();
	
	/**
	 * 创建配置文件
	 */
	public abstract void createConfig();
	
}
