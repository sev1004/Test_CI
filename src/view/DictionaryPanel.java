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

import controller.MainController;

public class DictionaryPanel {
	private JPanel dicPanel;
	private JPanel animalPanel;
	private JPanel plantPanel;
	private JPanel toolPanel;
	private JPanel nationPanel;
	private JPanel wordImagePanel;
	private JPanel textPanel;
	private JPanel categoryPanel;
	private JPanel dummyPanel;
	private JLabel wordImage;
	private JLabel searchBtnLabel;
	private TextField search_TextField;
	private String searchString;
	private ImageIcon img;
	private boolean searchFlag;
	private SoundPlayer sp;
	private TitledBorder panelTitle1;
	private TitledBorder panelTitle2;
	private TitledBorder panelTitle3;
	private TitledBorder panelTitle4;
	private ImageIcon searchBtn;

	Dimension d;
	double ratio;

	public DictionaryPanel() {

	}

	public DictionaryPanel(JFrame frame) {
		dicPanel = new JPanel();
		dicPanel.setSize((int) (frame.getWidth() * 0.99),
				(int) (frame.getHeight() * 0.99));
		frame.setLayout(new BorderLayout());
		frame.add(dicPanel);
		dicPanel.setVisible(true);
		sp = new SoundPlayer();
	}

	public void killPanel() {
		dicPanel.setVisible(false);
	}

	public String getSearchString() {
		try {
			Integer.parseInt(search_TextField.getText());
		} catch (Exception e) {
			searchString = new String(search_TextField.getText().toLowerCase());
			search_TextField.setText("");
			return this.searchString;
		}
		searchString = "123";

		search_TextField.setText("");
		return this.searchString;
	}

	public void viewSearchWord(MainController mainControl, JFrame frame) {
		dicPanel.setVisible(false);

		sp.play(mainControl.getDictionaryController().getWord().getSoundURL());
		ImageIcon word_icon = new ImageIcon(mainControl.getDictionaryController()
				.getWord().getImageURL().replace(".gif", ".jpg"));

		word_icon = new ImageIcon(word_icon.getImage().getScaledInstance(
				(int) (frame.getHeight() * 0.9),
				(int) (frame.getHeight() * 0.9), Image.SCALE_REPLICATE));
		wordImage = new JLabel(word_icon);

		wordImagePanel = new JPanel(new BorderLayout());
		wordImagePanel.add(wordImage);
		frame.remove(dicPanel);
		wordImagePanel.setVisible(true);
		frame.add(wordImagePanel);
	}

