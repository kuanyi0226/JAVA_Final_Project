package eater;
import java.awt.*;



public class Eater {
	Image img = GameImage2.Me;
	Image img2 = GameImage2.MeSuper;
	//position
	int x = 700;
	int y = 500;
	int width = 80;
	int height = 80;
	
	int speed = 30;
	
	int level = 1;
	
	
	void logic() {
		if(GameImage2.UP) {
			y = y - speed;
		}
		if(GameImage2.DOWN) {
			y = y + speed;
		}
		if(GameImage2.LEFT) {
			x = x - speed;
		}
		if(GameImage2.RIGHT) {
			x = x + speed;
		}
		
	}
	
	
	public void paintSelf(Graphics g) {
		logic();
		if(GameFoodie.state == 3) {
			speed = 40;
			g.drawImage(img2, x, y, width+120, height+120, null);
		}else {
			g.drawImage(img, x, y, width+GameImage2.score, height+GameImage2.score, null);
	
		}
	}
	
	public Rectangle getRec() {
		if(GameFoodie.state == 3) {
			return new Rectangle(x, y, width+120, height+120);
		}else {
			return new Rectangle(x, y, width+GameImage2.score, height+GameImage2.score);
	
		}
	}
}
