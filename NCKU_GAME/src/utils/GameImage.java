package utils;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import obj.GameObj;
import obj.ShellObj;
import obj.BulletObj;
import obj.EnemyObj;
import obj.ExplodeObj;

public class GameImage {
	public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
	public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
	public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode.gif");
	public static Image playerImg = Toolkit.getDefaultToolkit().getImage("imgs/player.png");
	public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
	public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bullet.png");
	public static Image loseImg = Toolkit.getDefaultToolkit().getImage("imgs/lose.png");
	public static Image foodImg = Toolkit.getDefaultToolkit().getImage("imgs/food.png");
	public static Image stoneImg = Toolkit.getDefaultToolkit().getImage("imgs/stone.png");
	public static Image[] enemyImg = new Image[7];
	static {
		for (int i = 0; i < enemyImg.length; i++) {
			enemyImg[i] = Toolkit.getDefaultToolkit().getImage("imgs/enemy"+i+".png");
		}
	}
	
	public static List<GameObj> gameObjList = new ArrayList<>();
	public static List<GameObj> removeList = new ArrayList<>();
	public static List<ShellObj> shellObjList = new ArrayList<>();
	public static List<BulletObj> bulletObjList = new ArrayList<>();
	public static List<EnemyObj> enemyObjList = new ArrayList<>();
	public static List<ExplodeObj> explodeObjList = new ArrayList<>();
	
	public static void drawWord(Graphics gImage, String str, Color color, int size, int x, int y) {
		gImage.setColor(color);
		gImage.setFont(new Font("Arial", Font.BOLD, size));
		gImage.drawString(str, x, y);
	}
}
