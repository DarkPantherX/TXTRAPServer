package ch.ilikechickenwings.TXTRAP.Entity;

import java.util.Random;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public abstract class Human extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Saves the city the human lives in */
	private City city;
	private Place place;
	private String responseLine;
	private PrimaryAttribute primaryAttribute;
	
	private Item rightHand;
	private Item leftHand;
	
	/** Has the name of the human */
	private String name;
	
	public Human(float health, String name, City city, String responseLine1, Place place2){
		super(health);
		setName(name);
		setCity(city);
		setResponseLine(responseLine1);
		setPlace(place2);
		responseLine.concat("");
		setPrimaryAttribute(new PrimaryAttribute(10));
		
	}
	
	public double getDamage() {
		double val=getPrimaryAttribute().getAttackDamage();
		
		if(getRightHand()!=null){
			val=val+getRightHand().getDamageValue();
		}
		Random r= new Random();
		if(r.nextInt(10)<3){
			if(r.nextInt(10)==0){
				val=val*2;
			}
			val=val*1.5;
		}else{
			int temp=r.nextInt((int)val);
			if(temp<(int)(val/9)){
				if(r.nextInt(2)==0){
					val=val+temp;
				}else{
					val=val-temp;
					
				}
			}
			
		}
		
		
		return (double)((int)val);
	}
	
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}




	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}




	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
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
	 * @return the responseLine
	 */
	public abstract String getResponseLine();




	/**
	 * @param responseLine the responseLine to set
	 */
	public void setResponseLine(String responseLine) {
		this.responseLine = responseLine;
	}




	/**
	 * @return the primaryAttribute
	 */
	public PrimaryAttribute getPrimaryAttribute() {
		return primaryAttribute;
	}




	/**
	 * @param primaryAttribute the primaryAttribute to set
	 */
	public void setPrimaryAttribute(PrimaryAttribute primaryAttribute) {
		this.primaryAttribute = primaryAttribute;
	}




	/**
	 * @return the rightHand
	 */
	public Item getRightHand() {
		return rightHand;
	}




	/**
	 * @param rightHand the rightHand to set
	 */
	public void setRightHand(Item rightHand) {
		this.rightHand = rightHand;
	}




	/**
	 * @return the leftHand
	 */
	public Item getLeftHand() {
		return leftHand;
	}




	/**
	 * @param leftHand the leftHand to set
	 */
	public void setLeftHand(Item leftHand) {
		this.leftHand = leftHand;
	}
	
	
	
	
}
