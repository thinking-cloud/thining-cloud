package thinking.cloud.generator.file.impl;

import java.io.PrintStream;
import java.util.List;

import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.utils.PackageNameUtils;

/**
 * 输出BO文件
 * @author zhouxinke
 * @date 2021年4月30日
 */
public class OutputBo extends OutputJavaFile{

	public OutputBo(String outputFilePath, List<TableClassMapping> tableClassMapping) {
		super(outputFilePath, tableClassMapping);
	}

	@Override
	public String getFileName(TableClassMapping table) {
		return table.getBoName();
	}

	@Override
	protected void outputPackage(TableClassMapping table, PrintStream ps) {
		String packageName = PackageNameUtils.boPackage();
		ps.println("package "+packageName+";");
	}

	@Override
	protected void outputAnnotation(TableClassMapping table, PrintStream ps) {
		ps.println("@Data");
	}

	@Override
	protected void outputClassName(TableClassMapping table, PrintStream ps) {
		ps.println("public class "+table.getBoName()+" extends "+table.getEntityName()+" {");
	}

	@Override
	protected void outputClassFiled(TableClassMapping table, PrintStream ps) {

	}

	@Override
	protected void outputMethod(TableClassMapping table, PrintStream ps) {
		
	}
	
}
