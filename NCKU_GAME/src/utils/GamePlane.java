package utils;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.sound.sampled.*;
import javax.swing.*;

import obj.*;

public class GamePlane extends JFrame {
	
	public static int state = 0;
	public static int score = 0;
	public static int time = 100;
	public static int width = 1000;
	public static int height = 800;
	public static int shellInterval = 12;
	public static int shellSpeed = 8;
	public static boolean isComplete = false;
	int count = 1;
	int enemyCount = 0;
	
	Image offScreen = null;
	Image currentBgImg = null;
	Clip bgm = null;
	
	BgObj bgObj = new BgObj(GameImage.bgImg, 0, -6400, 2);
	public PlaneObj planeObj = new PlaneObj(GameImage.playerImg, 350, 400, 80, 80, 0, this);
	public BossObj bossObj = null;
	public FoodObj foodObj = null;
	public StoneObj stoneObj = null;
	
	public void launch() {
		
		// window settings
        this.setFocusable(true);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setTitle("飛機大戰");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		GameImage.gameObjList.add(bgObj);
		GameImage.gameObjList.add(planeObj);
		
		showRules();
		
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == 1 && state == 0) {
					state = 1;
					loadBGM("sound/planeBGM.wav");
					playBGM();
				}
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					switch (state) {
					case 1:
						state = 2;
						stopBGM();
						break;
					case 2:
						state = 1;
						playBGM();
						break;
					default:
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
			else if (state == 3) {
				stopBGM();
				score = 0;
				repaint();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isComplete = true;
				break;
			}
			else if (state == 4) {
				stopBGM();
				score += 30;
				score += time/2;
				repaint();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isComplete = true;
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
		if (state == 2) {
	        // Game paused state
	        gImage.drawImage(currentBgImg, 0, 0, width, height, this);
	        GameImage.drawWord(gImage, "PAUSED", Color.YELLOW, 50, width/2 - 100, height/2);
	    }
		if (state == 3) {
			gImage.drawImage(GameImage.loseImg, planeObj.getX(), planeObj.getY(),null);
			GameImage.drawWord(gImage, "GAME OVER", Color.RED, 50, width/2 - 150, height/2 - 40);
			GameImage.drawWord(gImage, "Total score: " + score, Color.RED, 50, width/2 - 150, height/2 + 40);
		}
		if (state == 4) {
			gImage.drawImage(GameImage.explodeImg, bossObj.getX()+30, bossObj.getY(),null);
			GameImage.drawWord(gImage, "GAME CLEAR", Color.RED, 50, width/2 - 150, height/2 - 40);
			GameImage.drawWord(gImage, "Total score: " + score, Color.RED, 50, width/2 - 150, height/2 + 40);
		}

		GameImage.drawWord(gImage, "Time: " + time, Color.GREEN, 40, 20, 100);
		GameImage.drawWord(gImage, "Score: " + score, Color.GREEN, 40, width - 200, 100);
		g.drawImage(offScreen, 0, 0, null);
		currentBgImg = offScreen;
	}
	
	void createObj() {
		if (!planeObj.invulnerable && count%shellInterval == 0) {
			GameImage.shellObjList.add(new ShellObj(GameImage.shellImg, planeObj.getX()+50, planeObj.getY()- 8, 15, 30, shellSpeed, this));
			GameImage.gameObjList.add(GameImage.shellObjList.get(GameImage.shellObjList.size()-1));
		}
		
		if (count > 100 && count%30 == 0) {
			GameImage.enemyObjList.add(new EnemyObj(GameImage.enemyImg[(int)(Math.random()*7)], (int)(Math.random()*18 + 1)*50, 0, 60, 60, 5, this));
			GameImage.gameObjList.add(GameImage.enemyObjList.get(GameImage.enemyObjList.size()-1));
			enemyCount++;
		}

		if (bossObj != null && count%22 == 0) {
			GameImage.bulletObjList.add(new BulletObj(GameImage.bulletImg, bossObj.getX()+30, bossObj.getY()+85, 25, 25, 8, this));
			GameImage.gameObjList.add(GameImage.bulletObjList.get(GameImage.bulletObjList.size()-1));
			GameImage.bulletObjList.add(new BulletObj(GameImage.bulletImg, bossObj.getX()+210, bossObj.getY()+85, 25, 25, 8, this));
			GameImage.gameObjList.add(GameImage.bulletObjList.get(GameImage.bulletObjList.size()-1));
		}

		if (enemyCount > 30 && foodObj == null) {
			foodObj = new FoodObj(GameImage.foodImg, (int)(Math.random()*18 + 1)*50, 0, 150, 115, 4, this);
			GameImage.gameObjList.add(foodObj);
		}
		
		if (enemyCount > 0 && bossObj == null) {
			bossObj = new BossObj(GameImage.bossImg, width/2 - 150, 40, 270, 100, 5, this);
			GameImage.gameObjList.add(bossObj);
		}
		
		if (bossObj != null && bossObj.life == 90 && stoneObj == null) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			stopBGM();
			loadBGM("sound/stoneBGM.wav");
			playBGM();
			stoneObj = new StoneObj(GameImage.stoneImg, width/2 - 300, -600, 600, 600, 10, this);
			GameImage.gameObjList.add(stoneObj);
		}
		
		
		if (count%40 == 0) {
			time--;
			if (time == 0) {
				state = 3;
			}
		}
	}
	
	private void loadBGM(String bgmFilePath) {
		try {
			File bgmFile = new File(bgmFilePath);
			bgm = AudioSystem.getClip();
			AudioInputStream ais = AudioSystem.getAudioInputStream(bgmFile);
			bgm.open(ais);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void playBGM() {
		if (bgm != null) {
            bgm.loop(Clip.LOOP_CONTINUOUSLY);
        }
	}
	
	private void stopBGM() {
		if (bgm != null) {
            bgm.stop();
        }
	}
	
	private void showRules() {
	    // Create a separate dialog to show the rules
	    JDialog rulesDialog = new JDialog(this, "Rules", Dialog.ModalityType.APPLICATION_MODAL);
	    rulesDialog.setSize(400, 200);
	    rulesDialog.setLocationRelativeTo(this);

	    JTextArea rulesTextArea = new JTextArea();
	    rulesTextArea.setText("Game Rules:\n\n" +
	            "1. Use the mouse to control the plane.\n" +
	            "2. Avoid enemy objects and boss projectiles.\n" +
	            "3. Collect power-ups.\n" +
	            "4. Destroy enemy objects to earn points(40 the most).\n" +
	            "5. Kill the boss as soon as you can.\n\n");

	    rulesTextArea.setEditable(false);
	    rulesTextArea.setFont(new Font("Arial", Font.PLAIN, 16));

	    rulesDialog.add(rulesTextArea);
	    rulesDialog.setVisible(true);
	}
	
	
}
