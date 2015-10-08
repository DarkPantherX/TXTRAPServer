package ch.ilikechickenwings.TXTRAP;

import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Entity.Entity;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public class City implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList with enitites in the city 
	 */
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Place> places = new ArrayList<Place>();

	private WorldFrame world;

	private String cityName;

	/**
	 *
	 * @param cityname
	 *            Constructs the city with a new name
	 */
	public City(String cityname) {
		setCityName(cityname);
	}

	/**
	 * @return the entities
	 */
	public ArrayList<Entity> getEntities() {
		return entities;
	}

	/**
	 * @param entities
	 *            the entities to set
	 */
	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}

	/**
	 * @return the places
	 */
	public ArrayList<Place> getPlaces() {
		return places;
	}

	/**
	 * @param places
	 *            the places to set
	 */
	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}

	/**
	 * @return the world
	 */
	public WorldFrame getWorldFrame() {
		return world;
	}

	/**
	 * @param world
	 *            the world to set
	 */
	public void setWorldFrame(WorldFrame world) {
		this.world = world;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName
	 *            the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
