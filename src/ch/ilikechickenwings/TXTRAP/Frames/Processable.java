package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.Serializable;

import ch.ilikechickenwings.TXTRAP.ServerProtocol;

public interface Processable extends Serializable{
	
	public void processInput(String[] s, ServerProtocol sP);
	
	public String getStartInput();

}
