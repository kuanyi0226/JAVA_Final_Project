package plane;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D.Double;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StoneObj extends GameObj {

	boolean hitted = false;
	
	public StoneObj() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoneObj(Image img, int x, int y, int width, int height, double speed, GamePlane frame) {
		super(img, x, y, width, height, speed, frame);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paintSelf(Graphics gImage) {
		// TODO Auto-generated method stub
		super.paintSelf(gImage);
		y += speed;
		if (!hitted && this.getRec().intersects(this.frame.planeObj.getRec())) {
			this.frame.planeObj.invulnerable = true;
			hitted = true;
			GamePlane.playSoundEffect("sound/expl0.wav");
			GamePlane.playSoundEffect("sound/hit.wav");
		}
		
		if (y > GamePlane.height) {
			GameImage.removeList.add(this);
		}
	}

	@Override
	public Double getRec() {
		// TODO Auto-generated method stub
		return super.getRec();
	}

}
