package jat.studio.gamenews2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Toni on 24/11/2014.
 */
public class itemRevista extends Fragment {
    private View view;
    private ImageView portada;
    private Bundle bundle;
    private String urlPortada;
    private String urlRevista;
    @Override
    public void onCreate( Bundle save) {
        super.onActivityCreated(save);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_revista, container,false);
        portada = (ImageView)view.findViewById(R.id.portadaRevista);
        bundle = this.getArguments();
        if(bundle != null){
            urlPortada=bundle.getString("urlImagenPortada")+".png";
            urlRevista = bundle.getString("urlRevista");
            Picasso.with(getActivity()).load(urlPortada).into(portada);
        }
        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(),RevistaActivity.class);
                i.putExtra("urlRevista", urlRevista);
                ((MainActivity) getActivity()).startActivity(i);
            }
        });
        return view;
    }
}
