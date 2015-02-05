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


public class FragmentUpdate extends Fragment implements View.OnClickListener {

//Fragmento para modificar datos en la base de datos
    SQLiteDatabase data3;
    private OnInsert listener;
    public void setdatabase(SQLiteDatabase d3){data3=d3;}
    EditText sede,latitud,longitud;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 }

 @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);
        sede=(EditText) rootView.findViewById(R.id.eSede);
        latitud=(EditText) rootView.findViewById(R.id.eLatitud);
        longitud=(EditText) rootView.findViewById(R.id.eLongitud);

        Button sobreescribir=(Button) rootView.findViewById(R.id.bSobreescribir);
        sobreescribir.setOnClickListener(this);
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
            case R.id.bSobreescribir:
                ContentValues valor=new ContentValues();
                String tabla="Sedes_Medellin";
                String selection="sede = ?";
                String [] selectionArgs={sede.getText().toString()};

                valor.put("sede",sede.getText().toString());
                valor.put("latitud",Float.parseFloat(latitud.getText().toString()));
                valor.put("longitud",Float.parseFloat(longitud.getText().toString()));
            if(data3.update(tabla,valor,selection,selectionArgs)==0)
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Ocurrio un error," +
                            " no se modifico el valor",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"valor modificado" +
                            " correctamente",Toast.LENGTH_LONG).show();
                    listener.Insert(data3);
                }
           break;
        }
        borrar();
    }
    public void borrar()
    {
        sede.setText("");
        latitud.setText("");
        longitud.setText("");
    }
}
