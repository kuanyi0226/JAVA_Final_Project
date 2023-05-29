package obj;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

import utils.GameImage;
import utils.GameWin;

public class BulletObj extends GameObj {

	public BulletObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BulletObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
		super(img, x, y, width, height, speed, frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintSelf(Graphics gImage) {
		// TODO Auto-generated method stub
		super.paintSelf(gImage);
		y += speed;
		
		if (this.getRec().intersects(this.frame.planeObj.getRec())) {
			GameWin.state = 3;
		}
		
		if (y > GameWin.height) {
			this.x = -300;
			this.y = 300;
			GameImage.removeList.add(this);
		}
	}

	@Override
	public Rectangle2D.Double getRec() {
		// TODO Auto-generated method stub
		return super.getRec();
	}

	
}
