package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Damageable, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The health an entity has*/
	private double health;
	/** The maximal health an entity can have*/
	private double maxHealth;
	/** ArrayList to save Items in*/
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private boolean alive=true;

	
	/**Creates a new Entity with health
	 * @param health2 Sets the health as double of the entity
	 * */
	public Entity(float health2){
		setHealth(health2);
		setMaxHealth(health2);
		
	}
	
	
	
	
	@Override
	public void getDamaged(double damage) {
		
		health=health-damage;
		if(health<=0){
			System.out.println(health);
			health=0;
			alive=false;
		}
		
	}
	
	
	/**
	 * @return the health
	 */
	public double getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(double health) {
		this.health = health;
	}

	/**
	 * @return the maxHealth
	 */
	public double getMaxHealth() {
		return maxHealth;
	}




	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}




	/**
	 * @return the inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}




	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}




	/**
	 * @param alive the alive to set
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}




}
