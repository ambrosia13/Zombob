package io.github.ambrosia.zombob;

public class StrongZombie extends Zombie {
	
	// Construct a new strongzombie
	public StrongZombie() {
		type = 1;
		behavior = 10;
		
		health = 10;
		this.attack = 2;
		this.speed = -1;
	}
	
	// What the strong zombie says
	@Override
	public void speak() {
		System.out.println("Strong zombie says wheeeeee");
	}
}
