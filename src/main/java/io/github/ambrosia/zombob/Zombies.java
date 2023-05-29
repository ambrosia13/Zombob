package io.github.ambrosia.zombob;

public class Zombies {
	public static Zombie createZombie() {
		int a = Game.rand.nextInt(10);
		
		return switch(a) {
			case 0, 1, 2, 3, 4, 5 -> new Zombie();
			case 6, 7, 8 -> new FastZombie();
			case 9 -> new StrongZombie();
			default -> throw new IllegalStateException();
		};
	}
}
