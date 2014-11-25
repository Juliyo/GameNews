package jat.studio.gamenews2;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Toni on 24/11/2014.
 */
public class descargarRevista extends AsyncTask<String,Integer,Boolean> {
    private URL direccion;
    private View mView;
    private Bitmap imagen;
    private RevistaActivity revista;
    private HttpClient cliente = null;
    private HttpPost post = null;
    private HttpResponse respuesta = null;
    private HttpEntity entidad = null;
    private JSONArray json;
    private String url;
    private ProgressDialog dialog;
    public descargarRevista(String urlRevista,RevistaActivity activity){
        revista = activity;
        url = urlRevista;
        cliente = new DefaultHttpClient();
    }
    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        dialog.dismiss();
        revista.iniciar();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(revista);
        dialog.setCancelable(true);
        dialog.setMessage("Descargando revista");
        dialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setMessage("Descargando páginas: "+values[0] + "/" + values[1]);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String resp="";
        String urlScript = url + "/listar.php";
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
                    publishProgress(i,json.length());
                    direccion = new URL(url.toString()+"/"+json.getString(i));
                    imagen  = BitmapFactory.decodeStream(direccion.openConnection().getInputStream());
                    if(imagen != null){
                        revista.addImagen(imagen);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            estado = "error de red";
        }
        return null;
    }
}
