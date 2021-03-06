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
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, MouseListener{

	final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;
	JButton button;
	JButton instructionsbutton;
	//Timer timer;
	GameBoard gb;
	String currentTurn;
	Player player;
	ArrayList <Point> pointsleft = new ArrayList <Point>();
	ArrayList <Point> pointsright = new ArrayList <Point>();
	ArrayList <Point> pointsup = new ArrayList <Point>();
	ArrayList <Point> pointsdown = new ArrayList <Point>();
	ArrayList <Point> pointsrightup = new ArrayList <Point>();
	ArrayList <Point> pointsrightdown = new ArrayList <Point>();
	ArrayList <Point> pointsleftup = new ArrayList <Point>();
	ArrayList <Point> pointsleftdown = new ArrayList <Point>();
	int whiteNum = 0;
	int blackNum = 0;
	boolean leftPlaysLeft = true;
	boolean rightPlaysLeft = true;
	boolean upPlaysLeft = true;
	boolean downPlaysLeft = true;
	boolean rightupPlaysLeft = true;
	boolean rightdownPlaysLeft = true;
	boolean leftupPlaysLeft = true;
	boolean leftdownPlaysLeft = true;
	
	public GamePanel() {
		button = new JButton("CONTINUE");
		button.addActionListener(this);
		instructionsbutton = new JButton ("INSTRUCTIONS");
		instructionsbutton.addActionListener(this);
		titleFont = new Font("Arial", Font.PLAIN, 50);
		//timer = new Timer(1000/60, this);
		this.setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel.add(button);
		panel.add(instructionsbutton);
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
		whiteNum = 0;
		blackNum = 0;
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
		g.drawString("Press PLAY AGAIN to start another round", 250, 280);
		if(whiteNum>blackNum) {
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString("White player won!", 300, 250);
		}
		else if(whiteNum<blackNum) {	
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString("Black player won!", 300, 250);
			}
		else if(whiteNum==blackNum) {
			g.setColor(Color.WHITE);
			g.setFont(getFont());
			g.drawString("White player and black player tied!", 260, 250);
			}
		resetBoard();
	}

	void startGame() {
		//timer.start();
	}
	
	void resetBoard() { 
		for(int x = 0, y = 0; x<gb.board.length && y<gb.board.length; x++, y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = gb.board.length-1, y = 0; x>=0 && y<gb.board.length; x--, y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 0, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 1, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 2, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 3, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 4, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 5, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 6, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		for(int x = 7, y = 0; y<gb.board.length; y++) {
			if(gb.board[x][y].empty.equals("white")) {
				whiteNum = whiteNum+1;
			}
			else if(gb.board[x][y].empty.equals("black")) {
				blackNum = blackNum+1;
			}
			gb.board[x][y].empty = "empty";
		}
		leftPlaysLeft = true;
		rightPlaysLeft = true;
		upPlaysLeft = true;
		downPlaysLeft = true;
		rightupPlaysLeft = true;
		rightdownPlaysLeft = true;
		leftupPlaysLeft = true;
		leftdownPlaysLeft = true;
		currentTurn = "black";
		gb.board[3][3].empty = "white";
		gb.board[4][3].empty = "black";
		gb.board[3][4].empty = "black";
		gb.board[4][4].empty = "white";
		System.out.println("whiteNum = " + whiteNum + " blackNum = " + blackNum);
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
		}
		
		else if (arg0.getSource() == button && currentState == END) {
			currentState = MENU;
			button.setText("CONTINUE");
		}
		
		if(arg0.getSource() == instructionsbutton) {
			JOptionPane.showMessageDialog(null, "Objective: get the majority of pieces on the board at the end of the game. The game goes back and forth between white and black until:\r\n" + 
					"\r\n" + 
					"- one player can not make a valid move to outflank the opponent.\r\n" + 
					"- both players have no valid moves.\r\n" + 
					"\r\n" + 
					"When a player has no valid moves, he/she pass his/her turn and the opponent continues.\r\n" + 
					"A player can not forfeit his/her turn unless there are no valid moves.\r\n" + 
					"When both players have no more valid moves, the game ends. \r\n" + 
					"\r\n" +
					"How the game works: A move is made by placing a disc of the player's color on the board in a position that \"out-flanks\" one or more of the opponent's discs." + 
					"\r\n" + 
					"A disc or row of discs is outflanked when it is surrounded at the ends by discs of the opposite color in any direction (left, right, up, down, and all four diagonals) \r\n"
					+ "\r\n" + "Check out this website for a more detailed explantion or look up how to play othello: \r\n"
					+ "https://www.mastersofgames.com/rules/reversi-othello-rules.htm");
		}
		
		//if(arg0.getSource() == timer) {
		//	repaint();
		//}
		repaint();
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

	ArrayList checkPlay(int i, int j) {
		pointsleft.clear();
		pointsright.clear();
		pointsup.clear();
		pointsdown.clear();
		pointsrightup.clear();
		pointsrightdown.clear();
		pointsleftup.clear();
		pointsleftdown.clear();
		
		//pointsleft --> placing a piece on the left checking pieces to the right
		for(int c = i+1; c<gb.board.length; c++) { //piece placed = i,j so basically checking i++ -->
			if(c-i>=2 && gb.board[i][j].empty.equals("empty")) { //checking that the tail piece is not right next to the piece put down; at least one piece in between
				if(gb.board[c][j].empty.equals(currentTurn)) { //checking if there is a tail to the sandwich
					for(int inside = c-1; inside>=i+1; inside--) { //INSIDE MUST STOP AT FIRST TAIL
						if(gb.board[inside][j].empty.equals("empty") || gb.board[inside][j].empty.equals(currentTurn)) { //checking if ALL the pieces in the sandwich are not the same color or empty and if they are it's not a valid play
							pointsleft.clear();
							leftPlaysLeft = false;
							System.out.println("leftPlaysLeft 2 " + leftPlaysLeft);
							break;
						}
						pointsleft.add(new Point(inside, j));
					}
					break;
				}
			}
		}
		
		//pointsleft
//		if(i<gb.board.length-2) {
//		for(int c = i+1; c<gb.board.length; c++) {
//			if(tail==true) { //---> make first loop checking whether there is a tail of the sandwich
//			if(!gb.board[c][j].empty.equals("empty") && !gb.board[c][j].empty.equals(currentTurn)) {
//				pointsleft.add(new Point(c, j));
//			//}
//			}
//			else if(gb.board[c][j].empty.equals("empty")) {
//				break;
//			}
//			else {
//				break;
//			}
//		}
//		}
		
		//pointsright --> placing a piece on the right checking pieces to the left
		for(int c = i-1; c>=0; c--) {
			if(i-c>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[c][j].empty.equals(currentTurn)) {
					for(int inside = c+1; inside<=i-1; inside++) {
						if(gb.board[inside][j].empty.equals("empty") || gb.board[inside][j].empty.equals(currentTurn)) { 
							pointsright.clear();
							rightPlaysLeft = false;
							System.out.println("rightPlaysLeft " + rightPlaysLeft);
							break;
						}
						pointsright.add(new Point(inside, j));
					}
					break;
				}
			}
		}
		
		//pointsright
//		if(i>1) {
//		for(int b = i-1; b>=0; b--) {
//			if(!gb.board[b][j].empty.equals("empty") && !gb.board[b][j].empty.equals(currentTurn)) {
//				pointsright.add(new Point(b, j));
//			}
//			else if(gb.board[b][j].empty.equals("empty")) {
//				break;
//			}
//			else {
//				break;
//			}
//		}
//		}

		//pointsdown --> placing a piece on the bottom checking pieces upwards
		for(int c = j-1; c>=0; c--) {
			if(j-c>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[i][c].empty.equals(currentTurn)) {
					for(int inside = c+1; inside<=j-1; inside++) {
						if(gb.board[i][inside].empty.equals("empty") || gb.board[i][inside].empty.equals(currentTurn)) { 
							pointsdown.clear();
							downPlaysLeft = false;
							System.out.println("downPlaysLeft " + downPlaysLeft);
							break;
						}
						pointsdown.add(new Point(i, inside));
					}
					break;
				}
			}
			}
		
		//pointsdown
//		if(j>=2) {
//		for(int f = j-1; f>=0; f--) {
//			if(!gb.board[i][f].empty.equals("empty") && !gb.board[i][f].empty.equals(currentTurn)) {
//				pointsdown.add(new Point(i, f));
//				System.out.println("pointsdown " + pointsdown.size());
//			}
//			else if(gb.board[i][f].empty.equals("empty")) {
//				break;
//			}
//			else {
//				break;
//			}
//		}
//		}
		
		//pointsup --> placing a piece on the top checking pieces downward
		for(int c = j+1; c<gb.board.length; c++) {
			if(c-j>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[i][c].empty.equals(currentTurn)) {
					for(int inside = c-1; inside>=j+1; inside--) {
						if(gb.board[i][inside].empty.equals("empty") || gb.board[i][inside].empty.equals(currentTurn)) { 
							pointsup.clear();
							upPlaysLeft = false;
							System.out.println("upPlaysLeft " + upPlaysLeft);
							break;
						}
						pointsup.add(new Point(i, inside));
					}
					break;
				}
			}
		}
			
		//pointsup
