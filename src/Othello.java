import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Othello {

	static JFrame frame;
	GamePanel gamepanel;
	public static final int WIDTH = 715;
	public static final int HEIGHT = 691;
	
	public Othello() {
		this.frame = new JFrame();
		this.gamepanel = new GamePanel();
	}
	
	void setup() {
		frame.setVisible(true);
		frame.add(gamepanel);
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		Othello othello = new Othello();
		othello.setup();
	}
}
