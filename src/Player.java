import java.awt.Color;
import java.awt.Graphics;

public class Player {

	boolean piecePlaced;
	
	public Player(boolean piecePlaced) {
		this.piecePlaced = false;
	}

	void instructions(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Player 1 please click to place a piece.", 260, 30);
	}
	
}
