package jat.studio.gamenews2;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Toni on 24/11/2014.
 */
public class FragmentRevista extends Fragment {
    private View view;
    private ViewPager viewPager;
    private PageAdapter adaptador;
    List<Fragment> fragments;
    ArrayList<String> urlRevista = new ArrayList<String>();
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_revista, container,false);
        fragments = new Vector<Fragment>();
        viewPager = (ViewPager) view.findViewById(R.id.viewpagerRevista);
        AsyncTask task=new ListarRevistas(getActivity(),view,this).execute();
        //introducirRevista("http://game-news.url.ph/wp-content/uploads/2014/10/PORTADA.png");
        //introducirRevista("http://game-news.url.ph/wp-content/uploads/2014/10/PORTADA.png");
       //inicializar();
        return view;
    }
    public void introducirRevista(String urlPortada,String urlRevista){
        Fragment kk = new itemRevista();
        Bundle bundle = new Bundle();
        bundle.putString("urlImagenPortada", urlPortada);
        bundle.putString("urlRevista",urlRevista);
        kk.setArguments(bundle);
        fragments.add(android.support.v4.app.Fragment.instantiate(getActivity(), ((Object) kk).getClass().getName(),bundle));
    }

    public void inicializar() {
        adaptador = new PageAdapter(getActivity().getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adaptador);
    }
}
