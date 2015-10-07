package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;

public class Item implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int quantity;
	private float price;
	private float damageValue;
	
	
	public Item(String name2, int quantity, float price2){
		setName(name2);
		setQuantity(quantity);
		setPrice(price2);
		
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price2) {
		this.price = price2;
	}

	public float getDamageValue() {
		return damageValue;
	}

	public void setDamageValue(float damageValue) {
		this.damageValue = damageValue;
	}
	
	
	
	

}
