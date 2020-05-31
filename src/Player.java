import java.awt.Graphics;

public class Player {

	boolean piecePlaced;
	
	public Player(boolean piecePlaced) {
		this.piecePlaced = false;
	}

	void instructions(Graphics g) {
		g.drawString("Player 1 please place a piece.", 30, 30);
		
	}
	
}
