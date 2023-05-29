package io.github.ambrosia.zombob;

public class FastZombie extends Zombie {
	public FastZombie() {
		health = 10;
		attack = 1;
		speed = 2;
	}
	
	@Override
	public void speak() {
		System.out.println("Fast zombie says tuhhhh");
	}
}
