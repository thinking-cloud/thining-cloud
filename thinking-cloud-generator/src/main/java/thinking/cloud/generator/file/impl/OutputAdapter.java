package thinking.cloud.generator.file.impl;

import java.io.PrintStream;
import java.util.List;

import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.utils.PackageNameUtils;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 输出adapter文件
 * @author zhouxinke
 * @date 2021年4月29日
 */
public class OutputAdapter extends OutputJavaFile{

    public OutputAdapter(String outputFilePath, List<TableClassMapping> tableClassMapping) {
    	super(outputFilePath, tableClassMapping);
    }

    @Override
    public String getFileName(TableClassMapping table) {
	return table.getAdapterName();
    }

    @Override
    protected String packageName() {
    	return PackageNameUtils.adapterPackage();

    }

    @Override
    protected void outputAnnotation(TableClassMapping table, PrintStream ps) {
	ps.println("@Adapter");
    }

    @Override
    protected void outputClassName(TableClassMapping table, PrintStream ps) {
		String generic = "<"+table.getEntityName()+", "+table.getVoName()+">";
		StringBuilder line = new StringBuilder();
		line.append("public ");
		line.append("class ");
		line.append(table.getAdapterName()+" ") ;
		line.append("extends ");
		line.append("BaseAdapter");
		line.append(generic);
		line.append("{\n");
		ps.println(line.toString());
    }

    @Override
    protected void outputClassFiled(TableClassMapping table, PrintStream ps) {
    	ps.println(methodIndent+"private "+table.getServiceName()+" "+ReflectBeanUtils.className2FieldName("", table.getServiceName())+";");
    }

	@Override
	protected void outputMethod(TableClassMapping table, PrintStream ps) {
		String serviceName = ReflectBeanUtils.className2FieldName("", table.getServiceName());
		ps.println(methodIndent+"public "+table.getVoName()+" add("+table.getBoName() +" bo){" );
		ps.println(methodBodyIndent+serviceName+".insert(bo);");
		ps.println(methodBodyIndent+"return entityConvert().convert(bo);");
		ps.println(methodIndent+"}");
		ps.println();
		ps.println(methodIndent+"public void delete("+table.getPk().getFieldType() +" id){" );
		ps.println(methodBodyIndent+serviceName+".delete(new "+table.getEntityName()+"(id));");
		ps.println(methodIndent+"}");
		ps.println();
		ps.println(methodIndent+"public int update("+table.getPk().getFieldType() +" id){" );
		ps.println(methodBodyIndent+"return " +serviceName+".delete(new "+table.getEntityName()+"(id));");
		ps.println(methodIndent+"}");
		ps.println();
		ps.println(methodIndent+"public "+table.getVoName()+" select("+table.getPk().getFieldType() +" id){" );
		ps.println(methodBodyIndent+ table.getEntityName()+" bo = "+serviceName+".selectByPK(id);");
		ps.println(methodBodyIndent+"return entityConvert().convert(bo);");
		ps.println(methodIndent+"}");
		ps.println();
		ps.println(methodIndent+"public Page<"+table.getVoName()+"> limit("+table.getLimitBoName() +" limitBo){" );
		ps.println(methodBodyIndent+ "Page<"+table.getEntityName()+"> page = "+serviceName+".selectByPK(id);");
		ps.println(methodBodyIndent+"this.limitConvert().limitToPage(limitBo, page);");
		ps.println(methodIndent+"}");
		ps.println();
	}
	
}
