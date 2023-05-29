package io.github.ambrosia.zombob;

public class Zombie implements Displayable {
	public int type;
	public int behavior;
	
	public int health;
	public int attack;
	public int speed;
	
	// Cosntruct a new zombie
	public Zombie() {
		health = 20;
		attack = 1;
		speed = 0;
	}
	
	// What the zombie says when encountered
	public void speak() {
		System.out.println("Zombie says bluhughdfhhj");
	}
	
}
