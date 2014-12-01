package jat.studio.gamenews2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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

    }
    public Map<String,?> createItem(String title, String caption) {
        Map<String,String> item = new HashMap<String,String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CAPTION, caption);
        return item;
    }
}
