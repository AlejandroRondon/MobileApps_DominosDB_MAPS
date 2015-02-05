package SQL_DBManager;

import com.Dominospizzadb.dominospizzadb.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentSearch extends Fragment implements View.OnClickListener {
    //FRAGMENT PARA SELECT
    private SQLiteDatabase data1;
    EditText e;
    TextView t;
    Button b;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        Button b=(Button) rootView.findViewById(R.id.bBuscar);
        e=(EditText) rootView.findViewById(R.id.eBuscar);
        t=(TextView) rootView.findViewById(R.id.sInfo);
        b.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public void setdatabase(SQLiteDatabase d1){data1=d1;}

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bBuscar:

                String selection="sede = ?";
                String [] columnas={"sede","latitud","longitud"};
                String tabla="Sedes_Medellin";
                String resultado="";
                String [] selectionArgs={e.getText().toString()};
                Cursor c  = data1.query(tabla,columnas,selection,selectionArgs,null,null,null);

                if (c.moveToFirst()) {
                    do{
                        for (int i = 0; i < c.getColumnCount(); i++)
                            resultado=resultado+c.getString(i) + "\t";
                        resultado=resultado+"\n";
                    }while(c.moveToNext());
                }
                else
                {
                    resultado="No existe el dato buscado";
                }

                 t.setText(resultado.toString());
                Toast.makeText(getActivity().getApplicationContext(),String.
                        valueOf(c.moveToFirst())
                        +String.valueOf(c.getCount()),Toast.LENGTH_LONG).show();

                break;
        }
        borrar();
    }
    public void borrar()
    {
        e.setText("");
    }
}
