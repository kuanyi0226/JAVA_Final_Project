package eater;

import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GameFoodie extends JFrame{
	
	
	//state 
	static int state = 0;
	
	Image offScreenImage;
	
	private int width = 1400;
	private int height = 900;

	private double random;
	
	private int repeatCount = 0;
	
	
	
	Background bg = new Background();
	
	Restaurant rest;
	
	Eater eater = new Eater();
	
	public void launch() {
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
	    			GameImage.UP = true;
	    		}
	    		if(e.getKeyCode() == 40) {
	    			GameImage.DOWN = true;
	    		}
	    		if(e.getKeyCode() == 37) {
	    			GameImage.LEFT = true;
	    		}
	    		if(e.getKeyCode() == 39) {
	    			GameImage.RIGHT = true;
	    		}
	    	}
	    	
	    	public void keyReleased(KeyEvent e) {
	    		super.keyReleased(e);
	    		if(e.getKeyCode() == 38) {
	    			GameImage.UP = false;
	    		}
	    		if(e.getKeyCode() == 40) {
	    			GameImage.DOWN = false;
	    		}
	    		if(e.getKeyCode() == 37) {
	    			GameImage.LEFT = false;
	    		}
	    		if(e.getKeyCode() == 39) {
	    			GameImage.RIGHT = false;
	    		}
	    	}
	    	
	    	
	    	
	    });
	    //create rest period
	    while(true) {
	    	if(repeatCount%20 == 0) {
	    		GameImage.timeleft = GameImage.timeleft - 1;
	    	}
	    	if(GameImage.timeleft == 0) {
	    		state = 2;
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
			for(Restaurant rest:GameImage.RestaurantList) {
				rest.paintSelf(gImage);
			}
			break;
		case 2://end
//			for(Restaurant rest:GameImage.RestaurantList) {
//				rest.paintSelf(gImage);
//			}
			break;
		case 3://super state
			eater.paintSelf(gImage);
			logic();
			for(Restaurant rest:GameImage.RestaurantList) {
				rest.paintSelf(gImage);
			}
			break;
		case 4:
			break;
		default:
		
		}
		
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	void logic() {
		//me level
		if(GameImage.score < 10) {
			GameImage.level = 0;
			eater.level = 1;
			
		}else if(GameImage.score <= 20) {
			GameImage.level = 1;
			
		}else if(GameImage.score <= 50) {
			GameImage.level = 2;
			eater.level = 2;
			
		}else if(GameImage.score <= 115) {
			GameImage.level = 3;
			eater.level = 3;
			
		}else if(GameImage.score <= 1000) {
			state = 3;
			GameImage.level = 4;
			eater.level = 5;
			
		}
		random = Math.random();
		
		
		
		//lv不同 餐廳不同
		switch(GameImage.level) {
			case 4://super mode
				if(repeatCount %5 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_13();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_14();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_15();
						GameImage.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_16();
						GameImage.RestaurantList.add(restaurant);
					}
				}
				break;	
			//under 300
			case 3:
			case 2:
				if(repeatCount %30 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_9();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_10();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_11();
						GameImage.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_12();
						GameImage.RestaurantList.add(restaurant);
					}
				}
			//under 200
			case 1:
				if(repeatCount %20 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_5();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_6();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_7();
						GameImage.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_8();
						GameImage.RestaurantList.add(restaurant);
					}
				}
			//under 100
			case 0:
				if(repeatCount %10 == 0) {
					if(random > 0.75) {
						Restaurant restaurant = new Rest_l();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.5 && random <= 0.75){
						Restaurant restaurant = new Rest_r();
						GameImage.RestaurantList.add(restaurant);
					}else if(random > 0.25 && random <= 0.5) {
						Restaurant restaurant = new Rest_3();
						GameImage.RestaurantList.add(restaurant);
					}else{
						Restaurant restaurant = new Rest_4();
						GameImage.RestaurantList.add(restaurant);
					}
				}
			break;
			
			
		}
		//move way
		for(Restaurant rest:GameImage.RestaurantList) {
			rest.x = rest.x + rest.direction*rest.speed;
			//撞到
			if(eater.getRec().intersects(rest.getRec()) ) {
				if(eater.level >= rest.type) {
					rest.x = -200;
					rest.y = -200;
					GameImage.score = GameImage.score + rest.count;
				}else {
					rest.x = -200;
					rest.y = -200;
					GameImage.score = GameImage.score + rest.count;
					GameImage.money = GameImage.money - rest.count*100;
					//lose 
					if(GameImage.money == 0) {
						state = 2; ///lose
					}
				}
			}
		}
		
	}
	
	
	
	
	// public static void main(String[] arg) {
	// 	//GameFoodie gameFoodie = new GameFoodie();
	// 	//gameFoodie.launch();
	// }

}
