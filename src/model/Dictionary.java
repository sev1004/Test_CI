package model;

import java.util.*;

public class Dictionary {

    private Vector<Word> wordArr;
    private Word word;
	
    public Dictionary() {
    	
    }

    public void addWordArr(Word w) {
        wordArr.add(w);
    }
    public Vector<Word> getWordArr() {
        return this.wordArr;
    }

}