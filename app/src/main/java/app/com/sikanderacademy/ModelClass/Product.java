package app.com.sikanderacademy.ModelClass;

/**
 * Created by KURPC on 24-01-2018.
 */

public class Product {
    private int id;
    private String title;
    private String shortdesc;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Product(int id, String title, String shortdesc, String name, String hour, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.name = name;
        this.hour = hour;
        this.image = image;
    }

    private String hour;
    private int image;

}
