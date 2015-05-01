package model;

import java.util.*;


public class Word extends Contents{

    public String name;
    public Vector<Alphabet> arr;
    public int length;
    public int type;
    public Boolean correct;
    public String url2;
    public Database db;
    
    public Word() {
    	
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
    	this.name=name;
    }

    public Alphabet getAlphabetIndex(int index) {
    	return this.arr.elementAt(index);
    }

    public int getLength() {
    	return this.length;
    }

    public void setLength(int length) {
    	this.length=length;
    }

    public void setCorrect(Boolean correct) {
    	this.correct=correct;
    }

    public int getType() {
    	return this.type;
    }

    public void setType(int type) {
    	this.type=type;
    }

    public Vector<Alphabet> getAlphabetArr() {
    	return null;
    }

    public void playSound() {
    }

    public void setImageURL2(String url2) {
    	this.url2=url2;
    }

    public void setAlphabetArr() {
    	this.arr=arr;
    }

    public void appendAlphabet(Alphabet alphabet) {
    	arr.addElement(alphabet);
    }

    public Boolean getCorrect() {
    	return this.correct;
    }
    
    public void setCategorizedWordPos() {
    	
    }

}