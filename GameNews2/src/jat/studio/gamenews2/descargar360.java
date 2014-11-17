package jat.studio.gamenews2;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Toni on 17/11/2014.
 */
public class descargar360 extends AsyncTask<String,Void,Boolean> {
    private URL direccion;
    private View mView;
    private Bitmap imagen;
    private ProgressBar circulo;
    private Visor360 visor;
    public descargar360(String url,View view,Visor360 visor){
        try {
            direccion = new URL(url.toString());
            this.visor = visor;
            mView = view;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }
    protected void onPreExecute(){
        if(mView!=null){
            circulo=(ProgressBar)mView.findViewById(R.id.cargando360);
            circulo.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected Boolean doInBackground(String... params) {
        boolean vuelta = false;
        try {
            imagen  = BitmapFactory.decodeStream(direccion.openConnection().getInputStream());
            vuelta = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return vuelta;
    }
    protected void onPostExecute(final Boolean success){
        if(success){
            if(mView!=null){
                circulo.setVisibility(View.INVISIBLE);
                visor.ponerPanorama(imagen);
            }
        }else{
            if(mView!=null){
                //circulo.setVisibility(View.INVISIBLE);
            }
        }

    }
}
