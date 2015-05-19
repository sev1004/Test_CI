package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;

import controller.Main_Controller;

public class Main_Frame extends JFrame implements KeyListener{
	private static final GraphicsDevice gd =  (GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices())[0];
	private Main_Controller main_C;
	private SoundPlayer sp;
	private JPanel main_Panel;
	private Dictionary_Panel d_Panel;
	private Game_Panel g_Panel;
	private Picture_Panel p_Panel;
	private FileDialog fd;
	
	private JLabel gameStartLabel;
	private JLabel dictionaryLabel;
	private JLabel pictureLabel;
	private JLabel exitLabel;
	private ImageIcon gameStart_Image;
	private ImageIcon dictionary_Image;
	private ImageIcon picture_Image;
	private ImageIcon exit_Image;
	
	private Dimension d;
	
	double ratio;
	
	public Main_Frame(){
		main_C = new Main_Controller();
		init();
	}
	
	public void init(){
		d = Toolkit.getDefaultToolkit().getScreenSize();
		ratio=((double)(d.getWidth()+d.getHeight())*0.0005);
		sp = new SoundPlayer();	
		this.setExtendedState(Frame.MAXIMIZED_BOTH);   // 풀 스크린 출력
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 닫힘 버튼 설정
		//gd.setFullScreenWindow(this);
        //validate();
		this.setSize(d);
		this.setResizable(false);
		this.setTitle("유아용 수집왕!!");
		this.setBackground(Color.WHITE);
		System.out.println(d.getHeight());
		setting_1366_768_Frame(this);
		this.addKeyListener(this);
		this.setVisible(true);// 프레임 가시성 활성화
		
	}
	private void setting_1366_768_Frame(JFrame frame){
		gameStart_Image = new ImageIcon("image/button/1.jpg");
		gameStart_Image = new ImageIcon(gameStart_Image.getImage().getScaledInstance((int)((double)(gameStart_Image.getIconWidth())*ratio/2),(int)((double)(gameStart_Image.getIconHeight())*ratio/2), Image.SCALE_REPLICATE));
		dictionary_Image = new ImageIcon("image/button/2.jpg");
		dictionary_Image = new ImageIcon(dictionary_Image.getImage().getScaledInstance((int)((double)dictionary_Image.getIconWidth()*ratio/2),(int)((double)dictionary_Image.getIconHeight()*ratio/2), Image.SCALE_REPLICATE));
		picture_Image = new ImageIcon("image/button/3.jpg");
		picture_Image = new ImageIcon(picture_Image.getImage().getScaledInstance((int)((double)picture_Image.getIconWidth()*ratio/2),(int)((double)picture_Image.getIconHeight()*ratio/2), Image.SCALE_REPLICATE));
		exit_Image = new ImageIcon("image/button/4.jpg");
		exit_Image = new ImageIcon(exit_Image.getImage().getScaledInstance((int)((double)exit_Image.getIconWidth()*ratio/2),(int)((double)exit_Image.getIconHeight()*ratio/2), Image.SCALE_REPLICATE));

		JLabel gameStartLabel = new JLabel(gameStart_Image);
		JLabel dictionaryLabel = new JLabel(dictionary_Image);
		JLabel pictureLabel = new JLabel(picture_Image);
		JLabel exitLabel = new JLabel(exit_Image);
		main_Panel = new JPanel();
		frame.add(main_Panel);
		main_Panel.setLayout(new GridLayout(2,2));
		main_Panel.setBackground(Color.WHITE);
		main_Panel.add(gameStartLabel);
		main_Panel.add(dictionaryLabel);
		main_Panel.add(pictureLabel);
		main_Panel.add(exitLabel);
		main_Panel.setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(main_C.getState() == 0 ){
			main_Frame(e);
		} else if(main_C.getState() == 1){
			game_Frame(e);
		} else if(main_C.getState() == 2){
			dic_Frame(e);
		} else if(main_C.getState() == 3){
			picture_Frame(e);
		}
	}
	
	
	private void picture_Frame(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyChar() == '0'){
			p_Panel.kill_Panel();
			main_C.quit();
			main_Panel.setVisible(true);
		} else if(e.getKeyChar() == 'u'){
			main_C.getPictureController().uploadPicture();
			fd = new FileDialog(this, "파일열기", 0);
			fd.setDirectory("C:");
			fd.setVisible(true);
			String fname = fd.getFile();
			String fdirectory=fd.getDirectory();
			if(fdirectory!=null)
			{
				fdirectory = fdirectory + fname;
				System.out.println(fdirectory);
				main_C.getPictureController().selectFileDirectory(fdirectory);
				p_Panel.refresh_Information(fdirectory);
			}
		}else if (e.getKeyChar() == 'd'){
			main_C.getPictureController().deletePicture();
			p_Panel.refresh_Information("image/reward/default.jpg");
		}
	}

	private void dic_Frame(KeyEvent e) {
		if(e.getKeyChar() == '0'){
			d_Panel.kill_Panel();
			main_C.quit();
			main_Panel.setVisible(true);
		} 
	}

	private void game_Frame(KeyEvent e){
		if(e.getKeyChar() == '0'){
			g_Panel.kill_Panel();
			main_C.quit();
			main_Panel.setVisible(true);
		}else {
			char c = e.getKeyChar();
			main_C.getGameController().pressAlphabet(c);
			
			if(main_C.getGameController().getWord().getAlphabetArr().elementAt(main_C.getGameController().getWordIndex()-1).getCorrect()){
//				g_Panel.알파벳맞춘함수
				g_Panel.kill_Panel();
				g_Panel.refresh();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sp.play(main_C.getGameController().getWord().getAlphabetArr().elementAt(main_C.getGameController().getWordIndex()-1).getSoundURL());
				main_C.getGameController().getWord().getAlphabetArr().elementAt(main_C.getGameController().getWordIndex()-1).setCorrect(false);
			}
			if(main_C.getGameController().getWord().getCorrect()){
				g_Panel.display_Reward_Image(this);
//				g_Panel.재시작함수
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				sp.play(main_C.getGameController().getWord().getSoundURL());
			}
		}
	}
	
	private void main_Frame(KeyEvent e){
		if(e.getKeyChar()=='1'){
			main_C.getGameController().gameStart();
			main_Panel.setVisible(false);
			g_Panel = new Game_Panel(this,main_C);
		} else if(e.getKeyChar() == '2'){
			main_C.getDictionaryController().dictionaryOpen();
			main_Panel.setVisible(false);
			d_Panel = new Dictionary_Panel(this);
			d_Panel.viewDictionary(main_C,main_Panel,this);
		} else if(e.getKeyChar() == '3'){
			main_C.getPictureController().managePicture();
			main_Panel.setVisible(false);
			p_Panel = new Picture_Panel(this,main_C.getPictureController().getRewardPicture().getImageURL());
		} else if(e.getKeyChar() == '4'){
			main_C.exit();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
