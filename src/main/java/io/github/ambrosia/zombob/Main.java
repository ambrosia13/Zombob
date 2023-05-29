package io.github.ambrosia.zombob;

public class Main {
	public static void main(String[] args) {
		try {
			new Game().run();
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}