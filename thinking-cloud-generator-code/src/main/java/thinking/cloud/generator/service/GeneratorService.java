package thinking.cloud.generator.service;

import java.util.List;

import thinking.cloud.generator.bean.ColumnFieldMapping;
import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.dao.ReaderMateData;
import thinking.cloud.generator.utils.ConnectionUtils;

/**
 * 业务实现类
 * @author zhouxinke
 * @date 2021年4月8日
 */
public class GeneratorService {
	public void generatorService() {
		try(ConnectionUtils connectionUtils = new ConnectionUtils()){
			ReaderMateData metaData = new ReaderMateData(connectionUtils.getMetaData());
			List<TableClassMapping> tableMatedata = metaData.tableMatedata();
			for (TableClassMapping table : tableMatedata) {
				System.out.println(table.getTableName());
				metaData.columnMateData(table);
				for (ColumnFieldMapping column : table.getColFieldMappingList()) {
					System.out.println("--->"+column.getColumnName()+" "+ column.getFieldType() +" "+column.getFieldName());
				}
			}
		}
	}
}