//			if(j<gb.board.length-2) {
//			for(int d = j+1; d<gb.board.length; d++) {
//				if(!gb.board[i][d].empty.equals("empty") && !gb.board[i][d].empty.equals(currentTurn)) {
//					pointsup.add(new Point(i, d));
//				}
//				else if(gb.board[i][d].empty.equals("empty")) {
//					break;
//				}
//				else {
//					break;
//				}
//			}
//			}
		
		//pointsrightup --> placing a piece diagonally to the top on the right and checking pieces diagonally to the bottom on the left
		for(int row=i-1, collumn=j+1; row>=0 && collumn<gb.board.length; row--, collumn++) {
			if(i-row>=2 && collumn-j>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[row][collumn].empty.equals(currentTurn)) {
					for(int insidex = row+1, insidey = collumn-1; insidex<=i-1 && insidey>=j+1; insidex++, insidey--) {
						if(gb.board[insidex][insidey].empty.equals("empty") || gb.board[insidex][insidey].empty.equals(currentTurn)) { 
							pointsrightup.clear();
							rightupPlaysLeft = false;
							System.out.println("rightupPlaysLeft " + rightupPlaysLeft);
							break;
						}
						pointsrightup.add(new Point(insidex, insidey));
					}
					break;
				}
			}
		}
		
		//pointsrightdown --> placing a piece diagonally to the bottom on the right and checking pieces diagonally to the top on the left
		for(int row=i-1, collumn=j-1; row>=0 && collumn>=0; row--, collumn--) {
			if(i-row>=2 && j-collumn>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[row][collumn].empty.equals(currentTurn)) {
					for(int insidex = row+1, insidey = collumn+1; insidex<=i-1 && insidey<=j-1; insidex++, insidey++) {
						if(gb.board[insidex][insidey].empty.equals("empty") || gb.board[insidex][insidey].empty.equals(currentTurn)) { 
							pointsrightdown.clear();
							rightdownPlaysLeft = false;
							System.out.println("rightdownPlaysLeft " + rightdownPlaysLeft);
							break;
						}
						pointsrightdown.add(new Point(insidex, insidey));
					}
					break;
				}
			}
		}
		
		//pointsleftup --> placing a piece diagonally to the top on the left and checking pieces diagonally to the bottom on the right
		for(int row=i+1, collumn=j+1; row<gb.board.length && collumn<gb.board.length; row++, collumn++) {
			if(row-i>=2 && collumn-j>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[row][collumn].empty.equals(currentTurn)) {
					for(int insidex = row-1, insidey = collumn-1; insidex>=i+1 && insidey>=j+1; insidex--, insidey--) {
						if(gb.board[insidex][insidey].empty.equals("empty") || gb.board[insidex][insidey].empty.equals(currentTurn)) { 
							pointsleftup.clear();
							leftupPlaysLeft = false;
							System.out.println("leftupPlaysLeft " + leftupPlaysLeft);
							break;
						}
						pointsleftup.add(new Point(insidex, insidey));
					}
					break;
				}
			}
		}
		
		//pointsleftdown --> placing a piece diagonally to the bottom on the left and checking pieces diagonally to the top on the right
		for(int row=i+1, collumn=j-1; row<gb.board.length && collumn>=0; row++, collumn--) {
			if(row-i>=2 && j-collumn>=2 && gb.board[i][j].empty.equals("empty")) {
				if(gb.board[row][collumn].empty.equals(currentTurn)) {
					for(int insidex = row-1, insidey = collumn+1; insidex>=i+1 && insidey<=j-1; insidex--, insidey++) {
						if(gb.board[insidex][insidey].empty.equals("empty") || gb.board[insidex][insidey].empty.equals(currentTurn)) { 
							pointsleftdown.clear();
							leftdownPlaysLeft = false;
							System.out.println("leftdownPlaysLeft " + leftdownPlaysLeft);
							break;
						}
						pointsleftdown.add(new Point(insidex, insidey));
					}
					break;
				}
			}
		}
		
