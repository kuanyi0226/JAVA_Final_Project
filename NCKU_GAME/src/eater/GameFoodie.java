package eater;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameFoodie extends JFrame{
	public static boolean isComplete = false;
	public static boolean isStop = false;
	
	public static int nextLevel = 10;
	//state 
	public static int state = 0;
	
	Image offScreenImage;
	
	private int width = 1100;
	private int height = 800;

	private double random;
	
	private int repeatCount = 0;
	
	
	
	Background bg = new Background();
	
	Restaurant rest;
	
	Eater eater = new Eater();
	
	public void launch() {
		//this.setFocusable(true);
	    this.setVisible(true);
		this.setSize(width, height);
		//this.setResizable(false);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    //滑鼠點擊
	    this.addMouseListener(new MouseAdapter() {
	    	public void mouseClicked(MouseEvent e) {
	    		super.mouseClicked(e);
	    		if(e.getButton() == 1 && state == 0) {
	    			state = 1;
	    			repaint();
	    		}
	    	}	    	
	    });
	    //MOVE listener
	    this.addKeyListener(new KeyAdapter() {
	    	
	    	public void keyPressed(KeyEvent e) {
	    		super.keyPressed(e);
	    		if(e.getKeyCode() == 38) {
	    			GameImage2.UP = true;
	    		}
	    		if(e.getKeyCode() == 40) {
	    			GameImage2.DOWN = true;
	    		}
	    		if(e.getKeyCode() == 37) {
	    			GameImage2.LEFT = true;
	    		}
	    		if(e.getKeyCode() == 39) {
	    			GameImage2.RIGHT = true;
	    		}
	    		if(e.getKeyCode() == 32) {
	    			switch(state) {
	    				case 1:
	    				case 3:
	    					isStop = true;
	    					GameImage2.drawWord(getGraphics(), "Press Space to Continue", Color.blue, 50, 250, 440);
	    					state = 4;
	    					break;
	    				case 4:
	    					isStop = false;
	    					if(eater.level >= 3) {
	    						state = 3;
	    					}else {
	    						state = 1;
	    					}
	    					break;
	    				
	    			}
	    		}
	    	}
	    	
	    	public void keyReleased(KeyEvent e) {
	    		super.keyReleased(e);
	    		if(e.getKeyCode() == 38) {
	    			GameImage2.UP = false;
	    		}
	    		if(e.getKeyCode() == 40) {
	    			GameImage2.DOWN = false;
	    		}
	    		if(e.getKeyCode() == 37) {
	    			GameImage2.LEFT = false;
	    		}
	    		if(e.getKeyCode() == 39) {
	    			GameImage2.RIGHT = false;
	    		}
	    	}
	    	
	    	
	    	
	    });
	    //create rest period
	    while(!isComplete) {
		    	if(repeatCount%20 == 0 && !isStop) {
		    		GameImage2.timeleft = GameImage2.timeleft - 1;
		    	}
		    	if(GameImage2.timeleft == 0) {
		    		state = 2;
		    		isComplete = true;
		    	}
		    	repaint();
		    	repeatCount++;
		    	try {
					Thread.sleep(40);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	
	    }
	}//launch end
	
	public void closeWindow() {
        dispose();
    }
	
	
	
	
	//image show
	public void paint(Graphics g) {
		
		offScreenImage = createImage(width, height);
		Graphics gImage  = offScreenImage.getGraphics();
		bg.paintSelf(gImage, eater.level);
	
		switch(state) {
		case 0://wait for start
			break;
		case 1://in game
			eater.paintSelf(gImage);
			logic();
			for(Restaurant rest:GameImage2.RestaurantList) {
				rest.paintSelf(gImage);
			}
			break;
		case 2://end
//			for(Restaurant rest:GameImage2.RestaurantList) {
//				rest.paintSelf(gImage);
//			}
			break;
		case 3://super state
			eater.paintSelf(gImage);
			logic();
			for(Restaurant rest:GameImage2.RestaurantList) {
				rest.paintSelf(gImage);
			}
			break;
		case 4:
			
			return;
		default:
		
		}
		
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	void logic() {
		//me level
		if(GameImage2.score < 10) {
			GameImage2.level = 0;
			eater.level = 1;
			nextLevel = 16 - GameImage2.score;
			
		}else if(GameImage2.score <= 16) {
			GameImage2.level = 1;
			nextLevel = 16 - GameImage2.score;
			
		}else if(GameImage2.score <= 28) {
			GameImage2.level = 2;
			eater.level = 2;
			nextLevel = 28 - GameImage2.score;
			
		}else if(GameImage2.score <= 80) {
			GameImage2.level = 3;
			eater.level = 3;
			nextLevel = 80 - GameImage2.score;
			
		}else if(GameImage2.score <= 1000) {
			state = 3;
			GameImage2.level = 4;
			eater.level = 5;
			
		}
		random = Math.random();
		
		
		
		//lv不同 餐廳不同
		switch(GameImage2.level) {
			case 4://super mode
				if(repeatCount %8 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_15();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_14();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_13();
						GameImage2.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_16();
						GameImage2.RestaurantList.add(restaurant);
					}
				}
				break;	
			//under 300
			case 3:
			case 2:
				if(repeatCount %40 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_9();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_10();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_11();
						GameImage2.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_12();
						GameImage2.RestaurantList.add(restaurant);
					}
				}
			//under 200
			case 1:
				if(repeatCount %30 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_5();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_6();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_7();
						GameImage2.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_8();
						GameImage2.RestaurantList.add(restaurant);
					}
				}
			//under 100
			case 0:
				if(repeatCount %15 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_l();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_r();
						GameImage2.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_3();
						GameImage2.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_4();
						GameImage2.RestaurantList.add(restaurant);
					}
				}
			break;
			
			
		}
		//move way
		for(Restaurant rest:GameImage2.RestaurantList) {
			rest.x = rest.x + rest.direction*rest.speed;
			//撞到
			if(eater.getRec().intersects(rest.getRec()) ) {
				if(eater.level >= rest.type) {
					rest.x = -200;
					rest.y = -200;
					GameImage2.score = GameImage2.score + rest.count;
				}else {
					rest.x = -200;
					rest.y = -200;
					GameImage2.score = GameImage2.score + rest.count;
					GameImage2.money = GameImage2.money - rest.count*100;
					//lose 
					if(GameImage2.money == 0) {
						state = 2; ///lose
						isComplete = true;
					}
				}
			}
		}
		
	}
	
	
	
	
	public static void main(String[] arg) {
		GameFoodie gameFoodie = new GameFoodie();	
		gameFoodie.launch();
		while(true) {
			if(isComplete) {
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
//				tempScore = (""+GameImage2.score);
//				setToServer = true;
//				receiveFromServer = true;
//				changePage = false;
				
				gameFoodie.closeWindow();
				gameFoodie = null;
				break;
			}
		}
		
		
	}

}
