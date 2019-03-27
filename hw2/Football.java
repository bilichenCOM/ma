package hw2;

import java.util.ArrayList;

public class Football {

	public static void play() {

		class Player {
			public void hitTheBall() {
			}
		}

		ArrayList<Player> players = new ArrayList<>();
		for (int i = 0; i < 22; i++) {
			players.add(new Player());
			players.get(i).hitTheBall();
		}
		System.out.println("Game over!");
	}
<<<<<<< HEAD
=======

>>>>>>> c77268321dad0543219877de7b60fd293a93fa30
}
