package SQL_DBManager;

public class SQL_Table {
	private String tableName;
	private String namesCols[];
	public SQL_Table(String tableName,String namesCols[]) {
		// TODO Auto-generated constructor stub
		this.tableName = tableName;
		this.namesCols = namesCols;
	}
	public String getTableName() {
		return tableName;
	}
	public String[] getNamesCols() {
		return namesCols;
	}
	
	
	
	
	
}
