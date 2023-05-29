package io.github.ambrosia.zombob;

public class StrongZombie extends Zombie {
	public StrongZombie() {
		type = 1;
		behavior = 10;
		
		health = 10;
		this.attack = 2;
		this.speed = -1;
	}
	
	@Override
	public void speak() {
		System.out.println("Strong zombie says wheeeeee");
	}
}
