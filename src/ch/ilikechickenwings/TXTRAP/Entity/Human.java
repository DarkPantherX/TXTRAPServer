package ch.ilikechickenwings.TXTRAP.Entity;

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
	
	/** Has the name of the human */
	private String name;
	
	public Human(float health, String name, City city, String responseLine1, Place place2){
		super(health);
		setName(name);
		setCity(city);
		setResponseLine(responseLine1);
		setPlace(place2);
		responseLine.concat("");
		
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
	
	
	
	
}
