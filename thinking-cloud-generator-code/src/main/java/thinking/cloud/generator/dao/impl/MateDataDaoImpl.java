package thinking.cloud.generator.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import thinking.cloud.generator.bean.Column;
import thinking.cloud.generator.bean.Table;
import thinking.cloud.generator.config.Config;
import thinking.cloud.generator.dao.MateDataDao;
import thinking.cloud.generator.exception.GeneratorException;

/**
 * 
 * <P>
 * </P>
 * @author zhouxinke
 * @date 2021年3月8日
 */
public class MateDataDaoImpl extends MateDataDao {
	

	public List<Table> tableMatedata() {
		List<Table> tableList = new LinkedList<>();
		ResultSet resultSet = null;
		try {
			String databaseName = Config.getDataBaseName();
			resultSet = metaData.getTables(databaseName, null, null, null);
			while(resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				String[] tableNames = Config.getTableNames();
				b:if(tableNames != null) {
					for (String tn : tableNames) {
						if(tn.equalsIgnoreCase(tableName)) {
							break b;
						}
					}
					continue;
				}
				
				Table table = new Table(tableName);			
				tableList.add(table);
			}
		} catch (SQLException e) {
			throw new GeneratorException("获取数据库中的表出现异常", e);
		}finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tableList;
	}
	
	public List<Column> columnMateData(Table table){
		List<Column> columnList = new LinkedList<>();
		ResultSet resultSet = null;
		try {
			String dataBaseName = Config.getDataBaseName();
			resultSet = metaData.getColumns(dataBaseName, null, table.getTableName(), null);
			while(resultSet.next()) {
				String columnName = resultSet.getString("COLUMN_NAME");
//				columnList.add(new Column(resultSet.getS));
				
			}
			return columnList;
		}catch (Exception e) {
			throw new GeneratorException("获取数据库中的列出现异常", e);
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		MateDataDaoImpl meMateDataDaoImpl = new MateDataDaoImpl();
		meMateDataDaoImpl.init();
		List<Table> tableMatedata = meMateDataDaoImpl.tableMatedata();
		for (Table table : tableMatedata) {
			List<Column> columnMateData = meMateDataDaoImpl.columnMateData(table);
		}
		meMateDataDaoImpl.close();
	}
}
