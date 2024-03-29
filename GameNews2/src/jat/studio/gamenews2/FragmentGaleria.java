package jat.studio.gamenews2;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

public class FragmentGaleria extends Fragment {
	
	private GridView gridView;
	private GridViewAdapter customGridAdapter;
    private View view;
    private ArrayList<String> imagenes;
    public static Vector<String> imagenPosicion = new Vector<String>();
	public FragmentGaleria(){
		
	}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view=null;
        view = inflater.inflate(R.layout.fragment_galeria, container,false);
        View getview = this.getView();
        imagenes = new ArrayList<String>();
        gridView = (GridView) view.findViewById(R.id.gridView);
        AsyncTask task=new Listar(getActivity(),view,this).execute();
        gridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,int position, long id) {
                //Toast.makeText(getActivity(),"Has seleccionado la foto: " + position ,Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),Visor360.class);
                i.putExtra("URL",imagenPosicion.get(position));
                ((MainActivity) getActivity()).startActivity(i);
            }
        });
        return view;
    }
    public void addImagen(String url){
        imagenes.add(url);
    }
    public void addImagen360(String url){
        imagenPosicion.add(url);
    }
    public void crearAdapter(){
        if(imagenes != null){
            if(getActivity() != null){
                customGridAdapter = new GridViewAdapter(getActivity(), R.layout.row_grid, imagenes);
            }
        }
    }
    public void setAdapter() {
        gridView.setAdapter(customGridAdapter);
    }


}
