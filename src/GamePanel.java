import java.awt.Color;
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
	JButton menutogame;
	JButton gametoend;
	JButton endtomenu;
	Timer timer;
	
	public GamePanel() {
		this.menutogame = new JButton("CONTINUE");
		menutogame.addActionListener(this);
		this.add(menutogame);
		this.gametoend = new JButton("END GAME");
		gametoend.addActionListener(this);
		this.endtomenu = new JButton("PLAY AGAIN");
		endtomenu.addActionListener(this);
		titleFont = new Font("Arial", Font.PLAIN, 50);
		this.timer = new Timer(1000/60, this);
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
		g.drawString("OTHELLO", 240, 200);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
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
		if (arg0.getSource() == menutogame) {
			currentState = GAME;
			this.remove(menutogame);
			this.add(gametoend);
		}
		else if (arg0.getSource() == gametoend) {
			currentState = END;
			this.remove(gametoend);
			this.add(endtomenu);
		}
		else if (arg0.getSource() == endtomenu) {
			currentState = MENU;
			this.remove(endtomenu);
			this.add(menutogame);
		}
		if(arg0.getSource() == timer) {
			repaint();
		}
	}
}
