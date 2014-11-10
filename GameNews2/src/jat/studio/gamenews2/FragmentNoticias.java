package jat.studio.gamenews2;

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
	public static ArrayList<String> list_titles;
    private static View view;

	public FragmentNoticias(){
		
	}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_noticias, container,false);
        list_titles=new ArrayList<String>();
        AsyncTask task=new ProgressTask(this.getActivity().getApplicationContext(),"http://game-news.url.ph/feed/",view).execute();

		return view;
	}
    public static void mostrarLista(Context context){
        ArrayAdapter<String> adaptador=new ArrayAdapter<String>(context,R.layout.fragment_noticias,FragmentNoticias.list_titles);
        ListView list_titles=(ListView)view.findViewById(R.id.listView);
        list_titles.setAdapter(adaptador);
    }
}
