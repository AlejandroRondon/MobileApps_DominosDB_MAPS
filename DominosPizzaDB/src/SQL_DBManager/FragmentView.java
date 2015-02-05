package SQL_DBManager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import com.Dominospizzadb.dominospizzadb.R;

public class FragmentView extends ListFragment {
    private SQLiteDatabase data4;
    public void setdata(SQLiteDatabase d4){data4=d4;}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ArrayList <String> resultado=new ArrayList<String>();
        String tabla="Sedes_Medellin";
        Cursor cursor=data4.rawQuery("select * from "+tabla,null);
        
        if(cursor.moveToFirst())
        {
        	
            do{
            	String res="sede: ";
                for(int i=0;i<cursor.getColumnCount();i++)
                {
                    res=res+"\t\t"+cursor.getString(i);
                }
                resultado.add(res);
                res="";
            }while(cursor.moveToNext());
        }

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, resultado));
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        this.getListView().setBackgroundColor(getResources().getColor(R.color.Aquamarine));

        super.onActivityCreated(savedInstanceState);
    }
}
