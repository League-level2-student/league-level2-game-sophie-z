import java.awt.Color;
import java.awt.Graphics;

public class GameBoard {

	Square[][] board = new Square[8][8];
	int pieceSize = 70;
	int squareSize = 70;
	
	public GameBoard() {
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				board[i][j] = new Square("empty", i*squareSize + 75, j*squareSize + 55, squareSize);
			}
		}
		board[3][3].empty = "white";
		board[4][3].empty = "black";
		board[3][4].empty = "black";
		board[4][4].empty = "white";
	}
	
	void draw(Graphics g) {
		g.setColor(new Color (7, 100, 27));
		g.fillRect(75, 55, 560, 560);		

	for(int i = 0; i<board.length; i++) {
		for(int j = 0; j<board[i].length; j++) {
			board[i][j].draw(g);
		}
	}
}
}