package com.Dominospizzadb.dominospizzadb;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import SQL_DBManager.FragmentDelete;
import SQL_DBManager.FragmentInsert;
import SQL_DBManager.FragmentSearch;
import SQL_DBManager.FragmentUpdate;
import SQL_DBManager.FragmentView;
import SQL_DBManager.OnInsert;
import SQL_DBManager.SQL_DBCreator;
import SQL_DBManager.SQL_Table;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Dominospizzadb extends FragmentActivity implements OnInsert {
	LatLng a;
    PagerAdapter mSectionsPagerAdapter;
    ArrayList <Fragment> fragmentList;
    ViewPager mViewPager;
    FragmentSearch fragmentSearch;
    FragmentInsert fragmentInsert;
    FragmentDelete fragmentDelete;
    FragmentUpdate fragmentUpdate;
    FragmentView   fragmentView;

    
    SQLiteDatabase data;
    String filename;
    String path;
    
    
    GoogleMap map;
	double lat = 6.2682176, lng = -75.5677033;//UNIVERSITY
	LatLng positionUdeA = new LatLng(lat, lng);
	
	ArrayList<LatLng> points;
	ArrayList<String> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dominospizzadb);
        //fromfile();
        fromstart();

        fragmentSearch=new FragmentSearch();
        fragmentInsert=new FragmentInsert();
        fragmentDelete=new FragmentDelete();
        fragmentUpdate=new FragmentUpdate();
        fragmentView=new FragmentView();
        

        fragmentSearch.setdatabase(data);
        fragmentInsert.setdatabase(data);
        fragmentDelete.setdatabase(data);
        fragmentUpdate.setdatabase(data);
        fragmentView.setdata(data);

        fragmentList=new ArrayList<Fragment>();

        fragmentList.add(fragmentSearch);
        fragmentList.add(fragmentInsert);
        fragmentList.add(fragmentDelete);
        fragmentList.add(fragmentUpdate);
        fragmentList.add(fragmentView);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),fragmentList);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
//        Toast.makeText(this,"Ruta: "+data.getPath(),Toast.LENGTH_LONG).show();

        //points= new ArrayList<LatLng>();

        
        
        map = ((MapFragment)getFragmentManager().
				findFragmentById(R.id.map)).getMap();

		map.setMyLocationEnabled(true);
		map.moveCamera(CameraUpdateFactory.
				newLatLngZoom(positionUdeA, 12));

		showMarkers(points);
    
    }
    

	/* Method to show markers */
	public void showMarkers(ArrayList<LatLng> points) {	
		map.clear();
        points = getDotsFromDB();
        names = getNamesFromDB();
		map.addMarker(new MarkerOptions().
				title("UdeA").
				snippet("Lugar de referencia").
				position(positionUdeA));
				//icon(BitmapDescriptor.class));
		for (int i = 0; i < points.size(); i++) {


			map.addMarker(new MarkerOptions().
					title("Domino's Pizza").
					snippet(names.get(i)).
					position(points.get(i)));
		}
		
	}
    private void copyDataBase() throws IOException {

        InputStream myInput = getApplicationContext().getAssets().open(filename);

        String outFileName = path;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    @Override
    public void Insert(SQLiteDatabase dd) {
//        Toast.makeText(this,"valor recibido",Toast.LENGTH_LONG).show();
        data=dd;
        mViewPager.getAdapter().notifyDataSetChanged();
        showMarkers(points);
    }
    
    public void fromfile()
    {
        filename="Sedes.s3db";
        path=getApplication().getFilesDir().getPath().toString()+"/"+filename;
        try {
            copyDataBase();
            data=SQLiteDatabase.openOrCreateDatabase(path,null);
        } catch (IOException e) {
//            Toast.makeText(this,"app: database error: "+path,Toast.LENGTH_LONG).show();
        }
    }
    
    public void fromstart()
    {
        String[] nameCols = {"id integer primary key autoincrement","sede text","latitud float","longitud float"};
        SQL_Table tableProvicional = new SQL_Table("Sedes_Medellin",nameCols);
        
        SQL_DBCreator dbCreator=new SQL_DBCreator("Sedes_Dominos7",tableProvicional,this,null,1);
        data=dbCreator.getWritableDatabase();
        
//        Toast.makeText(this,"Nombre: "+dbCreator.getDatabaseName().toString(),Toast.LENGTH_LONG).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dominospizzadb, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public ArrayList<LatLng> getDotsFromDB(){
    	ArrayList<LatLng> AuxArray = new ArrayList<LatLng>();
    	LatLng auxLatLng ;
        String tabla="Sedes_Medellin";
        Cursor cursor=data.rawQuery("select * from "+tabla,null);
        String ID,sede,latitud,longitud;
        if(cursor.moveToFirst())
        {
            do{

            	ID =cursor.getString(0);
            	sede =cursor.getString(1);
            	latitud =cursor.getString(2);
            	longitud =cursor.getString(3);
                auxLatLng = new LatLng(Float.parseFloat(latitud), Float.parseFloat(longitud));
            	AuxArray.add(auxLatLng);
                Log.i("Dots","Added to array DOTS"+latitud+" "+longitud);

            }while(cursor.moveToNext());
        }
    	return AuxArray;
    	
    }
    
    public ArrayList<String> getNamesFromDB(){
    	ArrayList<String> AuxArray = new ArrayList<String>();
        String tabla="Sedes_Medellin";
        Cursor cursor=data.rawQuery("select * from "+tabla,null);
        String ID,sede,latitud,longitud;
        if(cursor.moveToFirst())
        {
            do{

            	ID =cursor.getString(0);
            	sede =cursor.getString(1);
            	latitud =cursor.getString(2);
            	longitud =cursor.getString(3);
            	AuxArray.add(sede);
                Log.i("Dots","Added to array NAMES "+latitud+" "+longitud);

            }while(cursor.moveToNext());
        }
    	return AuxArray;
    	
    }
}
