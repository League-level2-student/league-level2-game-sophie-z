import java.awt.Color;
import java.awt.Graphics;

public class Player {

	int currentPlayer;
	
	public Player() {
		this.currentPlayer = 1;
	}

	void instructions(Graphics g) {
		if(currentPlayer == 1) {
			g.setColor(Color.WHITE);
			g.drawString("Player 1 please click to place a piece.", 260, 30);
		}
		else if(currentPlayer == 2) {
			g.setColor(Color.WHITE);
			g.drawString("Player 2 please click to place a piece.", 260, 30);
		}
	}
	
}
