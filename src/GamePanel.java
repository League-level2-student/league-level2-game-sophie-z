import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener{

	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
	JButton button;
	Timer timer;
	GameBoard gb;
	
	public GamePanel() {
		this.button = new JButton("CONTINUE");
		button.addActionListener(this);
		titleFont = new Font("Arial", Font.PLAIN, 50);
		this.timer = new Timer(1000/60, this);
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(button);
		this.add(panel, BorderLayout.SOUTH);
		startGame();
		gb = new GameBoard();
	}
	
	@Override
	public void paintComponent(Graphics g){
		if(currentState == MENU){
		    drawMenuState(g);
		}
		else if(currentState == GAME){
		    drawGameState(g);
		}
		else if(currentState == END){
		    drawEndState(g);
		}
	}
	
	void drawMenuState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("OTHELLO", 235, 200);
		g.setFont(getFont());
		g.drawString("Press CONTINUE to start", 268, 250);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(new Color (0, 0, 102));
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
		gb.draw(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 220, 200);
	}

	void startGame() {
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getSource() == button && currentState == MENU) {
			currentState = GAME;
			button.setText("END GAME");
			System.out.println(currentState);
		}
	
		else if (arg0.getSource() == button && currentState == GAME) {
			currentState = END;
			button.setText("PLAY AGAIN");
		}
		
		else if (arg0.getSource() == button && currentState == END) {
			currentState = MENU;
			button.setText("CONTINUE");
		}
		
		if(arg0.getSource() == timer) {
			repaint();
		}
	}
}
