package ncku_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ScorePage extends JFrame{
	
	public ScorePage() {
		init();
	}
	
	private void init() {
		this.setSize(1000,800);
		this.setTitle("Scores");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exit the whole program
		
	   
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
		JLabel title = new JLabel("Scores");
		title.setFont(font);
		title.setBounds(300,100,300,100);
		heading.add(title);
		
		//background
		ImageIcon background_img = new ImageIcon("assets/images/ncku_tree.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		background.add(container);
		background.add(heading);
		background.setBounds(0,0,1000,800);
		add(background);

		//labels
		JLabel player1NameScore = new JLabel(PlayerSocket.name_scores[0]);
		container.add(player1NameScore);
		JLabel player2NameScore = new JLabel(PlayerSocket.name_scores[1]);
		container.add(player2NameScore);
		JLabel player3NameScore = new JLabel(PlayerSocket.name_scores[2]);
		container.add(player3NameScore);
	
	}

}