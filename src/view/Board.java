package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import controller.Main_Controller;

public class Board extends JPanel implements Runnable {

    private final int B_WIDTH = 350;
    private final int B_HEIGHT = 350;
    private final int DELAY = 8;
    private int width,height;
    private JFrame frame;
    private Image star;
    private Thread animator;
    private int x=0, y=0;
    private int dx, dy;
    private Game_Panel panel;
    private Main_Controller main_C;
	
    private Dimension d;
	
	double ratio;
	
    public Board(JFrame frame,Main_Controller main_C, Game_Panel panel) {
    	this.frame = frame;
    	this.main_C = main_C;
    	this.panel = panel;
    	
        initBoard();
    }
    public void kill_Panel()
    {
    	this.setVisible(false);
    	this.getGraphics().clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }
    public Thread getThread()
    {
    	return this.animator;
    }
    private void loadImage() {
		d = Toolkit.getDefaultToolkit().getScreenSize();
		ratio=((double)(d.getWidth()+d.getHeight())*0.0005);

        ImageIcon ii = new ImageIcon(main_C.getGameController().getWord().getImageURL());
        ii = new ImageIcon(ii.getImage().getScaledInstance((int)(280*ratio/2),(int)(280*ratio/2), Image.SCALE_REPLICATE));
        star = ii.getImage();
    }

    private void initBoard() {
    	this.setdemension();
        //setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        setDoubleBuffered(true);

        loadImage();
    }
    public void setdemension()
    {
    	this.width = frame.getWidth();
    	this.height = frame.getHeight();
    	x = width/2-150;
    	y = height/2-150;
    	System.out.println("dd");
    	switch(main_C.getGameController().getWord().getType()){
        case 1 :
        	dx = width/2 - width/4-150;
        	dy = height/2 - height/4-150;
        	break;
        case 2:
        	dx = width/2 + width/4-150;
        	dy = height/2 - height/4-150;
        	break;
        case 3 :
        	dx = width/2 - width/4-150;
        	dy = height/2 + height/4-150;
        	break;
        case 4 :
        	dx = width/2 + width/4-150;
        	dy = height/2 + height/4-150;
        	break;
        }
    	System.out.println("dd1");
    }

    @Override
    public void addNotify() {
        super.addNotify();
        
        animator = new Thread(this);
        animator.setName("animation");
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	g.fillRect(0, 0, this.getWidth(),this.getHeight());
        ImageIcon categoryicon = new ImageIcon("image/reward/category.jpg");
	    g.drawImage(categoryicon.getImage(), 0, 0, frame.getWidth(), frame.getHeight(), null);
        drawStar(g);
    }

    private void drawStar(Graphics g) {

        g.drawImage(star, x, y, this);
        Toolkit.getDefaultToolkit().sync();
    }

    private boolean cycle() {

        if(x>dx && y>dy)
        {
        	x--;
        	y--;
        	if(x==dx || y==dy)
            	return false;
            else
            	return true;
        }else if(x>dx && y<dy){
        	x--;
        	y++;
        	if(x==dx || y==dy)
            	return false;
            else
            	return true;
        }else if(x<dx && y>dy){
        	x++;
        	y--;
        	if(x==dx || y==dy)
            	return false;
            else
            	return true;
        }else
        {
        	x++;
        	y++;
        	if(x==dx || y==dy)
            	return false;
            else
            	return true;
        }
        
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();
        
        while (cycle()) {
        	
            repaint();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                System.out.println("Interrupted: " + e.getMessage());
            }

            beforeTime = System.currentTimeMillis();
        }
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("2¹ø Run");
        kill_Panel();
        main_C.getGameController().gameStart();
        panel.refresh();
    }
}
