package jat.studio.gamenews2;

import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toni on 12/11/2014.
 */
public class Listar extends AsyncTask<String,Void,List<String>> {

    private HttpClient cliente = null;
    private HttpPost post = null;
    private HttpResponse respuesta = null;
    private HttpEntity entidad = null;
    private JSONArray json;
    private View mView;
    private FragmentGaleria mFragment;
    private ProgressBar circulo;
    private Activity activity;
    private GridViewAdapter customGridAdapter;
    public Listar(Activity context,View view, FragmentGaleria fragment){
        activity = context;
        mView = view;
        mFragment = fragment;
        cliente = new DefaultHttpClient();
    }
    protected void onPreExecute(){
        if(mView!=null){
            circulo=(ProgressBar)mView.findViewById(R.id.cargandoGaleria);
            circulo.setVisibility(View.VISIBLE);
        }
    }

    protected void onPostExecute(List<String> imagenes){
        if(mView!=null) {
            circulo.setVisibility(View.INVISIBLE);
            mFragment.crearAdapter();
            mFragment.setAdapter();
        }
    }

    @Override
    protected List<String> doInBackground(String... params) {
        String resp="";
        String urlScript = "http://game-news.url.ph/imagenes/360/listar.php";
        post = new HttpPost(urlScript);
        String estado = ".";
        ArrayList<String> imagenes = new ArrayList<String>();
        try{
            List<NameValuePair> datos = new ArrayList<NameValuePair>(1);
            datos.add(new BasicNameValuePair("nombre", "null"));
            post.setEntity(new UrlEncodedFormEntity(datos));
            respuesta = cliente.execute(post);
            if(respuesta != null) {
                entidad = respuesta.getEntity();
                resp = EntityUtils.toString(entidad);
                json = new JSONArray(resp);
                imagenes = new ArrayList<String>();

                for(int i=0;i<json.length();i++){
                    mFragment.addImagen("http://game-news.url.ph/imagenes/360/" + json.getString(i));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            estado = "error de red";
        }
        return imagenes;
    }
}
