package io.github.ambrosia.zombob;

public class Zombie implements Displayable {
	public int type;
	public int behavior;
	
	public int health;
	public int attack;
	public int speed;
	
	public Zombie() {
		health = 20;
		attack = 1;
		speed = 0;
	}
	
	public void speak() {
		System.out.println("Zombie says bluhughdfhhj");
	}
	
	@Override
	public char display() {
		return 'Z';
	}
}
