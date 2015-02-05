package SQL_DBManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQL_DBCreator extends SQLiteOpenHelper {
    final static String SQLQUERYType_createTable = "create table ";

	
    SQLiteDatabase db;
    SQL_Table initialTable;
    
    public SQL_DBCreator( String nameDB, SQL_Table initialTable, Context context,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nameDB, factory, version);
        this.initialTable = initialTable;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    this.db = db;	//get the data base created
	tableCreator(initialTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    
    public void tableCreator(SQL_Table table){
    	int i;
    	String TABLE_NAME=initialTable.getTableName();
    	/******************************************************************/
    	StringBuilder query = new StringBuilder();
    	query.append(SQLQUERYType_createTable);
    	query.append(" ");
    	query.append(TABLE_NAME);
    	query.append("(");
    	for(i=0; i<initialTable.getNamesCols().length;i++){
    		query.append(initialTable.getNamesCols()[i]);
    		if(i != initialTable.getNamesCols().length-1){
    			query.append(",");
    		}
    	
    	}
    	query.append(")");
    	/******************************************************************/
    	Log.i("Query" ,query.toString());
        db.execSQL(query.toString());
    }
}
