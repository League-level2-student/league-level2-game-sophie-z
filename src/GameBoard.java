import java.awt.Color;
import java.awt.Graphics;

public class GameBoard extends GamePanel{

	Piece[][] board = new Piece[8][8];
	int pieceSize = 70;
	
	public GameBoard() {
		for(int i = 0; i<board.length; i++) {
			for(int j = 0; j<board[i].length; j++) {
				board[i][j] = new Piece(i*pieceSize + 75, j*pieceSize + 55, pieceSize, "");
			}
		}
		board[3][3].pieceColor = "white";
		board[4][3].pieceColor = "black";
		board[3][4].pieceColor = "black";
		board[4][4].pieceColor = "white";
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