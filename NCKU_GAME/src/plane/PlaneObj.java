package plane;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

public class PlaneObj extends GameObj{

	public boolean invulnerable = true;
	int count = 0;
	
	public PlaneObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaneObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
		super(img, x, y, width, height, speed, frame);
		// TODO Auto-generated constructor stub
		this.frame.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				super.mouseMoved(e);
				if (GamePlane.state != 3) {
					if (e.getX() > 0 + width && e.getX() < GamePlane.width) {
						PlaneObj.super.x = e.getX() - width;
					}
					if (e.getY() > 0 + height && e.getY() < GamePlane.height) {
						PlaneObj.super.y = e.getY() - height;
					}	
				}
			}
		});
	}

	@Override
	public void paintSelf(Graphics gImage) {
		// TODO Auto-generated method stub
		if (invulnerable) {
	        // Toggle visibility of the image based on time
	        long currentTime = System.currentTimeMillis();
	        boolean isVisible = (currentTime / 200) % 2 == 0; // Blink every 200 milliseconds
	        
	        if (isVisible) {
	            // Draw the image only when it should be visible
	            super.paintSelf(gImage);
	            count++;
	        }
	        
	        if (count > 50) {
	        	invulnerable = false;
	        	count = 0;
	        }
	        
	    } else {
	        // Draw the image normally when not invulnerable
	        super.paintSelf(gImage);
	    }
		
		if (!invulnerable && this.frame.bossObj != null && this.getRec().intersects(this.frame.bossObj.getRec())) {
			GamePlane.playSoundEffect("sound/hit.wav");
			invulnerable = true;
		}
	}

	@Override
	public Rectangle2D.Double getRec() {
		// TODO Auto-generated method stub
		return new Rectangle2D.Double(x+5,y,width,height);
	}

	
}
