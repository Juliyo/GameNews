package jat.studio.gamenews2;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

/**
 * Created by Toni on 25/11/2014.
 */
public class NoticiaActivity extends ActionBarActivity {

    private TextView tituloNoticia;
    private TextView contenidoNoticia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticia);
        tituloNoticia = (TextView) findViewById(R.id.textTitulo);
        contenidoNoticia = (TextView) findViewById(R.id.textNoticia);
        Bundle extras = getIntent().getExtras();
        String titulo = null;
        String contenido = null;
        if (extras != null) {
            titulo = extras.getString("titulo");
            contenido = extras.getString("contenido");
            tituloNoticia.setText(titulo);
            contenidoNoticia.setText(unescape(contenido));
        }else{

        }
    }
    private String unescape(String description) {
        return description.replaceAll("\\\\n", "\\\n");
    }

}
