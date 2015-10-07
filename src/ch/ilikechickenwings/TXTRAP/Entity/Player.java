package ch.ilikechickenwings.TXTRAP.Entity;

import java.io.Serializable;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;

public class Player extends Human implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gameClass;
	public String password;
	public Processable processable;
	
	
	public Player(String name, String gameClass, City city, Place place,String pw) {
		super(100.0f, name, city,"",place);
		setGameClass(gameClass);
		password=pw;
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


	@Override
	public String getResponseLine() {
			
		return getResponseLine();
	}


}
