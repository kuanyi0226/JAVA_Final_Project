package obj;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import utils.GameWin;

public class PlaneObj extends GameObj{

	
	public PlaneObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaneObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
		super(img, x, y, width, height, speed, frame);
		// TODO Auto-generated constructor stub
		this.frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if (GameWin.state != 3) {
					if (e.getX() > 0 + width && e.getX() < GameWin.width - width) {
						PlaneObj.super.x = e.getX() - width;
					}
					if (e.getY() > 0 + height*2 && e.getY() < GameWin.height - height) {
						PlaneObj.super.y = e.getY() - height;
					}	
				}
			}
		});
	}

	@Override
	public void paintSelf(Graphics gImage) {
		// TODO Auto-generated method stub
		super.paintSelf(gImage);
		
		if (this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())) {
			GameWin.state = 3;
		}
	}

	@Override
	public Rectangle2D.Double getRec() {
		// TODO Auto-generated method stub
		return super.getRec();
	}

	
}