//		//pointsrightup
//		boolean edge = false;
//		int collumn = i+1;
//		int row = j-1;
//		row--;
//		collumn++;	
//		while(!edge){
//		if(row<0 || collumn>=gb.board.length) {
//			edge = true;
//		}
//		else {
//			if(!gb.board[row][collumn].empty.equals("empty") && !gb.board[row][collumn].empty.equals(currentTurn)) {
//				pointsrightup.add(new Point(row, collumn));
//				System.out.println("row = " + row + " collumn = " + collumn + " pointsrightup = " + pointsrightup.size());
//			}
//			else if(gb.board[row][collumn].empty.equals("empty")) {
//				edge = true;
//			}
//			else {
//				edge = true;
//			}
//		}	
//		}
	
//		//pointsrightdown
//		edge = false;
//		collumn = i+1;
//		row = j+1;
//		row++;
//		collumn++;
//		while(!edge){
//		if(row<0 || collumn>=gb.board.length) {
//			edge = true;
//		}
//		else {
//			if(!gb.board[row][collumn].empty.equals("empty") && !gb.board[row][collumn].empty.equals(currentTurn)) {
//				pointsrightdown.add(new Point(row, collumn));
//			}
//			else if(gb.board[row][collumn].empty.equals("empty")) {
//				edge = true;
//			}
//			else {
//				edge = true;
//			}
//		}		
//		}
		
