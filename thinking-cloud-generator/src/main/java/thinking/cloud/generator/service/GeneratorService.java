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
			

		    OutputMapperXml gmx = new OutputMapperXml(new File(FilePathUtils.serverPath(),FilePathUtils.MAIN_JAVA_PATH).getAbsolutePath(), tableMatedata);
		    gmx.generatorXml();
		    OutputJavaFile om = new OutputMapper("d:/test", tableMatedata);
		    om.outputJavaFile();
		    OutputJavaFile os = new OutputService("d:/test", tableMatedata);
		    os.outputJavaFile();
		    OutputJavaFile osi = new OutputServiceImpl("d:/test", tableMatedata);
		    osi.outputJavaFile();
		    OutputJavaFile oa = new OutputAdapter("d:/test", tableMatedata);
		    oa.outputJavaFile();
		    OutputJavaFile oapi = new OutputApi("d:/test", tableMatedata);
		    oapi.outputJavaFile();
		    OutputJavaFile oc = new OutputController("d:/test", tableMatedata);
		    oc.outputJavaFile();
		    OutputJavaFile oe = new OutputEntity("d:/test", tableMatedata);
		    oe.outputJavaFile();
		    OutputJavaFile ob = new OutputBo("d:/test", tableMatedata);
		    ob.outputJavaFile();
		    OutputJavaFile oav = new OutputApiVO("d:/test", tableMatedata);
		    oav.outputJavaFile();
		    OutputJavaFile ov = new OutputVO("d:/test", tableMatedata);
		    ov.outputJavaFile();
		}
	}
}