package io.github.ambrosia.zombob;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
	public static final Random rand = new Random();
	public static final Scanner scan = new Scanner(System.in);
	
	public static final char[][] map = {
		{'-', 'S', '-', 'Z', 'Z', '-'},
		{'Z', '-', 'Z', '-', 'S', '-'},
		{'S', 'S', '-', 'S', '-', 'Z'},
		{'E', 'Z', 'S', 'Z', '-', '-'},
		{'Z', '-', 'Z', '-', 'S', 'S'},
		{'S', 'S', '-', '-', 'Z', '-'},
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
		return switch(map[point.x][point.y]) {
			case 'Z' -> Zombies.createZombie();
			case 'S' -> Supply.getSupply();
			default -> null;
		};
	}
	
	public void run() {
		for(;; turns++) {
			Point originalPos = (Point) playerPos.clone();
				
			System.out.println("Press w, a, s, d for movement");
			String input = scan.next();
			
			switch(input.toLowerCase()) {
				case "w" -> playerPos.y += 1;
				case "s" -> playerPos.y -= 1;
				case "d" -> playerPos.x += 1;
				case "a" -> playerPos.x -= 1;
			}
			
			try {
				var thing = lookup(playerPos);
				
				if(thing instanceof Zombie z) {
					player.tryAttackZombie(0, z);
				}
			} catch(ArrayIndexOutOfBoundsException e) {
				System.out.println("You went out of bounds");
				playerPos = originalPos;
				continue;
			}
		}
	}
}
