package jat.studio.gamenews2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Toni on 01/12/2014.
 */
public class AcercaDe extends ActionBarActivity {

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";
    private int contadorToni = 0;
    private String url;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_activity);
        List<Map<String,?>> security = new LinkedList<Map<String,?>>();
        security.add(createItem("PanoramaGL", "por Javier Baez"));
        security.add(createItem("Picasso", "por Square, Inc."));
        security.add(createItem("BarcodeFraglib v2", "por ikermendi"));
        security.add(createItem("Android Page Curl", "por cags12"));

        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(this);
        adapter.addSection("Game News - Sistemas Multimedia", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[] { "Toni Rebollo", "Julio Zamora", "Alejandro Martínez" }));
        adapter.addSection("Librerías de código abierto", new SimpleAdapter(this, security, R.layout.list_complex,
                new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        contadorToni = contadorToni+1;
                        if(contadorToni>=10){
                            Toast.makeText(AcercaDe.this,"Oh no!\nHas encontrado mi secreto. Será mejor que no se lo cuentes a nadie... <3<3",Toast.LENGTH_LONG).show();
                            contadorToni = 0;
                        }
                        break;
                    case 5:
                        //PanoramaGL
                        url = "https://code.google.com/p/panoramagl-android/";
                        i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;
                    case 6:
                        //Picasso
                        url = "http://square.github.io/picasso/";
                        i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;

                    case 7:
                        //Barcodafraglib v2
                        url = "https://code.google.com/p/barcodefraglibv2/";
                        i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;

                    case 8:
                        //Page Curl
                        url = "https://github.com/cags12/android_page_curl";
                        i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                        break;
                }
            }
        });

    }
    public Map<String,?> createItem(String title, String caption) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }
}
