package eater;
import java.awt.*;


public class Background {
	void paintSelf(Graphics g, int level) {
		g.drawImage(GameImage.bgimg, 0, 0, null);
		
		switch(GameFoodie.state) {
			case 0:
				GameImage.drawWord(g, "Start", Color.red, 80,600, 500);
				break;
			case 1:
				GameImage.drawWord(g, "Time: "+GameImage.timeleft, Color.orange, 40, 150, 100);
				GameImage.drawWord(g, "You can spend:  "+levelToMoney(level-1), Color.orange, 40, 700, 100);
				GameImage.drawWord(g, "score: "+GameImage.score , Color.orange, 30, 1000, 850);
				GameImage.drawWord(g, "Bank savings: "+GameImage.money , Color.orange, 30, 100, 850);
				break;
			case 2:
				GameImage.drawWord(g, "YOU GOT:"+GameImage.score, Color.orange, 70, 410, 500);
				break;
			case 3:
				GameImage.drawWord(g, "Super Mode", Color.orange, 70, 500, 100);
				GameImage.drawWord(g, "score: "+GameImage.score , Color.orange, 30, 1000, 850);
				GameImage.drawWord(g, "Time: "+GameImage.timeleft , Color.orange, 30, 100, 850);
				break;
			case 4:
				break;
				
		}
	}
	
	String levelToMoney(int level) {
		switch(level){
			case 0:
				return "under 100";
			case 1:
				return "under 200";
			case 2:
				return "under 300";
			case 3:
				return "money free";
			default:
				return "null";
		}
	}
	
	
}
