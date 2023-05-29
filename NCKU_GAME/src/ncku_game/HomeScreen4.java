package ncku_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.*;

public class HomeScreen4 extends JFrame{
	
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
		ImageIcon background_img = new ImageIcon("assets/images/path4.png");
		Image img = background_img.getImage();
		Image temp_img = img.getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
		background_img = new ImageIcon(temp_img);
		JLabel background = new JLabel("", background_img, JLabel.CENTER);
		background.add(container);
		background.add(heading);
		background.setBounds(0,0,1000,800);
		add(background);
			
		
	}
	
}
