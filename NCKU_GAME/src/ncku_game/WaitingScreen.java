package ncku_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.function.Function;

import javax.imageio.ImageIO;
import javax.swing.*;

public class WaitingScreen extends JFrame{
	
	public WaitingScreen() {
		init();
	}
	
	private void init() {
		this.setSize(1000,800);
		this.setTitle("Waiting Room");
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
		JLabel title = new JLabel("成大GAME");
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
			
		//labels and buttons
		JButton button1 = new JButton("加入遊戲 Join Game");
		button1.setSize(200,200);
		container.add(button1);
		button1.setAlignmentX(CENTER_ALIGNMENT);
		button1.setAlignmentY(CENTER_ALIGNMENT);
		button1.addActionListener(new MyListener1(title));
		
		JButton button2 = new JButton("查看規則 Rules");
		container.add(button2);
		button2.setAlignmentX(CENTER_ALIGNMENT);
		button2.setAlignmentY(CENTER_ALIGNMENT);
		button2.addActionListener(new MyListener2());
		
		JButton button3 = new JButton("離開遊戲");
		container.add(button3);
		button3.setAlignmentX(CENTER_ALIGNMENT);
		button3.setAlignmentY(CENTER_ALIGNMENT);
		button3.addActionListener(new MyListener3());
		
		
		
		
	
	}
//////////////////////////////////////////////
	//Listener 1
	private class MyListener1 implements ActionListener{
		JLabel label;
		public MyListener1(JLabel label) {
			this.label = label;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			label.setText("等待更多玩家加入");	
		}		
	}
	//2
	private class MyListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Click");	
		}		
	}
	//3
	private class MyListener3 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}		
	}
}
