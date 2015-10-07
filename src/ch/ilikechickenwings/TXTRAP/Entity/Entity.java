package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Entity implements Damageable, Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The health an entity has*/
	private float health;
	/** The maximal health an entity can have*/
	private float maxHealth;
	/** ArrayList to save Items in*/
	private ArrayList<Item> inventory = new ArrayList<Item>();

	
	/**Creates a new Entity with health
	 * @param health2 Sets the health as double of the entity
	 * */
	public Entity(float health2){
		setHealth(health2);
		
	}
	
	
	
	
	@Override
	public void getDamaged(float damage) {
		health=health-damage;
		
	}
	
	
	/**
	 * @return the health
	 */
	public float getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(float health) {
		this.health = health;
	}

	/**
	 * @return the maxHealth
	 */
	public float getMaxHealth() {
		return maxHealth;
	}




	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(float maxHealth) {
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




}
