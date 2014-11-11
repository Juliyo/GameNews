package jat.studio.gamenews2;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentNoticias extends Fragment{
    public static ArrayList<Noticia> noticia;
    private static View view;

	public FragmentNoticias(){

		
	}

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_noticias, container,false);
        noticia=new ArrayList<Noticia>();
        AsyncTask task=new ProgressTask(getActivity(),"http://game-news.url.ph/feed/",view).execute();

		return view;
	}
    public static void mostrarLista(Activity activity){
        AdaptadorNoticias adaptador=new AdaptadorNoticias(activity);
        ListView listaNoticias=(ListView)view.findViewById(R.id.listView);
        listaNoticias.setAdapter(adaptador);


    }
    private static class AdaptadorNoticias extends ArrayAdapter{
        private Activity context;

        private AdaptadorNoticias(Activity context){
            super(context,R.layout.noticias,noticia);
            this.context=context;

        }
        public View getView(int position,View convertView, ViewGroup parent){
            LayoutInflater inflater=context.getLayoutInflater();
            View item=inflater.inflate(R.layout.noticias,null);
            TextView titulo=(TextView)item.findViewById(R.id.textViewTitle);
            titulo.setText(noticia.get(position).getTitle());
            TextView descripcion=(TextView)item.findViewById(R.id.textViewDesc);
            descripcion.setText(noticia.get(position).getDescription());

            return(item);

        }

    }
}
