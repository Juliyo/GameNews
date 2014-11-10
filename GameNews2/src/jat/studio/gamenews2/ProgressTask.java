package jat.studio.gamenews2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Julio on 10/11/2014.
 */
public class ProgressTask extends AsyncTask<String,Void,Boolean>{
    private String str_url_rss;
    private Context context;
    private View mView;
    private ProgressBar circulo;

    public ProgressTask(Context c, String url_rss, View viewNoticias) {
        this.context=c;
        this.str_url_rss=url_rss;
        mView=viewNoticias;
    }
    protected void onPreExecute(){
        if(mView!=null){
            circulo=(ProgressBar)mView.findViewById(R.id.cargando);
            circulo.setVisibility(View.VISIBLE);
        }
    }
    protected void onPostExectute(final Boolean success){
        if(success){
            if(mView!=null){
                circulo.setVisibility(View.INVISIBLE);
            }
        }else{
            if(mView!=null){
                circulo.setVisibility(View.INVISIBLE);
                Toast.makeText(context,"Error de conexion",Toast.LENGTH_LONG).show();
            }
        }


    }
    @Override
    protected Boolean doInBackground(final String... strings) {
        URL url= null;
        try {
            url = new URL(str_url_rss);
            InputStream in=url.openStream();



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;
    }
}
