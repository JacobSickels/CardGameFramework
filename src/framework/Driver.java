package framework;

import games.CrazyEights;
import games.OldMaid;

public class Driver {

	public static void main(String[] args) {
		//Game game = new OldMaid(4);
		Game game = new CrazyEights(4, 5);
		game.start();

	}

}
