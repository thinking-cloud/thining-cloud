//package thinking.cloud.generator.dao;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.LinkedList;
//import java.util.List;
//
//import org.dom4j.Document;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//
//import lombok.AllArgsConstructor;
//import thinking.cloud.generator.bean.ColumnFieldMapping;
//import thinking.cloud.generator.bean.TableClassMapping;
//import thinking.cloud.generator.contant.TemplateFileContants;
//import thinking.cloud.generator.exception.GeneratorException;
//
///**
// * 
// * @author zhouxinke
// * @date 2021年4月8日
// */
//@AllArgsConstructor
//public class GeneratorMapperXml {
//	private String mapperXmlTemplatePath;
//	private String outputFilePath;
//	private TableClassMapping[] tableClassMapping;
//	private TemplateFileContants  templateFileContants = new TemplateFileContants();
//
//
//	
//	public void generatorXml() {
//		SAXReader mapperTemplateXml = new SAXReader();
//		
//		try (BufferedReader mapperXmlTemplateReader = new BufferedReader(new FileReader(mapperXmlTemplatePath))) {
//			try (BufferedWriter mapperXmlWriter = new BufferedWriter(new FileWriter(outputFilePath))) {
//				Document template = mapperTemplateXml.read(mapperXmlTemplateReader);
//				
//				for (TableClassMapping table : tableClassMapping) {
//					Element template_root = template.getRootElement();
//		
//					
//					
//					String line = null;
//					while ((line = mapperXmlTemplateReader.readLine()) != null) {
//						if(line.trim().startsWith("</foreach-column-template>")) {
//							continue;
//						}else if(line.trim().startsWith("<foreach-column-template")) {
//							
//						}else {
//							line = tableClassMapping(table, line);
//						}
//
//						mapperXmlWriter.write(line);
//						mapperXmlWriter.newLine();
//					}
//				}
//			}
//		} catch (Exception e) {
//			throw new GeneratorException(e);
//		}
//	}
//	
//	/**
//	 * 处理tableClassMaping 对应的值
//	 * @param table 
//	 * @param line
//	 * @return
//	 */
//	private String tableClassMapping(TableClassMapping table, String line) {
//		return lineReplace(table, line);
//		
//	}
//	
//	private String[] columnFieldMapping(ColumnFieldMapping[] columns, String line) {
//		
//		int start = line.indexOf("type=\"");
//		int end = line.indexOf("\"",start);
//		String type = line.substring(start,end);
//		
//		start = line.indexOf("template_attr='");
//		
//		List<String> lineList = new LinkedList<>();
//		
//		switch (type) {
//		case TemplateFileContants.MAPPER_RESULT:
//			for (ColumnFieldMapping col : columns) {
//				
//				lineList.add(lineReplace(col,TemplateFileContants.MAPPER_RESULT));
//			}
//			break;
//
//		default:
//			break;
//		}
//		if(type.equals("result")>=0) {
//
//		}else if(type.equals("association")>=0) {
//			
//			// TODO FKmapper
//		}else if(type.equals("columns_all")){
//			
//		}
//	}
//	
//	/**
//	 * 替换模板中的表达式
//	 * @param obj
//	 * @param line
//	 * @return
//	 */
//	public String lineReplace(Object obj, String line) {
//		int start = line.indexOf("%{");
//		int end = 0;
//		String var = null;
//		while(start != -1) {
//			try {
//				// 获取表达式
//				end = line.indexOf("}", start);
//				var = line.substring(start, end);
//				// 通过常量获取对应方法
//				Field field = templateFileContants.getClass().getField(var);
//				Object methodName = field.get(templateFileContants);
//				// 获取表达式对应的值
//				Method method = obj.getClass().getMethod(methodName.toString());
//				String text = method.invoke(obj).toString();
//				// 替换为对应的值
//				line = line.replaceAll("%{"+var+"}", text);
//				
//			} catch (NoSuchFieldException e) {
//				System.out.println("mapper-xml模板中出现未试别的表达式:%{"+var+"}");
//			} catch (Exception e) {
//				throw new GeneratorException(e);
//			}
//		}
//		return line;
//	}
//}
