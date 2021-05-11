package thinking.cloud.generator.file.impl;

import java.io.PrintStream;
import java.util.List;

import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.config.GeneratorContext;
import thinking.cloud.generator.enums.PropKey;
import thinking.cloud.generator.file.OutputJavaFile;
import thinking.cloud.generator.utils.PackageNameUtils;
import thinking.cloud.utils.reflect.ReflectBeanUtils;

/**
 * 输出 api接口
 * @author zhouxinke
 * @date 2021年4月29日
 */
public class OutputApi extends OutputJavaFile{

	public OutputApi(String outputFilePath, List<TableClassMapping> tableClassMapping) {
		super(outputFilePath, tableClassMapping);
	}

	@Override
	public String getFileName(TableClassMapping table) {
		return table.getApiName();
	}

	@Override
	protected void outputPackage(TableClassMapping table, PrintStream ps) {
		String packageName = PackageNameUtils.apiPackage();
		ps.println("import "+packageName+";");
	}

	@Override
	protected void outputAnnotation(TableClassMapping table, PrintStream ps) {
		ps.println("@FeignClient(value=Constant.APPLICATION_NAME,configuration = {FeignGetJson2GetParams.class} )");
		String prefix = GeneratorContext.applicationConfig.getProperty(PropKey.CLASS_NAME_PREFIX.propKey());
		String restpath = ReflectBeanUtils.snakeCase2RestPath(prefix, "_", table.getTableName());
		ps.println("@RequestMapping(\""+restpath+"\")");
		ps.println("@Api(tags=\"自动生成, 手动补充\")");
		
	}

	@Override
	protected void outputClassName(TableClassMapping table, PrintStream ps) {
		ps.println("public interface "+table.getApiName()+" {");
	}

	@Override
	protected void outputClassFiled(TableClassMapping table, PrintStream ps) {
		
	}

	@Override
	protected void outputMethod(TableClassMapping table, PrintStream ps) {
		String boName = table.getApiVoName();
		String pkType = table.getPk().getFieldType();
		String voName = table.getApiVoName();
		String param = null;
		ps.println(methodIndent+"@PostMapping(\"\")");
		ps.println(methodIndent+"@ApiOperation(value=\"\",httpMethod = \"POST\",  notes = \"\")");
		param = "@RequestBody @Validated "+boName+" bo";
		ps.println(methodIndent+"public Message<"+voName+"> save("+param+");");
		ps.println();
		
		ps.println(methodIndent+"@DeleteMapping(\"/{id}\")");
		ps.println(methodIndent+"@ApiOperation(value=\"\",httpMethod = \"DELETE\",  notes = \"\")");
		param = "@PathVariable(\"id\") "+pkType+" id"; 
		ps.println(methodIndent+"public Message<"+voName+"> delete("+param+");");
		ps.println();
		
		ps.println(methodIndent+"@PutMapping(\"/{id}\")");
		ps.println(methodIndent+"@ApiOperation(value=\"\",httpMethod = \"PUT\",  notes = \"\")");
		param = "@PathVariable(\"id\") "+pkType+" id, @Validated @RequestBody "+boName+" bo";
		ps.println(methodIndent+"public Message<"+voName+"> update("+param+");");
		ps.println();
		
		ps.println(methodIndent+"@GetMapping(\"/{id}\")");
		ps.println(methodIndent+"@ApiOperation(value=\"\",httpMethod = \"GET\",  notes = \"\")");
		param =  "@PathVariable(\"id\") "+pkType+" id";
		ps.println(methodIndent+"public Message<"+voName+"> select("+param+");");
		ps.println();
		
		ps.println(methodIndent+"@GetMapping(\"\")");
		ps.println(methodIndent+"@ApiOperation(value=\"\",httpMethod = \"GET\",  notes = \"\")");
		param = "@Validated "+table.getLimitBoName()+" limitbo";
		ps.println(methodIndent+"public Message<Page<"+voName+">> page("+param+");");
		ps.println();
		
	}
	
}
