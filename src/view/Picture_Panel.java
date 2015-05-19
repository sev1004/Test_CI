package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;

public class Picture_Panel {
	private JPanel pic_Panel;
	private JPanel info_Panel;
	private JPanel reward_Image_Panel;
	private JPanel text_Panel;
	private JPanel button_Panel;
	
	private ImageIcon reward_Image;
	private ImageIcon upload_Image;
	private ImageIcon delete_Image;
	
	private JTextField url_TextField;
	
	private JLabel upload_Label;
	private JLabel delete_Label;
	private JLabel reward_Image_Label;
	
	Dimension d;
	double ratio;
	
	public Picture_Panel() {

	}

	public Picture_Panel(JFrame frame, String url) {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		ratio=((double)(d.getWidth()+d.getHeight())*0.0005);
		String temp = url;
		System.out.println(url);
		pic_Panel = new JPanel();
		info_Panel = new JPanel();
		reward_Image_Panel = new JPanel();
		text_Panel = new JPanel();
		button_Panel = new JPanel();
		
		reward_Image = new ImageIcon(url);
		upload_Image = new ImageIcon("image/button/upload_Picture.jpg");
		upload_Image = new ImageIcon(upload_Image.getImage().getScaledInstance((int)(upload_Image.getIconWidth()*ratio/2),(int)( upload_Image.getIconHeight()*ratio/2), Image.SCALE_REPLICATE));
		delete_Image = new ImageIcon("image/button/delete_Picture.jpg");
		delete_Image = new ImageIcon(delete_Image.getImage().getScaledInstance((int)(delete_Image.getIconWidth()*ratio/2),(int)( delete_Image.getIconHeight()*ratio/2), Image.SCALE_REPLICATE));
		
		url_TextField = new JTextField(30);
		
		upload_Label = new JLabel();
		delete_Label = new JLabel();
		reward_Image_Label = new JLabel();
		///Set Layout///////////////////////////////////
		frame.setLayout(new BorderLayout());
		
		pic_Panel.setLayout(null);
		reward_Image_Panel.setLayout(null);
		info_Panel.setLayout(null);
		button_Panel.setLayout(new GridLayout(1,2));
		////////////////////////////////////////////////
		
		///Set Bounds//////////////////////////////////////////////////////////////////////////////////////////////////////
		pic_Panel.setSize(frame.getWidth()-50,frame.getHeight()-50);
		
		info_Panel.setBounds(10,	10, 						pic_Panel.getWidth(), (int)(pic_Panel.getHeight()*0.8));
		button_Panel.setBounds(10,	info_Panel.getHeight()+10, 	pic_Panel.getWidth(), (int)(pic_Panel.getHeight()*0.2));
		
		reward_Image_Panel.setBounds(30, 10, (int)(info_Panel.getWidth()*0.97), (int)(info_Panel.getHeight()*0.9));
		reward_Image_Panel.setBorder(new EtchedBorder());
		text_Panel.setBounds(frame.getWidth()/5, reward_Image_Panel.getHeight()+10, (int)(info_Panel.getWidth()*0.6), (int)(info_Panel.getHeight()*0.1));
		if(reward_Image.getIconWidth()>reward_Image_Panel.getWidth() || reward_Image.getIconHeight()>reward_Image_Panel.getHeight()){
			reward_Image = new ImageIcon(reward_Image.getImage().getScaledInstance((int)(reward_Image_Panel.getWidth()*0.95),(int)(reward_Image_Panel.getHeight()*0.95), Image.SCALE_REPLICATE));
		}
		reward_Image_Label.setIcon(reward_Image);
		reward_Image_Label.setBounds((reward_Image_Panel.getWidth()-reward_Image.getIconWidth())/2, ((reward_Image_Panel.getHeight()-reward_Image.getIconHeight()))/2, reward_Image.getIconWidth(), reward_Image.getIconHeight());
		
		url_TextField.setFont(new Font("¸¼Àº °íµñ",Font.BOLD,25));
		url_TextField.setForeground(Color.BLACK);
		url_TextField.setBackground(Color.WHITE);
		url_TextField.setText(url);
		
		upload_Label.setSize(upload_Image.getIconWidth(), upload_Image.getIconWidth());
		upload_Label.setIcon(upload_Image);
		delete_Label.setIcon(delete_Image);
		upload_Label.setBackground(Color.BLACK);
		delete_Label.setBackground(Color.ORANGE);
		upload_Label.setHorizontalAlignment(0);
		delete_Label.setHorizontalAlignment(0);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		pic_Panel.setBackground(Color.WHITE);
		info_Panel.setBackground(Color.WHITE);
		reward_Image_Panel.setBackground(Color.WHITE);
		text_Panel.setBackground(Color.WHITE);
		button_Panel.setBackground(Color.WHITE);
		///Add///////////////////////////////////////////////////////////////////

		frame.add(pic_Panel);
		
		pic_Panel.add(info_Panel);
		pic_Panel.add(button_Panel);
		
		info_Panel.add(reward_Image_Panel);
		info_Panel.add(text_Panel);
		
		reward_Image_Panel.add(reward_Image_Label);
		text_Panel.add(url_TextField);
				
		button_Panel.add(upload_Label);
		button_Panel.add(delete_Label);
		/////////////////////////////////////////////////////////////////////////
		
		url_TextField.setEnabled(false);
		pic_Panel.setVisible(true);
	}

	public void kill_Panel() {
		pic_Panel.setVisible(false);
	}
	
	public void refresh_Information(String url){
		reward_Image = new ImageIcon(url);
		if(reward_Image.getIconWidth()>reward_Image_Panel.getWidth() || reward_Image.getIconHeight()>reward_Image_Panel.getHeight()){
			reward_Image = new ImageIcon(reward_Image.getImage().getScaledInstance((int)(reward_Image_Panel.getWidth()*0.95),(int)(reward_Image_Panel.getHeight()*0.95), Image.SCALE_REPLICATE));
		}
		reward_Image_Label.setBounds((reward_Image_Panel.getWidth()-reward_Image.getIconWidth())/2, ((reward_Image_Panel.getHeight()-reward_Image.getIconHeight()))/2, reward_Image.getIconWidth(), reward_Image.getIconHeight());
//		upload_Label.setSize(upload_Image.getIconWidth(), upload_Image.getIconWidth());
		url_TextField.setText(url);
		reward_Image_Label.setIcon(reward_Image);
	}
}
