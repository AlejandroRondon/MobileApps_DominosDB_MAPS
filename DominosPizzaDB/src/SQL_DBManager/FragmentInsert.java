package SQL_DBManager;
import com.Dominospizzadb.dominospizzadb.R;
import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentInsert extends Fragment implements View.OnClickListener {

//Fragmento para insertar nuevos datos en la base de datos
    SQLiteDatabase data2;
    private OnInsert listener;
    public void setdatabase(SQLiteDatabase d1){data2=d1;}
    EditText nombre,latitud,longitud;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 }

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_insert, container, false);
        nombre=(EditText) rootView.findViewById(R.id.eNombre);
        latitud=(EditText) rootView.findViewById(R.id.eLatitud);
        longitud=(EditText) rootView.findViewById(R.id.eLongitud);
        //nota3=(EditText) rootView.findViewById(R.id.eNota3);
        Button insertar=(Button) rootView.findViewById(R.id.bInsertar);
        insertar.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listener = (OnInsert) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.bInsertar:
                String tabla="Sedes_Medellin";
                ContentValues valor=new ContentValues();
                valor.put("sede",nombre.getText().toString());
                valor.put("latitud",Float.parseFloat(latitud.getText().toString()));
                valor.put("longitud",Float.parseFloat(longitud.getText().toString()));

                if(data2.insert(tabla,null,valor)==-1)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Ocurrio un error," +
                            " no se inserto el valor",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"valor insertado" +
                            " correctamente",Toast.LENGTH_LONG).show();
                    listener.Insert(data2);
                }

            break;
        }
        borrar();
    }
    public void borrar()
    {
        nombre.setText("");
        latitud.setText("");
        longitud.setText("");
    }
}
