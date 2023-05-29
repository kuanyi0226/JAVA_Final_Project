package obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;

import utils.GameImage;
import utils.GamePlane;

public class FoodObj extends GameObj {

	double xspeed;
	double yspeed;
	
	public FoodObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
		super(img, x, y, width, height, speed, frame);
		// TODO Auto-generated constructor stub
		xspeed = speed;
		yspeed = speed/2;
	}

	@Override
	public void paintSelf(Graphics gImage) {
		// TODO Auto-generated method stub
		super.paintSelf(gImage);
		if (x < 0 || x > GamePlane.width - this.width) {
			xspeed = -xspeed;
		}
		x += xspeed;
		y += yspeed;
		if (this.getRec().intersects(this.frame.planeObj.getRec())) {
			GamePlane.shellInterval = 7;
			GamePlane.shellSpeed = 13;
			this.setX(-400);
			this.setY(100);
			GameImage.removeList.add(this);
		}
		
		if (y > GamePlane.height) {
			this.x = -400;
			this.y = 100;
			GameImage.removeList.add(this);
		}
	}

	@Override
	public Double getRec() {
		// TODO Auto-generated method stub
		return super.getRec();
	}

}
