package async;

public class B_SendData {

	public String tableName, jobid, columnName, typeName, typeValue, fileName;

	/**
	 * 
	 * @param tableName
	 * @param jobid
	 * @param columnName
	 * @param typeName
	 * @param typeValue
	 * @param fileName
	 */
	public B_SendData(String tableName, String jobid, String columnName,
			String typeName, String typeValue, String fileName) {
		super();
		this.tableName = tableName;
		this.jobid = jobid;
		this.columnName = columnName;
		this.typeName = typeName;
		this.typeValue = typeValue;
		this.fileName = fileName;
	}
}
