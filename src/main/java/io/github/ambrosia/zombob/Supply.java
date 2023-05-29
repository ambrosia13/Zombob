package io.github.ambrosia.zombob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public class Supply implements Displayable {
	private static final List<Supply> supplies = new ArrayList<>(Arrays.asList(
		new Supply("Healing potion", Type.HEAL),
		new Supply("Attack potion", Type.ATTACK),
		new Supply("Rejuvenation potion", Type.HEAL),
		new Supply("Strength potion", Type.ATTACK)
	));
	
	public static Supply getSupply() {
		return supplies.get(Game.rand.nextInt(supplies.size()));
	}
	
	private enum Type {
		HEAL, ATTACK
	}
	public final String name;
	final Type type;
	
	public Supply(String name, Type type) {
		this.name = name;
		this.type = type;
	}
	
	public void act(Player player) {
		System.out.println("Supply " + name + " was just used.");
		
		if(type == Type.ATTACK) {
			player.attack += 1;
		} else if(player.health <= player.maxHealth) {
			player.health += 4;
		}
	}
	
	@Override
	public char display() {
		return 'S';
	}
}
