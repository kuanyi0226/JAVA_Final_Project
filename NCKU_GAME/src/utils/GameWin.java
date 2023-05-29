package utils;

import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import obj.BgObj;
import obj.BossObj;
import obj.BulletObj;
import obj.EnemyObj;
import obj.PlaneObj;
import obj.ShellObj;

public class GameWin extends JFrame {
	
	public static int state = 0;
	public static int score = 0;
	public static int time = 120;
	public static int width = 1000;
	public static int height = 800;
	int count = 1;
	int enemyCount = 0;
	
	Timer timer = new Timer();
	Image offScreen = null;
	
	BgObj bgObj = new BgObj(GameImage.bgImg, 0, -6400, 2);
	public PlaneObj planeObj = new PlaneObj(GameImage.planeImg, 350, 400, 50, 50, 0, this);
	public BossObj bossObj = null;
	
	TimerTask task = new TimerTask() {
		public void run() {
			time--;
		}
	};
	
	public void launch() {
		
		// window settings
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setTitle("飛機大戰");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		Timer timer = new Timer();
		
		GameImage.gameObjList.add(bgObj);
		GameImage.gameObjList.add(planeObj);
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && state == 0) {
					state = 1;
					timer.schedule(task, 1000, 1000);
				}
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					switch (state) {
					case 1:
						state = 2;
						break;
					default:
						state = 1;
						break;
					}
				}
			}
		});
		
		while (true) {
			if (state == 1) {
				createObj();
				repaint();
				count++;
			}
			if (state == 3) {
				//repaint();
				break;
			}
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void paint(Graphics g) {
		if (offScreen == null) {
			offScreen = createImage(width, height);
		}
		Graphics gImage = offScreen.getGraphics();
		gImage.fillRect(0, 0, width, height);
		if (state == 0) {
			gImage.drawImage(GameImage.bgImg, 0, 0, width, height, this);
			gImage.drawImage(GameImage.bossImg, width/2 - GameImage.bossImg.getWidth(this)/2, height/5, null);
			gImage.drawImage(GameImage.explodeImg, width/2 - GameImage.explodeImg.getWidth(this)/2, height*3/5, null);
			GameImage.drawWord(gImage, "Click to Start", Color.YELLOW, 40, width/2 - 120, height/2);
		}
		if (state == 1) {
			GameImage.gameObjList.addAll(GameImage.explodeObjList);
			for (int i = 0; i < GameImage.gameObjList.size(); i++) {
				GameImage.gameObjList.get(i).paintSelf(gImage);
			}
			GameImage.gameObjList.removeAll(GameImage.removeList);
		}
		if (state == 3) {
			timer.cancel();
			timer.purge();
			gImage.drawImage(GameImage.explodeImg, planeObj.getX()+58, planeObj.getY(),null);
			GameImage.drawWord(gImage, "GAME OVER", Color.RED, 50, width/2 - 120, height/2);
		}
		if (state == 4) {
			timer.cancel();
			gImage.drawImage(GameImage.explodeImg, bossObj.getX()+30, bossObj.getY(),null);
			GameImage.drawWord(gImage, "GAME CLEAR", Color.RED, 50, width/2 - 120, height/2);
		}

		GameImage.drawWord(gImage, "Time: " + time, Color.GREEN, 40, 20, 100);
		GameImage.drawWord(gImage, "Score: " + score, Color.GREEN, 40, width - 200, 100);
		g.drawImage(offScreen, 0, 0, null);
		//System.out.println(GameImage.gameObjList.size());
	}
	
	void createObj() {
		if (count%10 == 0) {
			GameImage.shellObjList.add(new ShellObj(GameImage.shellImg, planeObj.getX()+58, planeObj.getY()- 8, 50, 50, 8, this));
			GameImage.gameObjList.add(GameImage.shellObjList.get(GameImage.shellObjList.size()-1));
		}
		
		if (count >= 100 && count%20 == 0) {
			GameImage.enemyObjList.add(new EnemyObj(GameImage.enemyImg, (int)(Math.random()*18 + 1)*50, 0, 50, 17, 5, this));
			GameImage.gameObjList.add(GameImage.enemyObjList.get(GameImage.enemyObjList.size()-1));
			enemyCount++;
		}

		if (count%15 == 0 && bossObj != null) {
			GameImage.bulletObjList.add(new BulletObj(GameImage.bulletImg, bossObj.getX()+74, bossObj.getY()+85, 25, 25, 8, this));
			GameImage.gameObjList.add(GameImage.bulletObjList.get(GameImage.bulletObjList.size()-1));
		}
		
		if (enemyCount > 10 && bossObj == null) {
			bossObj = new BossObj(GameImage.bossImg, 325, 40, 75, 75, 5, this);
			GameImage.gameObjList.add(bossObj);
		}
	}
}
