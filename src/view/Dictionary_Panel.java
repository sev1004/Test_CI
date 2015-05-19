package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.SliderUI;

import controller.Main_Controller;

public class Dictionary_Panel{
	private JPanel dic_Panel;
	private JPanel animal_Panel;
	private JPanel plant_Panel;
	private JPanel tool_Panel;
	private JPanel nation_Panel;
	private JPanel word_Image_Panel;
	private JPanel text_Panel;
	private JPanel taeho_Panel;
	private JPanel dummy_Panel;
	private JLabel word_Image;
	private JLabel searchbtn_Label;
	private TextField search_TextField;
	private String search_String;
	private ImageIcon img;
	private boolean search_Flag;
	private SoundPlayer sp;
	private TitledBorder panel_Title1;
	private TitledBorder panel_Title2;
	private TitledBorder panel_Title3;
	private TitledBorder panel_Title4;
	private ImageIcon search_btn;
	
	Dimension d;
	double ratio;
	public Dictionary_Panel(){
		
	}
	
	public Dictionary_Panel(JFrame frame){
		System.out.println("Dictionary_Panel");
		dic_Panel = new JPanel();
		dic_Panel.setSize((int)(frame.getWidth()*0.99),(int)(frame.getHeight()*0.99));
		frame.setLayout(new BorderLayout());
		frame.add(dic_Panel);
		dic_Panel.setVisible(true);
		sp = new SoundPlayer();
	}
	
	public void kill_Panel(){
		dic_Panel.setVisible(false);
	}

	public String get_search_String(){
		try{
		Integer.parseInt(search_TextField.getText());
		}catch(Exception e){
			search_String = new String(search_TextField.getText());
			search_TextField.setText("");
			return this.search_String;
		}
		if(search_TextField.getText().equalsIgnoreCase("0"))
		{
			search_String = new String(search_TextField.getText());
		}else{
			search_String = "123";
		}
		search_TextField.setText("");
		return this.search_String;
	}
	
	public void view_Search_Word(Main_Controller main_C, JFrame frame){
		dic_Panel.setVisible(false);
		
		sp.play(main_C.getDictionaryController().getWord().getSoundURL());
		ImageIcon word_icon = new ImageIcon(main_C.getDictionaryController().getWord().getImageURL().replace(".gif", ".jpg"));
		System.out.println(main_C.getDictionaryController().getWord().getImageURL());
		word_icon = new ImageIcon(word_icon.getImage().getScaledInstance((int)(frame.getHeight()*0.9),(int)(frame.getHeight()*0.9),Image.SCALE_REPLICATE));
		word_Image = new JLabel(word_icon);
		
		word_Image_Panel = new JPanel(new BorderLayout());
		word_Image_Panel.add(word_Image);
		frame.remove(dic_Panel);
		word_Image_Panel.setVisible(true);
		frame.add(word_Image_Panel);
		
		
	}
	
