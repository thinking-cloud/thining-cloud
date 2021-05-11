package thinking.cloud.generator;

import thinking.cloud.generator.service.GeneratorService;

/**
 * 
 * @author zhouxinke
 * @date 2021年3月8日
 */
public class ApplicationStart {
	
	public static void main(String[] args) {
		try {
			GeneratorService generatorService = new GeneratorService();
			generatorService.generatorService();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
