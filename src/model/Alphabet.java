package model;

public class Alphabet extends Contents {

    private char alphabet;
    private Boolean correct;

    public Alphabet() {
    	alphabet =0;
    	correct = false;
    }

    public char get_Alphabet() {
        return alphabet;
    }
    public void set_Alphabet(char alphabet) {
        this.alphabet = alphabet;
    }
    
    public Boolean getCorrect(){
    	return this.correct;
    }
    
    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

}