//		//pointsleftup
//		edge = false;
//		collumn = i-1;
//		row = j-1;
//		row--;
//		collumn--;	
//		while(!edge){
//		if(row<0 || collumn<0) {
//			edge = true;
//		}
//		else {
//			if(!gb.board[row][collumn].empty.equals("empty") && !gb.board[row][collumn].empty.equals(currentTurn)) {
//				pointsleftup.add(new Point(row, collumn));
//			}
//			else if(gb.board[row][collumn].empty.equals("empty")) {
//				edge = true;
//			}
//			else {
//				edge = true;
//			}
//		}	
//		}
		
//		//pointsleftdown
//		edge = false;
//		collumn = i-1;
//		row = j+1;
//		row++;
//		collumn--;	
//		while(!edge){
//		if(row<0 || collumn>=gb.board.length) {
//			edge = true;
//		}
//		else {
//			if(!gb.board[row][collumn].empty.equals("empty") && !gb.board[row][collumn].empty.equals(currentTurn)) {
//				pointsleftdown.add(new Point(row, collumn));
//			}
//			else if(gb.board[row][collumn].empty.equals("empty")) {
//				edge = true;
//			}
//			else {
//				edge = true;
//			}
//		}
//		}
		
		
		ArrayList <ArrayList<Point>> points = new ArrayList();
		points.add(pointsleft);
		points.add(pointsleftdown);
		points.add(pointsleftup);
		points.add(pointsright);
		points.add(pointsrightdown);
		points.add(pointsrightup);
		points.add(pointsup);
		points.add(pointsdown);
		return points; 
		}
	
	void placePiece(int i, int j) {		
		ArrayList<ArrayList<Point>> points = checkPlay(i, j);
		
		pointsleft = points.get(0);
		pointsleftdown = points.get(1);
		pointsleftup = points.get(2);
		pointsright = points.get(3);
		pointsrightdown = points.get(4);
		pointsrightup = points.get(5);
		pointsup = points.get(6);
		pointsdown = points.get(7);
		
		for(int x = 0; x<pointsleft.size(); x++) {
			int pointXCoord = (int) pointsleft.get(x).getX();
			int pointYCoord = (int) pointsleft.get(x).getY();
			gb.board[pointXCoord][pointYCoord].empty = currentTurn;
		}
		for(int x = 0; x<pointsright.size(); x++) {
			gb.board[(int) pointsright.get(x).getX()][(int) pointsright.get(x).getY()].empty = currentTurn;
		}
		for(int x = 0; x<pointsup.size(); x++) {
			gb.board[(int) pointsup.get(x).getX()][(int) pointsup.get(x).getY()].empty = currentTurn;
		}
		for(int x = 0; x<pointsdown.size(); x++) {
			gb.board[(int) pointsdown.get(x).getX()][(int) pointsdown.get(x).getY()].empty = currentTurn;
		}
		for(int x = 0; x<pointsrightup.size(); x++) {
			gb.board[(int) pointsrightup.get(x).getX()][(int) pointsrightup.get(x).getY()].empty = currentTurn;	
		}
		for(int x = 0; x<pointsrightdown.size(); x++) {
			gb.board[(int) pointsrightdown.get(x).getX()][(int) pointsrightdown.get(x).getY()].empty = currentTurn;
		}
		for(int x = 0; x<pointsleftup.size(); x++) {
			gb.board[(int) pointsleftup.get(x).getX()][(int) pointsleftup.get(x).getY()].empty = currentTurn;
		}
		for(int x = 0; x<pointsleftdown.size(); x++) {
			gb.board[(int) pointsleftdown.get(x).getX()][(int) pointsleftdown.get(x).getY()].empty = currentTurn;
		}
		
		boolean check = pointsright.size()>0 || pointsleft.size()>0 || pointsup.size()>0 || pointsdown.size()>0 || pointsrightup.size()>0 || pointsrightdown.size()>0 || pointsleftup.size()>0 || pointsleftdown.size()>0;
		if(currentTurn.equals("white") && gb.board[i][j].empty.equals("empty") && check) {
			gb.board[i][j].setStatus("white");
			currentTurn = "black";
		} 
		else if(currentTurn.equals("black") && gb.board[i][j].empty.equals("empty") && check) {
			gb.board[i][j].setStatus("black");
			currentTurn = "white";
		}
		if(!leftPlaysLeft&&!rightPlaysLeft&&!upPlaysLeft&&!downPlaysLeft&&!rightupPlaysLeft&&!rightdownPlaysLeft&&!leftupPlaysLeft&&!leftdownPlaysLeft) {
			JOptionPane.showMessageDialog(null, "No valid plays left for " + currentTurn + " player. Forfeiting turn.");
			if(currentTurn.equals("white")) {
				currentTurn = "black";
			}
			else if(currentTurn.equals("black")) {
				currentTurn = "white";
			}
			leftPlaysLeft=true;
			rightPlaysLeft=true;
			upPlaysLeft=true;
			downPlaysLeft=true;
			rightupPlaysLeft=true;
			leftupPlaysLeft=true;
			rightdownPlaysLeft=true;
			leftdownPlaysLeft=true;
		}
	}
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int i = (arg0.getX()-85)/70;
		int j = (arg0.getY()-85)/70;
		System.out.println("i:" + i);
		System.out.println("j:" + j);
		placePiece(i, j);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	static void instructions(String videoURL) {
		try {
			URI uri = new URI(videoURL);
			java.awt.Desktop.getDesktop().browse(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}