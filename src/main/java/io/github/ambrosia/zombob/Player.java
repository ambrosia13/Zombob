package io.github.ambrosia.zombob;

import java.util.ArrayList;

public class Player implements Displayable {
	public final String name;
	
	public int maxHealth;
	public int health;
	
	public int attack;
	public double defense;
	public int speed;
	
	public ArrayList<Supply> inventory;
	
	public Player(String name) {
		this.name = name;
		
		maxHealth = 20;
		health = 20;
		
		attack = 1;
		defense = 0.1;
		speed = 1;
		
		inventory = new ArrayList<>();
	}
	
	public void attack() {
		
	}
	
	public void collectSupply() {
		var supply = Supply.getSupply();
		System.out.println("U got a " + supply.name);
		
		inventory.add(supply);
	}
	
	public void useSupply() {
		if(inventory.size() == 0) {
			System.out.println("You don't have any supplies right now");
			return;
		}
		inventory.remove(Game.rand.nextInt(inventory.size())).act(this);
	}
	
	// Recursive function to attack a zombie
	public boolean tryAttackZombie(int times, Zombie zombie) {
		if(times == 1) {
			zombie.health -= this.attack;
			this.health -= zombie.attack;;
			return false;
		} else {
			return tryAttackZombie(++times, zombie);
		}
	}
	
	@Override
	public char display() {
		return 'P';
	}
}
