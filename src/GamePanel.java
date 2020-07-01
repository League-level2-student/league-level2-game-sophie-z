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
		//How to draw a specific piece/square: gb.board[0][1].empty = "white";
		if(currentTurn.equals("white")) {
			g.setColor(Color.WHITE);
			g.drawString("Player 2 (white) please click to place a piece.", 245, 30);
		}
		else if(currentTurn.equals("black")) {
			g.setColor(Color.WHITE);
			g.drawString("Player 1 (black) please click to place a piece.", 245, 30);
		}
		gb.draw(g);
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
		}
	
		else if (arg0.getSource() == button && currentState == GAME) {
			currentState = END;
			button.setText("PLAY AGAIN");
			//NEED TO RESET BOARD HERE?
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

	void placePiece(int i, int j) {
		if(currentTurn.equals("white") && gb.board[i][j].empty.equals("empty") && checkPlay(i, j)) {
			gb.board[i][j].setStatus("white");
			currentTurn = "black";
		} 
		else if(currentTurn.equals("black") && gb.board[i][j].empty.equals("empty") && checkPlay(i, j)) {
			gb.board[i][j].setStatus("black");
			currentTurn = "white";
		}
	}
	
	boolean checkPlay(int i, int j) {
		ArrayList<Point> pointsleft = new ArrayList<Point>();
		for(int c = i+1; c<gb.board.length; c++) {
			System.out.println("pointsright");
			if(!gb.board[c][j].empty.equals("empty") && !gb.board[c][j].empty.equals(currentTurn)) {
				pointsleft.add(new Point(c, j));
			}
			else if(gb.board[c][j].empty.equals("empty")) {
				pointsleft.clear();
				break;
			}
			else {
				break;
			}
		}
		for(int x = 0; x<pointsleft.size(); x++) {
			gb.board[(int) pointsleft.get(x).getX()][(int) pointsleft.get(x).getY()].empty = currentTurn;
		}
		
		ArrayList<Point> pointsright = new ArrayList<Point>();
		for(int b = i-1; b>=0; b--) {
			if(!gb.board[b][j].empty.equals("empty") && !gb.board[b][j].empty.equals(currentTurn)) {
				pointsright.add(new Point(b, j));
			}
			else if(gb.board[b][j].empty.equals("empty")) {
				pointsright.clear();
				break;
			}
			else {
				break;
			}
		}
		for(int y = 0; y<pointsright.size(); y++) {
			gb.board[(int) pointsright.get(y).getX()][(int) pointsright.get(y).getY()].empty = currentTurn;
		}
		
		ArrayList<Point> pointsdown = new ArrayList<Point>();
		for(int d = j+1; d<gb.board.length; d++) {
			if(!gb.board[i][d].empty.equals("empty") && !gb.board[i][d].empty.equals(currentTurn)) {
				pointsdown.add(new Point(i, d));
			}
			else if(gb.board[i][d].empty.equals("empty")) {
				pointsdown.clear();
				break;
			}
			else {
				break;
			}
		}
		for(int z = 0; z<pointsdown.size(); z++) {
			gb.board[(int) pointsdown.get(z).getX()][(int) pointsdown.get(z).getY()].empty = currentTurn;
		}
		
		ArrayList<Point> pointsup = new ArrayList<Point>();
		for(int f = j-1; f<gb.board.length; f--) {
			if(!gb.board[i][f].empty.equals("empty") && !gb.board[i][f].empty.equals(currentTurn)) {
				pointsup.add(new Point(i, f));
			}
			else if(gb.board[i][f].empty.equals("empty")) {
				pointsup.clear();
				break;
			}
			else {
				break;
			}
		}
		for(int s = 0; s<pointsup.size(); s++) {
			gb.board[(int) pointsup.get(s).getX()][(int) pointsup.get(s).getY()].empty = currentTurn;
		}
		
		ArrayList<Point> pointsrightup = new ArrayList<Point>();
		boolean edge = false;
		int row = j-1;
		int collumn = i+1;
		while(!edge){
		if(row<0 || collumn>=gb.board.length) {
			edge = true;
		}
		else {
			if(!gb.board[row][collumn].empty.equals("empty") && !gb.board[row][collumn].empty.equals(currentTurn)) {
				pointsrightup.add(new Point(row, collumn));
			}
			else if(gb.board[row][collumn].empty.equals("empty")) {
				pointsrightup.clear();
				edge = true;
			}
			else {
				edge = true;
			}
		}
		row--;
		collumn++;		
		}
	
		return pointsright.size()>0 || pointsleft.size()>0 || pointsup.size()>0 || pointsdown.size()>0 || pointsrightup.size()>0;
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int i = (arg0.getX()-85)/70;
		int j = (arg0.getY()-85)/70;
		System.out.println("i:" + i);
		System.out.println("j:" + j);
		placePiece(i, j);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}