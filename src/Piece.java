import java.awt.Color;
import java.awt.Graphics;

public class Piece {

	int pieceX;
	int pieceY;
	int pieceSize;
	String pieceColor;
	
	public Piece(int pieceX, int pieceY, int pieceSize, String pieceColor) {
		this.pieceX = pieceX;
		this.pieceY = pieceY;
		this.pieceSize = pieceSize;
		this.pieceColor = pieceColor;
	}
	
	void draw (Graphics g) {
		if(pieceColor.equals("white")) {
			g.setColor(Color.WHITE);
			g.fillOval(pieceX, pieceY, pieceSize, pieceSize);
		}
		else if(pieceColor.equals("black")) {
			g.setColor(Color.BLACK);
			g.fillOval(pieceX, pieceY, pieceSize, pieceSize);
		}
		g.setColor(Color.BLACK);
		g.drawRect(pieceX, pieceY, pieceSize, pieceSize);
	}
}
