package thinking.cloud.generator.service;

import java.io.File;
import java.util.List;

import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.db.ReaderMateData;
import thinking.cloud.generator.dir.InitProject;
import thinking.cloud.generator.dir.impl.InitMavenProject;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.file.impl.OutputAdapter;
import thinking.cloud.generator.file.impl.OutputApi;
import thinking.cloud.generator.file.impl.OutputApiVO;
import thinking.cloud.generator.file.impl.OutputBo;
import thinking.cloud.generator.file.impl.OutputController;
import thinking.cloud.generator.file.impl.OutputEntity;
import thinking.cloud.generator.file.impl.OutputMapper;
import thinking.cloud.generator.file.impl.OutputMapperXml;
import thinking.cloud.generator.file.impl.OutputService;
import thinking.cloud.generator.file.impl.OutputServiceImpl;
import thinking.cloud.generator.file.impl.OutputVO;
import thinking.cloud.generator.utils.ConnectionUtils;
import thinking.cloud.generator.utils.FilePathUtils;
import thinking.cloud.generator.utils.PackageNameUtils;

/**
 * 业务实现类
 * 
 * @author zhouxinke
 * @date 2021年4月8日
 */
public class GeneratorService {
	public void generatorService() {
		try (ConnectionUtils connectionUtils = new ConnectionUtils()) {
			ReaderMateData metaData = new ReaderMateData(connectionUtils.getMetaData());

			System.out.println("获取数据库元数据");
			List<TableClassMapping> tableMatedata = metaData.tableMatedata();
			for (TableClassMapping table : tableMatedata) {
				metaData.columnMateData(table);
			}
			InitProject initProject = new InitMavenProject();
			initProject.init();
			
			String beansModuleFilePath = new File(FilePathUtils.beansPath(),FilePathUtils.MAIN_JAVA_PATH).getAbsolutePath();
			String serverModuleFilePath = new File(FilePathUtils.serverPath(),FilePathUtils.MAIN_JAVA_PATH).getAbsolutePath();
			String apiModuleFilePath = new File(FilePathUtils.apiPath(),FilePathUtils.MAIN_JAVA_PATH).getAbsolutePath();
		    // beans
			OutputJavaFile oe = new OutputEntity(beansModuleFilePath, tableMatedata);
		    oe.outputJavaFile();
		    OutputJavaFile ob = new OutputBo(beansModuleFilePath, tableMatedata);
		    ob.outputJavaFile();
		    // vo
		    OutputJavaFile ov = new OutputVO(serverModuleFilePath, tableMatedata);
		    ov.outputJavaFile();
		    OutputJavaFile oav = new OutputApiVO(apiModuleFilePath, tableMatedata);
		    oav.outputJavaFile();
		    // api
		    OutputJavaFile oapi = new OutputApi(apiModuleFilePath, tableMatedata);
		    oapi.outputJavaFile();
		    // server
		    OutputMapperXml gmx = new OutputMapperXml(serverModuleFilePath, tableMatedata);
		    gmx.generatorXml();
		    OutputJavaFile om = new OutputMapper(serverModuleFilePath, tableMatedata);
		    om.outputJavaFile();
		    OutputJavaFile os = new OutputService(serverModuleFilePath, tableMatedata);
		    os.outputJavaFile();
		    OutputJavaFile osi = new OutputServiceImpl(serverModuleFilePath, tableMatedata);
		    osi.outputJavaFile();
		    OutputJavaFile oa = new OutputAdapter(serverModuleFilePath, tableMatedata);
		    oa.outputJavaFile();
		    OutputJavaFile oc = new OutputController(serverModuleFilePath, tableMatedata);
		    oc.outputJavaFile();

		    

		   

		}
	}
}