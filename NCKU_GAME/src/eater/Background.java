package eater;
import java.awt.*;


public class Background {
	void paintSelf(Graphics g, int level) {
		g.drawImage(GameImage2.bgimg, 0, 0, null);
		Color brown = new Color(139, 69, 19);
		Color oliveGreen = new Color(55, 120, 47);
		
		switch(GameFoodie.state) {
			case 0:
				GameImage2.drawWord(g, "Start", brown, 70, 490, 440);
				break;
			case 1:
				GameImage2.drawWord(g, "Time: "+GameImage2.timeleft, oliveGreen , 33, 37, 80);
				GameImage2.drawWord(g, "You can spend:  "+levelToMoney(level-1), oliveGreen, 30, 690,780);
				GameImage2.drawWord(g, "score: "+GameImage2.score, oliveGreen, 33, 850, 80);
				GameImage2.drawWord(g, "Bank savings: "+GameImage2.money, oliveGreen, 30, 55, 782);
				GameImage2.drawWord(g, "To next Lv: "+GameFoodie.nextLevel, oliveGreen, 30, 380, 80);
				
				break;
			case 2:
				GameImage2.drawWord(g, "YOU GOT:"+GameImage2.score, oliveGreen, 70, 310, 440);
				GameFoodie.isComplete = true;
				break;
			case 3:
				GameImage2.drawWord(g, "Super Mode", oliveGreen, 40, 360, 80);
				GameImage2.drawWord(g, "score: "+GameImage2.score, oliveGreen, 33, 850, 80);
				GameImage2.drawWord(g, "Time: "+GameImage2.timeleft, oliveGreen , 33, 37, 80);
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
