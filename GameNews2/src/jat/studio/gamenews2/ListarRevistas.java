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
 * Created by Toni on 24/11/2014.
 */
public class ListarRevistas extends AsyncTask<String,Void,List<String>> {

    private HttpClient cliente = null;
    private HttpPost post = null;
    private HttpResponse respuesta = null;
    private HttpEntity entidad = null;
    private JSONArray json;
    private View mView;
    private Activity activity;
    private FragmentRevista mFragment;
    private ProgressBar barra;
    public ListarRevistas(Activity context,View view, FragmentRevista fragment){
        activity = context;
        mView = view;
        mFragment = fragment;
        cliente = new DefaultHttpClient();
        barra = (ProgressBar) mView.findViewById(R.id.circuloRevista);
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        mFragment.inicializar();
        barra.setVisibility(View.INVISIBLE);
    }

    @Override
    protected List<String> doInBackground(String... params) {
        String resp="";
        String urlScript = "http://game-news.url.ph/revistas/listar.php";
        post = new HttpPost(urlScript);
        String estado = ".";
        try{
            List<NameValuePair> datos = new ArrayList<NameValuePair>(1);
            datos.add(new BasicNameValuePair("nombre", "null"));
            post.setEntity(new UrlEncodedFormEntity(datos));
            respuesta = cliente.execute(post);
            if(respuesta != null) {
                entidad = respuesta.getEntity();
                resp = EntityUtils.toString(entidad);
                json = new JSONArray(resp);
                for(int i=0;i<json.length();i++){
                    mFragment.introducirRevista("http://game-news.url.ph/revistas/portadas/" + json.getString(i),"http://game-news.url.ph/revistas/"+ json.getString(i));

                }
            }
        }catch(Exception e){
            e.printStackTrace();
            estado = "error de red";
        }
        return null;
    }
}
