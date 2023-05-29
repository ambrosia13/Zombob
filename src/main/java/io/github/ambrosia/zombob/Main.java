package io.github.ambrosia.zombob;

public class Main {
	public static void main(String[] args) {
		try {
			// run the game
			new Game().run();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}