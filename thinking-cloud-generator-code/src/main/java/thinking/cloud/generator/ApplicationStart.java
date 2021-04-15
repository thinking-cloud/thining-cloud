package thinking.cloud.generator;

import java.util.List;

import thinking.cloud.generator.bean.ColumnFieldMapping;
import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.dao.ReaderMateData;
import thinking.cloud.generator.service.GeneratorService;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
public class ApplicationStart {
	
	public static void main(String[] args) {
		GeneratorService generatorService = new GeneratorService();
		generatorService.generatorService();
		
		
	}
}
