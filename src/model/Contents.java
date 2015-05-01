package model;

public class Contents {

    private String sound_URL;
    private String image_URL;
    private int x_Pos;
    private int y_Pos;

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

    public int getXPos() {
    	return this.x_Pos;
    }

    public void setXPos(int x) {
    	this.x_Pos = x;
        // TODO implement here
    }

    public int getYPos() {
        return this.y_Pos;
    }

    public void setYPos(int y) {
        this.y_Pos = y;
    }

}