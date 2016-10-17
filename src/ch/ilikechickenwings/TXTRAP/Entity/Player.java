package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Places.Place;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;

public class Player extends Human implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gameClass;
	private City birthCity;
	private ArrayList<City> cities;
	
	public String password;
	public Processable processable;
	
	
	public Player(String name, String gameClass, City city, Place place,String pw) {
		super(100.0f, name, city,"",place);
		birthCity=city;
		setGameClass(gameClass);
		password=pw;
		setPrimaryAttribute(new PrimaryAttribute(20));
	}


	/**
	 * @return the gameClass
	 */
	public String getGameClass() {
		return gameClass;
	}

	/**
	 * @param gameClass the gameClass to set
	 */
	public void setGameClass(String gameClass) {
		this.gameClass = gameClass;
	}


	/**
	 * @return the birthCity
	 */
	public City getBirthCity() {
		return birthCity;
	}


	/**
	 * @param birthCity the birthCity to set
	 */
	public void setBirthCity(City birthCity) {
		this.birthCity = birthCity;
	}


	@Override
	public String getResponseLine() {
			
		return getResponseLine();
	}


	public ArrayList<City> getCities() {
		return cities;
	}


	public void addCities(City city) {
		cities.add(city);
	}


	public void killPlayer(Human human, ServerProtocol sP) {
		getInventory().clear();
		setCity(birthCity);
		setAlive(true);
		sP.setProcessable(sP.getWorldFrame());
		sP.sendMessage(new NetInput("You died and lost all your items! But your soul reincarnated in your birthcity!",Console.standardEvent,true,true));
		setHealth(getMaxHealth());
		
	}



}
