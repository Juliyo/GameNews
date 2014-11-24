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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentNoticias extends Fragment{
    private ArrayList<Noticia> noticia;
    private View view;
    private ListView listaNoticias;

    public FragmentNoticias(){

	}
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_noticias, container,false);
        noticia=new ArrayList<Noticia>();
        AsyncTask task=new ProgressTask(getActivity(),"http://game-news.url.ph/feed/",view,this).execute();
        /*listaNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
		return view;
	}
    public void mostrarLista(Activity activity){
        AdaptadorNoticias adaptador=new AdaptadorNoticias(activity);
        listaNoticias=(ListView)view.findViewById(R.id.listView);
        listaNoticias.setAdapter(adaptador);
    }

    public void setImage(String image) {
        noticia.get(noticia.size()-1).setImage(image);
    }

    public void setDescription(String description) {
        noticia.get(noticia.size()-1).setDescription(description);
    }

    public void setTitle(String title) {
        noticia.add(new Noticia());
        noticia.get(noticia.size()-1).setTitle(title);
    }

    private class AdaptadorNoticias extends ArrayAdapter{
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
            ImageView image=(ImageView)item.findViewById(R.id.imageView);
            Picasso.with(context).load(noticia.get(position).getImage()).resize(275,200).into(image);
            return(item);

        }

    }
}
