package jat.studio.gamenews2;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Toni on 25/11/2014.
 */
public class NoticiaActivity extends ActionBarActivity {

    private TextView tituloNoticia;
    private TextView contenidoNoticia;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticia);
        tituloNoticia = (TextView) findViewById(R.id.textTitulo);
        contenidoNoticia = (TextView) findViewById(R.id.textNoticia);
        imagen = (ImageView)findViewById(R.id.imagenPreviewNoticia);
        Bundle extras = getIntent().getExtras();
        String titulo = null;
        String contenido = null;
        String urlImagen = null;
        if (extras != null) {
            titulo = extras.getString("titulo");
            contenido = extras.getString("contenido");
            tituloNoticia.setText(titulo);
            contenidoNoticia.setText(unescape(contenido));
            urlImagen = extras.getString("urlImagen");
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitleTextColor(getResources().getColor(R.color.White));
            setSupportActionBar(toolbar);

            toolbar.setBackgroundColor(getResources().getColor(R.color.RedShop));
            final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
            upArrow.setColorFilter(getResources().getColor(R.color.White), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            getSupportActionBar().setTitle(titulo);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        Picasso.with(NoticiaActivity.this).load(urlImagen).resize(275,200).into(imagen);
    }
    private String unescape(String description) {
        return description.replaceAll("\\\\n", "\\\n");
    }

}
