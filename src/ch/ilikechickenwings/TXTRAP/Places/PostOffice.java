package ch.ilikechickenwings.TXTRAP.Places;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;

public class PostOffice extends Place{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	public PostOffice(){
		setName("PostOffice");
		
	}
	
	
	@Override
	public synchronized void processInput(String[] s, ServerProtocol sP) {
		switch(s[0].toLowerCase()){
		
		case "help":
			sP.sendMessage(new NetInput("Available commands:"
					+"\n letter -> shows you the new letter you got, 'type' letter again to show the next new letter"));
			break;
		case "letter":
			boolean done = false;
			for(StringBuilder let : sP.getWorldFrame().getLetters()){
				String s0[]=let.toString().split("-!-");
				if(s0[0].toLowerCase().equals(sP.getPlayer().getName().toLowerCase())){
					sP.sendMessage(new NetInput("\n"+s0[1]+"\n",Console.standartOutput));
					sP.getWorldFrame().getLetters().remove(let);
					done=true;
					break;
				}
				
				if(!done){
					sP.sendMessage(new NetInput("No new letters",Console.errorOutput));
				}
				
			}
			
			
			break;
			case "leave":
				stopInteract(sP);
				sP.getPlayer().setPlace(null);
				sP.sendMessage(new NetInput("You left the post office",Console.standartEvent,true,true));
				break;
		default:			
			sP.sendMessage(new NetInput("Command not found, try 'help'",Console.errorOutput));
			break;
		
		
		
		
		}
		
	}

	@Override
	public String getStartInput() {
		// TODO Auto-generated method stub
		return "You are the the post offce, press help for more information";
	}

	
	@Override
	public void interact(ServerProtocol sP) {
		sP.setProcessable(this);
		sP.sendMessage(new NetInput("Welcome to the post office, write 'help' for more information",Console.standartOutput,true,true));
		
	}

	@Override
	public void stopInteract(ServerProtocol sP) {
		sP.setProcessable(sP.getWorldFrame());
		
	}

}
