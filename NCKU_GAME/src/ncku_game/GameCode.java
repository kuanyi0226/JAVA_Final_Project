package ncku_game;



import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class GameCode extends JFrame {
	public GameCode() {
		 setFocusableWindowState(true);
	}
	Panel p = new Panel();
	private int width = 1000;
	private int height = 801;
	
	public void launch() {
		
		setFocusableWindowState(true);
		this.addKeyListener(new KeyAdapter() {        
 			public void keyPressed(KeyEvent e) {
	    		super.keyPressed(e);
	    		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
	    			if(Panel.isOverlap) {
	    				Panel.isOverlap = false;
	    				p.initDraw();
	    			}else {
	    				Panel.isStart = !Panel.isStart;
	    			}
	    			
	    			repaint();
	    		}
	    		if(e.getKeyCode() == 38) {
	    			Panel.direction = "U";
	    		}
	    		if(e.getKeyCode() == 40) {
	    			Panel.direction = "D";
	    		}
	    		if(e.getKeyCode() == 37) {
	    			Panel.direction = "L";
	    		}
	    		if(e.getKeyCode() == 39) {
	    			Panel.direction = "R";
	    		}
	    	}
 		});
		//avoid
		requestFocusInWindow();
        this.setFocusable(true);
        
	    this.setVisible(true);
		this.setSize(width, height);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    this.add(p);
	    this.setVisible(true);
	    
	    //開始計時
	    while(!Panel.isComplete) {
	    	if(Panel.foodIndex == 24) 
	    		Panel.isComplete = true;
	    	if(Panel.isStart) 
	    		Panel.elapsedSeconds = Panel.elapsedSeconds + 1;
	    	
	    	try {
				Thread.sleep(1200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
	    }
	};
}
