package foodie;

import java.awt.*;
import java.util.*;
import java.util.List;


public class GameImage {
	//time
	public static int timeleft = 10;
	
	//money
	public static int money = 1000;
	
	//me move
	static boolean UP = false;
	static boolean DOWN = false;
	static boolean LEFT = false;	
	static boolean RIGHT = false;
	//score
	public static int score = 0;
	//level
	static int level = 0;
	//much rest
	public static List<Restaurant> RestaurantList = new ArrayList<>();
	
	
	//background pic
	public static Image bgimg = Toolkit.getDefaultToolkit().getImage("foodieImgs/bk.jpg");
	
	//restaurant pic
	public static Image rest1 = Toolkit.getDefaultToolkit().getImage("foodieImgs/hl.png");
	public static Image rest2 = Toolkit.getDefaultToolkit().getImage("foodieImgs/hl2.png");
	public static Image rest3 = Toolkit.getDefaultToolkit().getImage("foodieImgs/dm.png");
	public static Image rest4 = Toolkit.getDefaultToolkit().getImage("foodieImgs/lfz.png");
	
	public static Image rest5 = Toolkit.getDefaultToolkit().getImage("foodieImgs/lg.png");
	public static Image rest6 = Toolkit.getDefaultToolkit().getImage("foodieImgs/hu.png");
	public static Image rest7 = Toolkit.getDefaultToolkit().getImage("foodieImgs/south.png");
	public static Image rest8 = Toolkit.getDefaultToolkit().getImage("foodieImgs/gd.png");
	
	public static Image rest9 = Toolkit.getDefaultToolkit().getImage("foodieImgs/vacano.png");
	public static Image rest10 = Toolkit.getDefaultToolkit().getImage("foodieImgs/zs.png");
	public static Image rest11 = Toolkit.getDefaultToolkit().getImage("foodieImgs/eight.png");
	public static Image rest12 = Toolkit.getDefaultToolkit().getImage("foodieImgs/day.png");
	
	public static Image rest13 = Toolkit.getDefaultToolkit().getImage("foodieImgs/sea.png");
	public static Image rest14 = Toolkit.getDefaultToolkit().getImage("foodieImgs/nm.png");
	public static Image rest15 = Toolkit.getDefaultToolkit().getImage("foodieImgs/st.png");
	public static Image rest16 = Toolkit.getDefaultToolkit().getImage("foodieImgs/m.png");
	
	
	//me pic
	public static Image Me = Toolkit.getDefaultToolkit().getImage("foodieImgs/me.png");
	public static Image MeSuper = Toolkit.getDefaultToolkit().getImage("foodieImgs/me2.png");
	
	
	//write word
	public static void drawWord(Graphics g,String str, Color color, int size, int x, int y) {
		g.setColor(color);
		g.setFont(new Font("Arial", Font.BOLD, size));
		g.drawString(str, x, y);
	}
	

}
