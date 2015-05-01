package controller;

import java.util.*;

import model.Alphabet;
import model.Database;
import model.Picture;
import model.Word;


public class Game_Controller {

    private Word word;
    private Alphabet alphabet;
    private String wordBuffer;
    private Picture vKeyboard;
    private Database db;
    private int wordIndex;//*//


    public Game_Controller() {
    	wordIndex=0;
    }
    public void selectWord() {
    	int randNum = (int)Math.random()%120+1;
        word = db.selectGameWord(randNum);
        
        word.setXPos(120);
        word.setYPos(130);
    }
    public void checkAlphabet(char c) {
        if(word.getName().charAt(wordIndex)==c)
        {
        	word.getAlphabetArr().elementAt(wordIndex).setCorrect(true);
        	checkWord();	
        }
    }
    public void updateDictionary() {
        // TODO implement here
    }
    public void gameStart() {
        selectWord();
        word.setAlphabetArr();
        checkKeyboardImage();
    }
    public void checkWord() {
        if(wordIndex!=word.getLength())
        {
        	wordIndex++;
        }
        else
        {
        	db.updateWord(word);        	
        }
    }
    public void pressAlphabet(char c) {
        checkAlphabet(c);
    }
    public void checkKeyboardImage() {
    	if(vKeyboard.getImageURL()=="")
    		db.selectKeyboardImage(vKeyboard);
    	
    	vKeyboard.setXPos(100);
    	vKeyboard.setYPos(200);
    }
    public void init() {
        // TODO implement here
    }

}