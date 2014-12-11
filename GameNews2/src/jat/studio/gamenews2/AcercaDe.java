package jat.studio.gamenews2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.VideoView;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Toni on 01/12/2014.
 */
public class AcercaDe extends ActionBarActivity {

    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CAPTION = "caption";
    private int contadorToni = 0;
    private int contadorJuliyo = 0;
    private int contadorAlejandro = 0;
    private String url;
    private Intent i;
    private MediaPlayer mediaPlayer;
    private MediaPlayer mediaAlejandro;
    private android.support.v7.app.ActionBar actBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acerca_activity);
        List<Map<String,?>> security = new LinkedList<Map<String,?>>();
        security.add(createItem("PanoramaGL", "por Javier Baez"));
        security.add(createItem("Picasso", "por Square, Inc."));
        security.add(createItem("BarcodeFraglib v2", "por ikermendi"));
        security.add(createItem("Android Page Curl", "por cags12"));
        security.add(createItem("Jsoup","por Jonathan Hedley"));
        // create our list and custom adapter
        SeparatedListAdapter adapter = new SeparatedListAdapter(this);
        adapter.addSection("Game News - Sistemas Multimedia", new ArrayAdapter<String>(this,
                R.layout.list_item, new String[] { "Toni Rebollo", "Julio Zamora", "Alejandro Martínez" }));
        adapter.addSection("Librerías de código abierto", new SimpleAdapter(this, security, R.layout.list_complex,
                new String[] { ITEM_TITLE, ITEM_CAPTION }, new int[] { R.id.list_complex_title, R.id.list_complex_caption }));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            setSupportActionBar(toolbar);

            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_drawer);

        }

        ListView list = (ListView)findViewById(R.id.listView);
        list.setAdapter(adapter);
        mediaAlejandro = MediaPlayer.create(AcercaDe.this, R.raw.hit);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 1:
                        contadorToni = contadorToni+1;
                        if(contadorToni>=10){
                            Toast.makeText(AcercaDe.this,"Dj Rebollo In Session!!",Toast.LENGTH_LONG).show();
                            mediaPlayer = MediaPlayer.create(AcercaDe.this, R.raw.toni);
                            mediaPlayer.start();
                            contadorToni = 0;
                        }
                        break;
                    case 2:
                        contadorJuliyo++;
                        if(contadorJuliyo >= 10){
                            final Dialog dialog = new Dialog(AcercaDe.this);
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            dialog.setContentView(R.layout.easter_video);
                            dialog.setCancelable(false);
                            dialog.show();
                            final Handler handler = new Handler();
                            final Runnable Update = new Runnable() {
                                public void run() {
                                    dialog.setCancelable(true);

                                }
                            };
                            final Timer timador;
                            timador = new Timer();
                            timador.schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    handler.post(Update);
                                    timador.cancel();
                                }
                            }, 4000, 4000);
                            WindowManager.LayoutParams lp = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
                            lp.copyFrom(dialog.getWindow().getAttributes());

                            lp.screenBrightness = 1.0f;

                            final VideoView videoview = (VideoView) dialog.findViewById(R.id.video_player_view);
                            Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video1);
                            videoview.setVideoURI(uri);
                            videoview.start();
                            videoview.setZOrderOnTop(true);

                            dialog.getWindow().setAttributes(lp);
                            contadorJuliyo = 0;
                        }
                        break;
                    case 3:
                        contadorAlejandro++;
                        if(contadorAlejandro>=10){

                            contadorAlejandro=0;
                        }else{
                            mediaAlejandro.seekTo(0);
                            mediaAlejandro.start();
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
                    case 9:
                        url = "http://jsoup.org/";
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
