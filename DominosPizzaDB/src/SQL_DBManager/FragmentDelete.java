package SQL_DBManager;

import com.Dominospizzadb.dominospizzadb.R;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentDelete extends Fragment implements View.OnClickListener {
    //Fragment para el borrado de algun dato
    private SQLiteDatabase data2;
    EditText e;
    Button b;
    OnInsert listener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);
        e=(EditText) rootView.findViewById(R.id.eDelBuscar);
        b=(Button) rootView.findViewById(R.id.bDelBuscar);
        b.setOnClickListener(this);
        return rootView;
    }

    public void setdatabase(SQLiteDatabase d2){data2=d2;}

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.bDelBuscar:
                String tabla="Sedes_Medellin";
                String selection="sede = ?";
                String [] selectionArgs={e.getText().toString()};
                if(data2.delete(tabla,selection,selectionArgs)==0)
                {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Ha ocurrido un error, no se ha borrado nada",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    listener.Insert(data2);
                    Toast.makeText(getActivity().getApplicationContext(),
                            "Se ha borrado la fila satisfactoriamente",
                            Toast.LENGTH_LONG).show();
                }
                break;

        }
        borrar();
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

    public void borrar()
    {
        e.setText("");
    }
}