	public void setPanelTrue(JFrame frame){
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.remove(word_Image_Panel);
				frame.add(dic_Panel);
				dic_Panel.setVisible(true);
				search_TextField.requestFocusInWindow();
			}
		});
		
	}
	
	
	public void viewDictionary(Main_Controller main_C, JPanel main, JFrame frame){
		d = Toolkit.getDefaultToolkit().getScreenSize();
		ratio=((double)(d.getWidth()+d.getHeight())*0.0005);

		int animal_Count=0;
		int plant_Count=0;
		int tool_Count=0;
		int nation_Count=0;
	 	int wordCount = main_C.getDictionaryController().getDictionary().getWordArr().size();
	 	
		text_Panel = new JPanel();
		taeho_Panel = new JPanel();
		animal_Panel = new JPanel();
		plant_Panel = new JPanel();
		tool_Panel = new JPanel();
		nation_Panel = new JPanel();
		dummy_Panel = new JPanel();
		
		search_TextField = new TextField(30);
		searchbtn_Label = new JLabel();
		search_btn = new ImageIcon("image/button/enter.jpg");
		search_btn = new ImageIcon(search_btn.getImage().getScaledInstance((int)(search_btn.getIconWidth()*ratio/1.5),(int)(search_btn.getIconHeight()*ratio/1.5), Image.SCALE_REPLICATE));
		panel_Title1 = new TitledBorder(new EtchedBorder());
		panel_Title2 = new TitledBorder(new EtchedBorder());
		panel_Title3 = new TitledBorder(new EtchedBorder());
		panel_Title4 = new TitledBorder(new EtchedBorder());
		
		/////setLayout//////
		dic_Panel.setLayout(new BorderLayout());
		
		text_Panel.setLayout(new FlowLayout());
		taeho_Panel.setLayout(new GridLayout(2,2));
	 	
	 	animal_Panel.setLayout(new GridLayout(3,10,5,5));
		plant_Panel.setLayout(new GridLayout(3,10,5,5));
		tool_Panel.setLayout(new GridLayout(3,10,5,5));
		nation_Panel.setLayout(new GridLayout(3,10,5,5));
		////////////////////////////////////////////////////////////////////////////////
		
		////set design////////////////
		dic_Panel.setSize(frame.getWidth(),frame.getHeight());
		dic_Panel.setBackground(Color.WHITE);
		text_Panel.setBounds(0,0, dic_Panel.getWidth(), (int)(dic_Panel.getHeight()*0.05));
		text_Panel.setBackground(Color.WHITE);
		taeho_Panel.setBounds(0,text_Panel.getHeight(), (int)(dic_Panel.getWidth()*0.99), (int)(dic_Panel.getHeight()*0.90));
		taeho_Panel.setBackground(Color.WHITE);
		search_TextField.setFont(new Font("∏º¿∫ ∞ÌµÒ",Font.BOLD,(int)(25*ratio/1.5)));
		searchbtn_Label.setIcon(search_btn);
		//Add///////////////////////////////////////////////////////////////////////////
		
		text_Panel.add(search_TextField);
		text_Panel.add(searchbtn_Label);
		taeho_Panel.add(animal_Panel);
		taeho_Panel.add(plant_Panel);
		taeho_Panel.add(tool_Panel);
		taeho_Panel.add(nation_Panel);

		dic_Panel.add(text_Panel);
		dic_Panel.add(taeho_Panel);
		dic_Panel.add(dummy_Panel);
		///////////////////////////////////////////////////////////////////////////////
		
		//search_text_Field
		search_TextField.requestFocusInWindow();
		search_TextField.addKeyListener(new KeyAdapter(){
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == KeyEvent.VK_ENTER){
					System.out.println("ø£≈Õ¿‘∑¬");
					String a = new String();
					a = get_search_String();
					if(a.equalsIgnoreCase("0")){
						frame.requestFocusInWindow();
						main_C.quit();
						kill_Panel();
						main.setVisible(true);
					}else {
						if(!(a.equalsIgnoreCase("123")))
						{
							search_Flag=true;
							main_C.getDictionaryController().search(a);
							view_Search_Word(main_C, frame);
							setPanelTrue(frame);
						}
					}
				}
			}
		});
		
		//for test//
		panel_Title1.setTitleFont(search_TextField.getFont());
		panel_Title2.setTitleFont(search_TextField.getFont());
		panel_Title3.setTitleFont(search_TextField.getFont());
		panel_Title4.setTitleFont(search_TextField.getFont());
		
		panel_Title1.setTitle("Animal");
		panel_Title2.setTitle("Plant");
		panel_Title3.setTitle("Tool");
		panel_Title4.setTitle("Nation");
		
		animal_Panel.setBorder(panel_Title1);
		plant_Panel.setBorder(panel_Title2);
		tool_Panel.setBorder(panel_Title3);
		nation_Panel.setBorder(panel_Title4);
		
		animal_Panel.setBackground(Color.WHITE);
		plant_Panel.setBackground(Color.WHITE);
		tool_Panel.setBackground(Color.WHITE);
		nation_Panel.setBackground(Color.WHITE);
		
		for (int i =0; i < wordCount ; i++){
			switch(main_C.getDictionaryController().getDictionary().getWordArr().elementAt(i).getType()){
			case 1:
				img = new ImageIcon(main_C.getDictionaryController().getDictionary().getWordArr().elementAt(i).getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
				word_Image = new JLabel(img);
				animal_Panel.add(word_Image);
				animal_Count++;
				break;
			case 2:
				img = new ImageIcon(main_C.getDictionaryController().getDictionary().getWordArr().elementAt(i).getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
				word_Image = new JLabel(img);
				plant_Panel.add(word_Image);
				plant_Count++;
				break;
			case 3:
				img = new ImageIcon(main_C.getDictionaryController().getDictionary().getWordArr().elementAt(i).getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
				word_Image = new JLabel(img);
				tool_Panel.add(word_Image);
				tool_Count++;
				break;
			case 4:
				img = new ImageIcon(main_C.getDictionaryController().getDictionary().getWordArr().elementAt(i).getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
				word_Image = new JLabel(img);
				nation_Panel.add(word_Image);
				nation_Count++;
				break;
			}
		}
		System.out.println(animal_Count);
		for(int i=0; i< 30-animal_Count; i++){
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
			word_Image = new JLabel(img);
			animal_Panel.add(word_Image);
		}
		for(int i=0; i< 30-plant_Count; i++){
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
			word_Image = new JLabel(img);
			plant_Panel.add(word_Image);
		}
		for(int i=0; i< 30-tool_Count; i++){
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
			word_Image = new JLabel(img);
			tool_Panel.add(word_Image);
		}
		for(int i=0; i< 30-nation_Count; i++){
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance((int)(100*ratio/2), (int)(100*ratio/2), Image.SCALE_REPLICATE));
			word_Image = new JLabel(img);
			nation_Panel.add(word_Image);
		}
		dic_Panel.setVisible(true);
	}
}