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
    private int wordIndex;
    
	public Game_Controller() {
		db = new Database();
    	word = new Word();
    	alphabet = new Alphabet();
    	wordBuffer ="";
    	vKeyboard = new Picture();
    	wordIndex=0;
    }

    public void gameStart() {
        selectWord();
        word.setAlphabetArr();
        checkKeyboardImage(word.getAlphabetArr().elementAt(0).get_Alphabet());
    }
    public void selectWord() {
    	double randomvalue = Math.random();
    	int randNum = (int)(randomvalue*26)+1;
    	System.out.println(randNum);
        word = db.selectGameWord(randNum);
        wordIndex = 0;
    }
    public void pressAlphabet(char c) {
        checkAlphabet(c);
    }
    public void checkAlphabet(char c) {
        if(word.getName().charAt(wordIndex)==c)
        {
        	System.out.println(word.getName().charAt(wordIndex));
        	word.getAlphabetArr().elementAt(wordIndex).setCorrect(true);
        	if(wordIndex<word.getLength()-1)
        		db.selectKeyboardImage(vKeyboard, word.getName().charAt(wordIndex+1));
        	checkWord();
        }
    }
    public void checkWord() {
        if(wordIndex != (word.getLength()-1))
        {
        	wordIndex++;
        }
        else
        {
        	wordIndex++;
        	word.setCorrect(true);
        	db.updateWord(word);
        }
    }
    public void checkKeyboardImage(char c) {
    	db.selectKeyboardImage(vKeyboard,c);
    }
    public void init() {
        
    }
    public Word getWord() {
		return word;
	}
	public void setWord(Word word) {
		this.word = word;
	}
	public Alphabet getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(Alphabet alphabet) {
		this.alphabet = alphabet;
	}
	public String getWordBuffer() {
		return wordBuffer;
	}
	public void setWordBuffer(String wordBuffer) {
		this.wordBuffer = wordBuffer;
	}
	public Picture getvKeyboard() {
		return vKeyboard;
	}
	public void setvKeyboard(Picture vKeyboard) {
		this.vKeyboard = vKeyboard;
	}
	public int getWordIndex() {
		return wordIndex;
	}
	public void setWordIndex(int wordIndex) {
		this.wordIndex = wordIndex;
	}

}