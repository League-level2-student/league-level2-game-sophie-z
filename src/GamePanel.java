import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener{

	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
	JButton button;
	Timer timer;
	GameBoard gb;
	String currentTurn;
	Player player;
	
	public GamePanel() {
		button = new JButton("CONTINUE");
		button.addActionListener(this);
		titleFont = new Font("Arial", Font.PLAIN, 50);
		timer = new Timer(1000/60, this);
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(button);
		Othello.frame.addMouseListener(this);
		this.add(panel, BorderLayout.SOUTH);
		startGame();
		this.currentTurn = "black";
		this.player = new Player();
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
		g.drawString("Press CONTINUE to start", 272, 250);
	}
	
	void drawGameState(Graphics g) {
		g.setColor(new Color (0, 0, 102));
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
		gb.draw(g);
		//How to draw a specific piece: gb.board[0][1].pieceColor = "white";
		player.instructions(g);
	}
	
	void drawEndState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0,0, Othello.WIDTH, Othello.HEIGHT);
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("GAME OVER", 220, 200);
		g.setFont(getFont());
		g.drawString("Press PLAY AGAIN to start another round", 250, 250);
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int i = (arg0.getX()-85)/70;
		int j = (arg0.getY()-85)/70;
		System.out.println("i:" + i);
		System.out.println("j:" + j);
		System.out.println(gb.board[i][j].pieceColor);
		checkPlay(i, j);
	}
	
	void checkPlay(int i, int j) {
		ArrayList<Point> points = new ArrayList<Point>();
		String blackwhite = "";
		if(player.currentPlayer==1) {
			blackwhite = "white";
		}
		else if(player.currentPlayer==2) {
			blackwhite = "black";
		}
		while(j>0) {
				j--;
				if(!gb.board[i][j].pieceColor.equals(blackwhite) && !gb.board[i][j].pieceColor.equals("")) {
					points.add(new Point(i, j));
			}
				else {
					break;
			}
		}
		for(int x = 0; x<points.size(); x++) {
			gb.board[(int) points.get(i).getX()][(int) points.get(i).getY()].pieceColor=blackwhite;
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}