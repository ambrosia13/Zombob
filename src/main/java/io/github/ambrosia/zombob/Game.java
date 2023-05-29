package io.github.ambrosia.zombob;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public static final Random rand = new Random();
	public static final Scanner scan = new Scanner(System.in);
	
	// The game map
	public static final char[][] map = {
		{'-', 'S', 'E', 'Z', 'Z', '-'},
		{'Z', '-', 'Z', '-', 'S', '-'},
		{'S', 'S', 'E', 'S', '-', 'Z'},
		{'E', 'Z', 'S', 'Z', 'E', '-'},
		{'Z', '-', 'Z', '-', 'S', 'S'},
		{'S', 'S', '-', 'E', 'Z', '-'},
	};
	
	// Random battle messages to use for the recursive attack
	public static final String[] battleMessages = {
		"BAM", "WOMP", "POW", "GOOP", "BOP", "KACHOW"
	};
	
	public int turns;
	public Point playerPos;
	public Player player;
	
	public Game() {
		turns = 0;
		playerPos = new Point();
		player = new Player("Linda");
	}
	
	private Displayable lookup(Point point) {
		// Check what the player landed on based on their position
		return switch(map[point.x][point.y]) {
			case 'Z' -> Zombies.createZombie();
			case 'S' -> Supply.getSupply();
			case 'E' -> {
				System.out.println("YOU WON!!! WOOO");
				// Print how many turns they played for
				System.out.println("You played for this many turns: " + turns);
				System.exit(0); // exit the game
				yield null;
			}
			default -> null;
		};
	}
	
	public void run() {
		System.out.println("Hi you are trapped in a zombie city");
		System.out.println("try to survive and collect items");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		
		for(;; turns++) {
			// Clone the player position in case  they go out of bounds
			Point originalPos = (Point) playerPos.clone();
			
			System.out.println("---------------------");
			System.out.println("Your current stats:");
			System.out.println("\tHealth: " + player.health);
			System.out.println("\tAttack: " + player.attack);
			System.out.println("---------------------");
			System.out.println("Press w, a, s, d for movement");
			String input = scan.next();
			
			// Process input
			switch(input.toLowerCase()) {
				case "w" -> playerPos.y += 1;
				case "s" -> playerPos.y -= 1;
				case "d" -> playerPos.x += 1;
				case "a" -> playerPos.x -= 1;
			}
			
			try {
				var thing = lookup(playerPos);
				
				if(thing instanceof Zombie z) {
					// Extract the name of the zombie through the class name
					String className = z.getClass().getName();
					System.out.println("You found a " + className.substring(className.lastIndexOf('.') + 1));
					z.speak();
					
					// Use recursive function to attack the zombie
					player.tryAttackZombie(0, z);
					
					// if false, zombie died
					boolean playerDied = resolve(z);
					
					if(playerDied) {
						System.out.println("The zombie kill u :(");
						System.exit(0);
					} else {
						System.out.println("U kill zombie :)");
					}
				} else if(thing instanceof Supply) {
					player.collectSupply();
					System.out.println("U got supply. Use supply? yes or no");
					
					input = scan.next();
					
					// If they want to, use the supply
					if(input.equalsIgnoreCase("yes")) {
						System.out.println("Using supply.");
						player.useSupply();
					}
				} else if(thing == null) {
					// There was nothing on the current tile
					System.out.println("There is nothing here.");
				}
				
			} catch(ArrayIndexOutOfBoundsException e) {
				// Player went out of bounds, restart the turn
				System.out.println("You went out of bounds");
				playerPos = originalPos;
				continue;
			}
			
			// Player heals over time
			player.health += 4;
			if(player.health < player.maxHealth) player.health = player.maxHealth;
		}
	}
	
	public boolean resolve(Zombie z) {
		boolean playerDied = false;
		
		// Cursed.
		while(player.health > 0 && z.health > 0) {
			if(player.speed > z.speed) {
				player.health -= z.attack;
				if(player.health <= 0) {
					playerDied = true;
					break;
				}
				
				z.health -= player.attack;
				if(z.health <= 0) {
					break;
				}
			} else {
				z.health -= player.attack;
				if(z.health <= 0) {
					break;
				}
				
				player.health -= z.attack;
				if(player.health <= 0) {
					playerDied = true;
					break;
				}
			}
			
			// Print random battle message
			System.out.println("\t" + battleMessages[rand.nextInt(battleMessages.length)] + "!!");
		}
		
		return playerDied;
	}
}
