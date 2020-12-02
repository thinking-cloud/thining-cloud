package thinking.cloud.rest.client.entity;

import org.springframework.core.io.Resource;

import lombok.Data;

/**
 * <P>
 * 	默认的文件上传下载的描述类	
 * </P>
 * @author think
 * @date 2020年11月19日
 */
@Data
public class RestDefaultFile implements RestUploadFile,RestDownloadFile {
	private String fileName;
	private Resource fileContent;
	private String filePath;
	
	public RestDefaultFile(String fileName, Resource fileContent) {
		this.fileName = fileName;
		this.fileContent = fileContent;
	}
	public RestDefaultFile() {
		
	}
}
