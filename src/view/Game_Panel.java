package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

import controller.Main_Controller;
import model.Alphabet;
import model.Database;
import model.Word;

import java.awt.*;

public class Game_Panel extends JPanel {
	private Word word;
	private Database db;
	
	double ratio;
	int count;
	int word_Length;
	int alphabet_PosX[];
	int alphabet_PosY[];
	
	private Main_Controller main_C;
	
	private Alphabet alphabet;
	private JPanel top_Panel;
	private JPanel bot_Panel;
	private JPanel reward_Panel;
	private Board board;

	private ImageIcon word_Image;
	private ImageIcon []alphabet_Image;
	private ImageIcon virtual_Keyboard_Image;
	
	private JLabel word_Label;
	private JLabel alphabet_Label[];
	private JLabel keyboard_Label;
	
	
	public Game_Panel() {
		
	}

	public Game_Panel(JFrame frame, Main_Controller main_C) {
		ratio=((double)(frame.getWidth()+frame.getHeight())*0.0005);
		this.main_C = main_C;
		init(frame);
	}

	public void kill_Panel() {
		this.setVisible(false);
    	this.getGraphics().clearRect(0, 0, this.getWidth(), this.getHeight());
	}

	public void display_Reward_Image(JFrame frame) {
		this.kill_Panel();
		for(int i=0; i<word_Length; i++){
			alphabet_Image[i]=null;
			alphabet_Label[i]=null;
		}
		Board board = new Board(frame,main_C,this);
		frame.setLayout(new BorderLayout());
		main_C.getPictureController().managePicture();
		ImageIcon reward = new ImageIcon(main_C.getPictureController().getRewardPicture().getImageURL());
		JLabel rewardimage = new JLabel();
		
		reward_Panel = new JPanel();
		reward_Panel.setLayout(null);
	    reward_Panel.setBackground(Color.WHITE);
	    if(reward.getIconWidth()>frame.getWidth() || reward.getIconHeight()>frame.getHeight()){
	    	reward= new ImageIcon(reward.getImage().getScaledInstance(frame.getWidth(),frame.getHeight(), Image.SCALE_REPLICATE));
	    }
	    rewardimage.setBounds((frame.getWidth()-reward.getIconWidth())/2, ((frame.getHeight()-reward.getIconHeight()))/2, reward.getIconWidth(), reward.getIconHeight());
	    rewardimage.setIcon(reward);

	    reward_Panel.add(rewardimage);
		frame.add(reward_Panel);
		
		// 출력 다 끝난후, 종료
			SwingUtilities.invokeLater(new Runnable(){
			@Override
				public void run() {
				try {
					reward_Panel.setVisible(true);
					Thread.sleep(3000);
					reward_Panel.setVisible(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.println("1번 Run");
				frame.add(board);
				board.getThread().start();
			}
		});
	}

	public void init(JFrame frame) {
		word_Length = main_C.getGameController().getWord().getLength();
		
		top_Panel = new JPanel();
		bot_Panel = new JPanel();
		
		this.setBackground(Color.WHITE);
		top_Panel.setBackground(Color.white);
		bot_Panel.setBackground(Color.WHITE);

		frame.setLayout(null);
		this.setLayout(null);
		top_Panel.setLayout(null);
		bot_Panel.setLayout(null);
		this.setBounds(0,0,(int)(frame.getWidth()*0.99), (int)(frame.getHeight()*0.99));
		top_Panel.setBounds((int)(frame.getWidth()*0.01), (int)(frame.getWidth()*0.01), (int)(frame.getWidth()*0.97), (int) (this.getHeight() * 0.56));
		bot_Panel.setBounds((int)(frame.getWidth()*0.01), top_Panel.getHeight() + (int)(frame.getWidth()*0.01), (int)(frame.getWidth()*0.97), (int) (this.getHeight() * 0.36));

	    refresh();

	    frame.add(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void refresh(){
		this.top_Panel.removeAll();
		this.bot_Panel.removeAll();

		word_Length = main_C.getGameController().getWord().getLength();
		
		alphabet_Image = new ImageIcon[10];
		alphabet_Label = new JLabel[10];
		for(int i=0; i<10; i++){
			alphabet_Image[i]= new ImageIcon();
			alphabet_Label[i]= new JLabel();
		}
		
		word_Image = new ImageIcon();
		word_Label = new JLabel();
		word_Image = new ImageIcon(main_C.getGameController().getWord().getImageURL());
		word_Image = new ImageIcon(word_Image.getImage().getScaledInstance((int)(280*ratio),(int)(280*ratio), Image.SCALE_REPLICATE));
		word_Label.setIcon(word_Image);
		word_Label.setBounds((top_Panel.getWidth()-word_Image.getIconWidth())/2,(top_Panel.getHeight()-word_Image.getIconHeight())/6,word_Image.getIconWidth(),word_Image.getIconHeight());
		
		for (int i = 0; i < word_Length; i++) {
			alphabet_Image[i] = new ImageIcon(main_C.getGameController().getWord().getAlphabetArr().elementAt(i).getImageURL());
			alphabet_Image[i] = new ImageIcon(alphabet_Image[i].getImage().getScaledInstance((int)(70*ratio),(int)(70*ratio), Image.SCALE_REPLICATE));
			alphabet_Label[i].setIcon(alphabet_Image[i]);
			alphabet_Label[i].setBounds((top_Panel.getWidth()-(top_Panel.getWidth()/19)*word_Length)/2+alphabet_Image[i].getIconWidth()*i-10,(int)(top_Panel.getHeight()*0.8),alphabet_Image[i].getIconWidth(),alphabet_Image[i].getIconHeight());
			top_Panel.add(alphabet_Label[i]);
		}
		
		keyboard_Label = new JLabel();
		virtual_Keyboard_Image = new ImageIcon(main_C.getGameController().getvKeyboard().getImageURL());
		double ratio2 = ((double)bot_Panel.getHeight()/(double)virtual_Keyboard_Image.getIconHeight());
		int keyboard_Width=((int)((double)virtual_Keyboard_Image.getIconWidth()*ratio2));
	    int keyboard_Height=((int)((double)virtual_Keyboard_Image.getIconHeight()*ratio2));
	    virtual_Keyboard_Image = new ImageIcon(virtual_Keyboard_Image.getImage().getScaledInstance(keyboard_Width, keyboard_Height, Image.SCALE_REPLICATE));
	    keyboard_Label.setBounds((bot_Panel.getWidth()-virtual_Keyboard_Image.getIconWidth())/2,0,virtual_Keyboard_Image.getIconWidth(),virtual_Keyboard_Image.getIconHeight());
		keyboard_Label.setIcon(virtual_Keyboard_Image);
		bot_Panel.add(keyboard_Label);
		
		top_Panel.add(word_Label);
		
		this.add(top_Panel);
		this.add(bot_Panel);
		
		this.setVisible(true);		
	}

	public void refresh_Keyboard(){
		this.bot_Panel.removeAll();
		
		char c=main_C.getGameController().getWord().getAlphabetArr().elementAt(main_C.getGameController().getWordIndex()-1).get_Alphabet();
		String keyboard_Name="image/keyboard/";
		keyboard_Name=keyboard_Name+c+".jpg";
		virtual_Keyboard_Image = new ImageIcon(keyboard_Name);
		keyboard_Label = new JLabel();
		double ratio2 = ((double)bot_Panel.getHeight()/(double)virtual_Keyboard_Image.getIconHeight());
		int keyboard_Width=((int)((double)virtual_Keyboard_Image.getIconWidth()*ratio2));
	    int keyboard_Height=((int)((double)virtual_Keyboard_Image.getIconHeight()*ratio2));
		keyboard_Label.setIcon(virtual_Keyboard_Image);
	    keyboard_Label.setBounds((bot_Panel.getWidth()-virtual_Keyboard_Image.getIconWidth())/2,0,virtual_Keyboard_Image.getIconWidth(),virtual_Keyboard_Image.getIconHeight());
	    
	   
	    bot_Panel.add(keyboard_Label);
		
		this.add(bot_Panel);
		
		this.setVisible(true);
	}
	
}