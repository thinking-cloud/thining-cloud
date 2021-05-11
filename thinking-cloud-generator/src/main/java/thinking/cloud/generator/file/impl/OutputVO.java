package thinking.cloud.generator.file.impl;

import java.io.PrintStream;
import java.util.List;

import thinking.cloud.generator.bean.ColumnFieldMapping;
import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.utils.PackageNameUtils;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 输出VO类
 * @author zhouxinke
 * @date 2021年4月30日
 */
public class OutputVO extends OutputJavaFile {

	public OutputVO(String outputFilePath, List<TableClassMapping> tableClassMapping) {
		super(outputFilePath, tableClassMapping);
	}

	@Override
	public String getFileName(TableClassMapping table) {
		return table.getVoName();
	}

	@Override
	protected void outputPackage(TableClassMapping table, PrintStream ps) {
		String packageName = PackageNameUtils.voPackage();
		ps.println("package "+packageName+";");
	}

	@Override
	protected void outputAnnotation(TableClassMapping table, PrintStream ps) {
		
	}

	@Override
	protected void outputClassName(TableClassMapping table, PrintStream ps) {
		ps.println("public class "+table.getVoName()+" extends "+table.getApiVoName()+"{");
	}

	@Override
	protected void outputClassFiled(TableClassMapping table, PrintStream ps) {
		
	}

	@Override
	protected void outputMethod(TableClassMapping table, PrintStream ps) {
		for (ColumnFieldMapping col : table.getColFieldMappingList()) {
			ps.println(methodIndent+"@Override");
			String methodName = ReflectBeanUtils.fieldName2MethodName("get", col.getFieldName());
			ps.println(methodIndent+"public "+col.getFieldType()+" "+methodName+"(){");
			ps.println(methodBodyIndent+"return source."+methodName+"();");
			ps.println(methodIndent+")");
		}
	}
}
