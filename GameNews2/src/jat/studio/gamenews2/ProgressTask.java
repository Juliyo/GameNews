package jat.studio.gamenews2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

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
    protected void onPostExecute(final Boolean success){
        if(success){
            if(mView!=null){
                circulo.setVisibility(View.INVISIBLE);
                FragmentNoticias.mostrarLista(context);

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

            if(in!=null){
                XmlPullParserFactory parserCreator=null;
                parserCreator=XmlPullParserFactory.newInstance();
                XmlPullParser parser;
                parser=parserCreator.newPullParser();
                parser.setInput(in,null);
                int parserEvent=parser.getEventType();

                while(parserEvent != XmlPullParser.END_DOCUMENT) {
                    switch (parserEvent) {
                        case XmlPullParser.START_TAG:
                            String tag = parser.getName();
                            if (tag.equalsIgnoreCase("title")) {
                                String title = parser.nextText();
                                FragmentNoticias.list_titles.add(title);
                            }
                            if(tag.equalsIgnoreCase("description")){
                                String description;
                                description=parser.nextText();
                                Document doc= Jsoup.parse(description);
                                Elements metaElems=doc.select("img");
                                String images=metaElems.attr("src");
                                if(images.length()!=0){
                                    FragmentNoticias.list_description.add(images);
                                }

                            }
                            break;
                    }//fin del switch
                    parserEvent = parser.next();
                }//fin del while
                }else{
                return false;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return true;
    }
}