	public void setPanelTrue(MainFrame frame) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				frame.remove(wordImagePanel);
				frame.add(dicPanel);
				dicPanel.setVisible(true);
				search_TextField.setText("");
				search_TextField.requestFocusInWindow();
			}
		});

	}

	public void viewDictionary(MainController mainControl, JPanel main, MainFrame frame) {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		ratio = ((double) (d.getWidth() + d.getHeight()) * 0.0005);

		int animal_Count = 0;
		int plant_Count = 0;
		int tool_Count = 0;
		int nation_Count = 0;
		int wordCount = mainControl.getDictionaryController().getDictionary()
				.getWordArr().size();

		textPanel = new JPanel();
		categoryPanel = new JPanel();
		animalPanel = new JPanel();
		plantPanel = new JPanel();
		toolPanel = new JPanel();
		nationPanel = new JPanel();
		dummyPanel = new JPanel();

		search_TextField = new TextField(30);
		searchBtnLabel = new JLabel();
		searchBtn = new ImageIcon("image/button/enter.jpg");
		searchBtn = new ImageIcon(searchBtn.getImage().getScaledInstance(
				(int) (searchBtn.getIconWidth() * ratio / 1.5),
				(int) (searchBtn.getIconHeight() * ratio / 1.5),
				Image.SCALE_REPLICATE));
		panelTitle1 = new TitledBorder(new EtchedBorder());
		panelTitle2 = new TitledBorder(new EtchedBorder());
		panelTitle3 = new TitledBorder(new EtchedBorder());
		panelTitle4 = new TitledBorder(new EtchedBorder());

		// ///setLayout//////
		dicPanel.setLayout(new BorderLayout());

		textPanel.setLayout(new FlowLayout());
		categoryPanel.setLayout(new GridLayout(2, 2));

		animalPanel.setLayout(new GridLayout(3, 10, 5, 5));
		plantPanel.setLayout(new GridLayout(3, 10, 5, 5));
		toolPanel.setLayout(new GridLayout(3, 10, 5, 5));
		nationPanel.setLayout(new GridLayout(3, 10, 5, 5));
		// //////////////////////////////////////////////////////////////////////////////

		// //set design////////////////
		dicPanel.setSize(frame.getWidth(), frame.getHeight());
		dicPanel.setBackground(Color.WHITE);
		textPanel.setBounds(0, 0, dicPanel.getWidth(),
				(int) (dicPanel.getHeight() * 0.05));
		textPanel.setBackground(Color.WHITE);
		categoryPanel.setBounds(0, textPanel.getHeight(),
				(int) (dicPanel.getWidth() * 0.99),
				(int) (dicPanel.getHeight() * 0.90));
		categoryPanel.setBackground(Color.WHITE);
		search_TextField.setFont(new Font("¸¼Àº °íµñ", Font.BOLD,
				(int) (25 * ratio / 1.5)));
		searchBtnLabel.setIcon(searchBtn);
		// Add///////////////////////////////////////////////////////////////////////////

		textPanel.add(search_TextField);
		textPanel.add(searchBtnLabel);
		categoryPanel.add(animalPanel);
		categoryPanel.add(plantPanel);
		categoryPanel.add(toolPanel);
		categoryPanel.add(nationPanel);

		dicPanel.add(textPanel);
		dicPanel.add(categoryPanel);
		dicPanel.add(dummyPanel);
		// /////////////////////////////////////////////////////////////////////////////

		// search_text_Field
		search_TextField.requestFocusInWindow();
		search_TextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					String a = new String();
					a = getSearchString();
					if (a.equalsIgnoreCase("")) {

					} else {
						if (!(a.equalsIgnoreCase("123"))) {
							searchFlag = true;
							mainControl.getDictionaryController().search(a);
							viewSearchWord(mainControl, frame);
							setPanelTrue(frame);
						}
					}
					
				} else if (e.getKeyChar() == 27) {
					frame.requestFocusInWindow();
					mainControl.quit();
					killPanel();
					main.setVisible(true);
				}
			}
		});

		// for test//
		panelTitle1.setTitleFont(search_TextField.getFont());
		panelTitle2.setTitleFont(search_TextField.getFont());
		panelTitle3.setTitleFont(search_TextField.getFont());
		panelTitle4.setTitleFont(search_TextField.getFont());

		panelTitle1.setTitle("Animal");
		panelTitle2.setTitle("Plant");
		panelTitle3.setTitle("Tool");
		panelTitle4.setTitle("Nation");

		animalPanel.setBorder(panelTitle1);
		plantPanel.setBorder(panelTitle2);
		toolPanel.setBorder(panelTitle3);
		nationPanel.setBorder(panelTitle4);

		animalPanel.setBackground(Color.WHITE);
		plantPanel.setBackground(Color.WHITE);
		toolPanel.setBackground(Color.WHITE);
		nationPanel.setBackground(Color.WHITE);

		for (int i = 0; i < wordCount; i++) {
			switch (mainControl.getDictionaryController().getDictionary()
					.getWordArr().elementAt(i).getType()) {
			case 1:
				img = new ImageIcon(mainControl.getDictionaryController()
						.getDictionary().getWordArr().elementAt(i)
						.getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance(
						(int) (100 * ratio / 2), (int) (100 * ratio / 2),
						Image.SCALE_REPLICATE));
				wordImage = new JLabel(img);
				animalPanel.add(wordImage);
				animal_Count++;
				break;
			case 2:
				img = new ImageIcon(mainControl.getDictionaryController()
						.getDictionary().getWordArr().elementAt(i)
						.getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance(
						(int) (100 * ratio / 2), (int) (100 * ratio / 2),
						Image.SCALE_REPLICATE));
				wordImage = new JLabel(img);
				plantPanel.add(wordImage);
				plant_Count++;
				break;
			case 3:
				img = new ImageIcon(mainControl.getDictionaryController()
						.getDictionary().getWordArr().elementAt(i)
						.getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance(
						(int) (100 * ratio / 2), (int) (100 * ratio / 2),
						Image.SCALE_REPLICATE));
				wordImage = new JLabel(img);
				toolPanel.add(wordImage);
				tool_Count++;
				break;
			case 4:
				img = new ImageIcon(mainControl.getDictionaryController()
						.getDictionary().getWordArr().elementAt(i)
						.getImageURL());
				img = new ImageIcon(img.getImage().getScaledInstance(
						(int) (100 * ratio / 2), (int) (100 * ratio / 2),
						Image.SCALE_REPLICATE));
				wordImage = new JLabel(img);
				nationPanel.add(wordImage);
				nation_Count++;
				break;
			}
		}
		for (int i = 0; i < 30 - animal_Count; i++) {
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance(
					(int) (100 * ratio / 2), (int) (100 * ratio / 2),
					Image.SCALE_REPLICATE));
			wordImage = new JLabel(img);
			animalPanel.add(wordImage);
		}
		for (int i = 0; i < 30 - plant_Count; i++) {
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance(
					(int) (100 * ratio / 2), (int) (100 * ratio / 2),
					Image.SCALE_REPLICATE));
			wordImage = new JLabel(img);
			plantPanel.add(wordImage);
		}
		for (int i = 0; i < 30 - tool_Count; i++) {
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance(
					(int) (100 * ratio / 2), (int) (100 * ratio / 2),
					Image.SCALE_REPLICATE));
			wordImage = new JLabel(img);
			toolPanel.add(wordImage);
		}
		for (int i = 0; i < 30 - nation_Count; i++) {
			img = new ImageIcon("image/word/blind.gif");
			img = new ImageIcon(img.getImage().getScaledInstance(
					(int) (100 * ratio / 2), (int) (100 * ratio / 2),
					Image.SCALE_REPLICATE));
			wordImage = new JLabel(img);
			nationPanel.add(wordImage);
		}
		dicPanel.setVisible(true);
	}
}