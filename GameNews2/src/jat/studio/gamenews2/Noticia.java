package jat.studio.gamenews2;

/**
 * Created by Julio on 11/11/2014.
 */
public class Noticia {
    private String title;
    private String image;
    private String description;
    private String contenido;

    public Noticia(){


    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setContenido(String contenido){
        this.contenido = contenido;
    }
    public String getContenido() {
        return contenido;
    }
}
