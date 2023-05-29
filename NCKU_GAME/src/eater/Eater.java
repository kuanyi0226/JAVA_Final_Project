package eater;
import java.awt.*;



public class Eater {
	Image img = GameImage.Me;
	Image img2 = GameImage.MeSuper;
	//position
	int x = 700;
	int y = 500;
	int width = 50;
	int height = 50;
	
	int speed = 20;
	
	int level = 1;
	
	
	void logic() {
		if(GameImage.UP) {
			y = y - speed;
		}
		if(GameImage.DOWN) {
			y = y + speed;
		}
		if(GameImage.LEFT) {
			x = x - speed;
		}
		if(GameImage.RIGHT) {
			x = x + speed;
		}
		
	}
	
	
	public void paintSelf(Graphics g) {
		logic();
		if(GameFoodie.state == 3) {
			speed = 30;
			g.drawImage(img2, x, y, width+120, height+120, null);
		}else {
			g.drawImage(img, x, y, width+GameImage.score, height+GameImage.score, null);
	
		}
	}
	
	public Rectangle getRec() {
		if(GameFoodie.state == 3) {
			return new Rectangle(x, y, width+120, height+120);
		}else {
			return new Rectangle(x, y, width+GameImage.score, height+GameImage.score);
	
		}
	}
}
