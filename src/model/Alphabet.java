package model;

public class Alphabet extends Contents {

    private String alphabet;
    private Boolean correct;

    public Alphabet() {
    }

    public String get_Alphabet() {
        return alphabet;
    }
    public void set_Alphabet(String alphabet) {
        this.alphabet = alphabet;
    }
    
    public void movePos() {
        // TODO implement here
    }
    
    public void setPos(int x, int y) {
        this.setXPos(x);
        this.setYPos(y);
    }
    
    public Boolean getCorrect(){
    	return this.correct;
    }
    
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}