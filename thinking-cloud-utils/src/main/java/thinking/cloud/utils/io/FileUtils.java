package thinking.cloud.utils.io;

import java.io.File;

/**
 * 文件相关的工具类
 * @author think
 *
 */
public class FileUtils {
	
	/**
	 * 创建目录
	 * @param file
	 */
	public static void mkdirs(File file) {
		if(!file.exists()) {
			boolean status = file.mkdirs();
			if(!status) {
				throw new RuntimeException("Error creating file the " + file.getAbsolutePath());
			}
		}
	}
	
	/**
	 * 创建文件
	 * @param file
	 */
	
	public static void createFile(File file) {
		try {
			File parentFile = file.getParentFile();
			mkdirs(parentFile);
			boolean createNewFile = file.createNewFile();
			if(!createNewFile) {
				throw new RuntimeException("Error creating file the " + file.getAbsolutePath());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
