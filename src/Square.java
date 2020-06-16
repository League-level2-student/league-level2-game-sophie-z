import java.awt.Color;
import java.awt.Graphics;

public class Square {

	String empty = "";
	int x;
	int y;
	int squareSize;
	
	public Square(String empty, int x, int y, int squareSize) {
		this.empty = empty;
		this.x = x;
		this.y = y;
		this.squareSize = squareSize;
	}
	
	public String changeStatus() {
		return empty;
	}
	
	public void setStatus(String empty) {
		this.empty = empty;
	}
	
	void draw (Graphics g) {
		if(empty.equals("white")) {
			g.setColor(Color.WHITE);
			g.fillOval(x, y, squareSize, squareSize);
		}
		else if(empty.equals("black")) {
			g.setColor(Color.BLACK);
			g.fillOval(x, y, squareSize, squareSize);
		}
		g.setColor(Color.BLACK);
		g.drawRect(x, y, squareSize, squareSize);
	}
}