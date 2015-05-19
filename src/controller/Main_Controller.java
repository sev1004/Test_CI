package controller;

import java.util.*;

import view.Main_Frame;
import view.SoundPlayer;
import model.Database;

public class Main_Controller {

	private Game_Controller gamecontrol;
	private Dictionary_Controller dictionarycontrol;
	private Picture_Controller picturecontrol;
	private int state=0;

	public Main_Controller(){
		gamecontrol = new Game_Controller();
		dictionarycontrol = new Dictionary_Controller();
		picturecontrol = new Picture_Controller();
	}
	
    public void quit() {
    	switch(state)
    	{
    	case 1:
    	{
    		gamecontrol.init();
    		state = 0;
    		break;
    	}
    	case 2:
    	{
    		dictionarycontrol.init();
    		state = 0;
    		break;
    	}
    	case 3:
    	{
    		picturecontrol.init();
    		state = 0;
    		break;
    	}
    	}
    }

    public void exit() {
    	System.exit(0);
    }
    public Game_Controller getGameController() {
    	state=1;
    	return gamecontrol;
    }	
    public Dictionary_Controller getDictionaryController() {
    	state=2;
    	return dictionarycontrol;
    }
    public Picture_Controller getPictureController() {
    	state=3;
    	return picturecontrol;
    }
    public int getState(){
    	return this.state;
    }

	public static void main(String args[])
	{
		Main_Frame mf = new Main_Frame();
	}

}