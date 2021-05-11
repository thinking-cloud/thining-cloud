package thinking.cloud.generator.file.impl;

import java.io.PrintStream;
import java.util.List;

import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.utils.PackageNameUtils;

/**
 * 生成Mapper接口的java文件
 * 
 * @author zhouxinke
 * @date 2021年4月28日
 */

public class OutputMapper extends OutputJavaFile {

	public OutputMapper(String outputFilePath, List<TableClassMapping> tableClassMapping) {
		super(outputFilePath, tableClassMapping);
	}

	@Override
	protected void outputPackage(TableClassMapping table, PrintStream ps) {
		String packaheName = PackageNameUtils.mapperPackage();
		ps.println("package " + packaheName + ";");
	}

	@Override
	protected void outputAnnotation(TableClassMapping table, PrintStream ps) {

	}

	@Override
	protected void outputClassName(TableClassMapping table, PrintStream ps) {
		String generic = "<" + table.getEntityName() + ", " +table.getPk().getFieldType() + ">";
		StringBuilder line = new StringBuilder();
		line.append("public ");
		line.append("interface ");
		line.append(table.getMapperName() + " ");
		line.append("extends ");     
		line.append("SimpleMapper");
		line.append(generic);
		line.append(", ");
		line.append("PageMapper");
		line.append(generic);
		line.append("{\n");
		ps.println(line.toString());
	}

	@Override
	protected void outputClassFiled(TableClassMapping table, PrintStream ps) {

	}

	@Override
	public String getFileName(TableClassMapping table) {
		return table.getMapperName();
	}

	@Override
	protected void outputMethod(TableClassMapping table, PrintStream ps) {
		
	}

}
