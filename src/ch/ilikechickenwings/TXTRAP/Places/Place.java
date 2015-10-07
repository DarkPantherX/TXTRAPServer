package ch.ilikechickenwings.TXTRAP.Places;

import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public abstract class Place implements Processable, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private WorldFrame worldFrame;
	
	private ArrayList<Human> humans= new ArrayList<Human>();

	
	public abstract void interact(ServerProtocol sP);
	
	public abstract void stopInteract(ServerProtocol sP);
	
	
	
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
	 * @return the worldFrame
	 */
	public WorldFrame getWorldFrame() {
		return worldFrame;
	}



	/**
	 * @param worldFrame the worldFrame to set
	 */
	public void setWorldFrame(WorldFrame worldFrame) {
		this.worldFrame = worldFrame;
	}

	/**
	 * @return the humans
	 */
	public ArrayList<Human> getHumans() {
		return humans;
	}

	/**
	 * @param humans the humans to set
	 */
	public void setHumans(ArrayList<Human> humans) {
		this.humans = humans;
	}

}
