package ncku_game;

import java.awt.*;
import javax.swing.*;

public class HomeScreen4 extends JFrame{
	static Image barImg = Toolkit.getDefaultToolkit().getImage("windowImgs/bar.png");
	int width = 500;
	int height = 300;
	public static String[] names = {"p1","p2","p3"};
	public static int[] progress = {1,2,4};

	public HomeScreen4() {
		init();
	}
	
	private void init() {
		this.setLocationRelativeTo(null);
		this.setSize(1000,800);
		this.setTitle("Happy Graduation!");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exit the whole program
		this.setResizable(false);
		
	   
		//Container
		JPanel container = new JPanel();
		container.setBounds(300, 300, 400, 350);
		container.setBackground(new Color(0,0,0,0));
		container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
		//this.setContentPane(container);
		
		//heading
		JPanel heading;
		heading = new JPanel();
		heading.setBackground(new Color(10,10,10,70));
		heading.setBounds(0,0,1000,250);
		
		Font font = new Font("Serif", Font.BOLD,100);
		JLabel title = new JLabel("Happy Graduation!");
		title.setFont(font);
		title.setBounds(300,100,300,100);
		heading.add(title);
		
		//background
		ImageIcon background_img = new ImageIcon("windowImgs/path4.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		background.add(container);
		background.add(heading);
		background.setBounds(0,0,1000,800);
		add(background);
			
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		drawWord(g, names[0], Color.BLACK, 20, 320);
		drawBar(g, Color.GREEN, progress[0], 300, this);
		
		drawWord(g, names[1], Color.BLACK, 20, 370);
		drawBar(g, Color.GREEN, progress[1], 350, this);

		drawWord(g, names[2], Color.BLACK, 20, 420);
		drawBar(g, Color.GREEN, progress[2], 400, this);
	}
	
	public static void drawWord(Graphics gImage, String str, Color color, int size, int y) {
		gImage.setColor(color);
		gImage.setFont(new Font("Arial", Font.BOLD, size));
		gImage.drawString(str, 550, y);
	}
	
	public static void drawBar(Graphics gImage, Color color, int progress, int y, Frame frame) {
		gImage.setColor(color);
		gImage.fillRect(250, y, 70*progress, 25);
		gImage.drawImage(barImg, 250, y, 280, 30, frame);
	}
}
