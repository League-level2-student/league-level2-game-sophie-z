import java.awt.Color;
import java.awt.Graphics;

public class GameBoard {

	Piece[][] board = new Piece[8][8];
	
	public GameBoard() {
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				board[i][j] = new Piece();
			}
		}
	}
	
	void draw(Graphics g) {
		g.setColor(new Color (7, 100, 27));
		g.fillRect(75, 55, 560, 560);
	}
}
