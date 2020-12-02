package thinking.cloud.rest.client.entity;

import java.util.UUID;

import org.springframework.core.io.Resource;

/**
 *  下载文件的接口
 * @author think
 * @date 2020年11月19日
 */
public interface RestDownloadFile {
	/**
	 * 获取文件路径,不包含文件名
	 * @return 文件所在路径
	 */
	default String getFilePath() {
		return "/tmp/";
	}
	
	/**
	 * 设置文件路径,不包含文件名
	 * @param path 文件所在路径
	 */
	default void setFilePath(String filePath) {
		throw new RuntimeException("RestDownloadFile.filePath 未定义");
	}
	
	/**
	 * 获取文件名
	 * @return 文件名
	 */
	default String getFileName() {
		return UUID.randomUUID()+".tmp";
	}
	/**
	 * 设置文件名
	 * @param fileName 文件名
	 */
	default void setFileName(String fileName) {
		throw new RuntimeException("RestDownloadFile.fileName 未定义");
	}
	/**
	 * 获取文件内容
	 * @return 文件内容
	 */
	default Resource getFileContent() {
		return null;
	}
	/**
	 * 设置文件内容
	 * @param fileContent 文件内容
	 */
	default void setFileContent(Resource fileContent) {
		throw new RuntimeException("RestDownloadFile.fileContent 未定义");
	}
	
	/**
	 * 文件所在的全路径
	 * @return
	 */
	default String fullPathName() {
		return getFilePath() + getFileName();
	}
}
