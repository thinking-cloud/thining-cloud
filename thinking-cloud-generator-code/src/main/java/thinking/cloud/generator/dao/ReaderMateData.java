package thinking.cloud.generator.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import thinking.cloud.generator.bean.ColumnFieldMapping;
import thinking.cloud.generator.bean.TableClassMapping;
import thinking.cloud.generator.config.Config;
import thinking.cloud.generator.exception.GeneratorException;
import thinking.cloud.generator.utils.ConnectionUtils;

/**
 * 读取数据库元数据
 * 
 * @date 2021年3月8日
 */
public class ReaderMateData {
	private DatabaseMetaData mateData;

	public ReaderMateData(DatabaseMetaData mateData) {
		this.mateData = mateData;
	}

	/**
	 * 获取表的元数据
	 */
	public List<TableClassMapping> tableMatedata() {

		List<TableClassMapping> tableList = new LinkedList<>();
		String databaseName = Config.applicationConfig.getDataBaseName();
		try (ResultSet resultSet = mateData.getTables(databaseName, null, null, null)) {
			while (resultSet.next()) {
				String tableName = resultSet.getString("TABLE_NAME");
				String[] tableNames = Config.applicationConfig.getTableNames();
				b: if (tableNames != null) {
					for (String tn : tableNames) {
						if (tn.equalsIgnoreCase(tableName)) {
							break b;
						}
					}
					continue;
				}
				TableClassMapping table = new TableClassMapping(tableName);
				tableList.add(table);
			}
		} catch (SQLException e) {
			throw new GeneratorException("获取数据库中的表出现异常", e);
		}
		return tableList;
	}

	/**
	 * 获取列的元数据
	 */
	public TableClassMapping columnMateData(TableClassMapping table) {
		String dataBaseName = Config.applicationConfig.getDataBaseName();
		try (ResultSet resultSet = mateData.getColumns(dataBaseName, null, table.getTableName(), null)) {
			while (resultSet.next()) {
				String columnName = resultSet.getString("COLUMN_NAME");
				String columnType = resultSet.getString("TYPE_NAME");
				table.getColFieldMappingList()
						.add(new ColumnFieldMapping(columnType, columnName, table.getImportPackageSet()));
			}
			return table;
		} catch (Exception e) {
			throw new GeneratorException("获取数据库中的列出现异常", e);
		}
	}
}
