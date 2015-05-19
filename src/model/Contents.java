package model;

public class Contents {

    private String sound_URL;
    private String image_URL;

    public Contents() {
    }

    public String getSoundURL() {
        return this.sound_URL;
    }

    public void setSoundURL(String url) {
        this.sound_URL = url;
    }

    public String getImageURL() {
        return this.image_URL;
    }

    public void setImageURL(String url) {
        this.image_URL = url;
    }
}