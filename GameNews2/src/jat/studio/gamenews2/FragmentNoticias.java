package jat.studio.gamenews2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class FragmentNoticias extends Fragment{
    public static ArrayList<Noticia> noticia;
    private static View view;

	public FragmentNoticias(){

		
	}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_noticias, container,false);
        noticia=new ArrayList<Noticia>();
        AsyncTask task=new ProgressTask(this.getActivity().getApplicationContext(),"http://game-news.url.ph/feed/",view).execute();

		return view;
	}
    public static void mostrarLista(Context context){
        AdaptadorNoticias adaptador=new AdaptadorNoticias(context);
        ListView listaNoticias=(ListView)view.findViewById(R.id.listView);
        listaNoticias.setAdapter(adaptador);


    }
    private static class AdaptadorNoticias extends ArrayAdapter{
        private Context context;

        private AdaptadorNoticias(Context context){
            super(context,R.layout.noticias,noticia);
            this.context=context;

        }

    }
}
