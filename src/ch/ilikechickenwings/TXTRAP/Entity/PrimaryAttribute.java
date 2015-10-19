package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;

public class PrimaryAttribute implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int points;
	
	
	public PrimaryAttribute(int p){
		setPoints(p);
		
	}


	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}


	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}
	
	public double getAttackDamage(){
		
		return (double)points;
		
	}
	
	
	
	
}
