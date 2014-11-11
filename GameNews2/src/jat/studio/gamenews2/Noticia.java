package jat.studio.gamenews2;

/**
 * Created by Julio on 11/11/2014.
 */
public class Noticia {
    private String title;
    private String image;
    private String description;

    public Noticia(String title, String image, String description){
        this.title=title;
        this.image=image;
        this.description=description;

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
}
