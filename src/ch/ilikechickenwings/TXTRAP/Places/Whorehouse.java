package ch.ilikechickenwings.TXTRAP.Places;



import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Whore;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class Whorehouse extends Place{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Whorehouse(WorldFrame wF) {
		setWorldFrame(wF);
		setName("Whorehouse");
	}

	@Override
	public synchronized void processInput(String[] s, ServerProtocol sP) {
		switch (s[0].toLowerCase()){
		case "help":
			sP.sendMessage(new NetInput("Available commands:"
					+"\n fuck <name of whore> -> shows the items to sell"
					+"\n showgirls -> shows the name of the girls"));
			break;
		case "fuck":
			for(Human h: getHumans()){
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())&&h instanceof Whore){
					Whore w = (Whore)h;
					sP.sendMessage(new NetInput("You fucked " +h.getName(),Console.standartEvent));
					w.setTimesHadSex(w.getTimesHadSex()+1);
					
				}else{
					
					sP.sendMessage(new NetInput("Whore not found", Console.errorOutput));
				}
				
			}
			
			break;
		case "showgirls":
			sP.sendMessage(new NetInput("There are following whores available: ",Console.standartOutput));
			for(Human h : getHumans()){
				if(h instanceof Whore){
					sP.sendMessage(new NetInput(h.getName()+" ",Console.standartListOutput,false,true));
					
				}
				
			}
			
			break;
		case "talk":
			for(Human h : getHumans()){
				if(s[1].toLowerCase().equals(h.getName().toLowerCase())){
					sP.sendMessage(new NetInput(h.getResponseLine()));
			break;
			}
				}
				
				break;
		case "leave":
			stopInteract(sP);
			sP.getPlayer().setPlace(null);
			sP.sendMessage(new NetInput("You left the whorehouse",Console.standartEvent,true,true));
			break;
		default:
			sP.sendMessage(new NetInput("Command not found, try help",Console.errorOutput));
			break;
			
		}
		
	}

	@Override
	public String getStartInput() {
		return "You are at the whorehouse, type 'help' for more information";
	}
	
	
	@Override
	public void interact(ServerProtocol sP) {
		sP.setProcessable(this);
		sP.sendMessage(new NetInput("Welcome to the whorehouse, write 'help' for more information",Console.standartOutput,true,true));
		
	}

	@Override
	public void stopInteract(ServerProtocol sP) {
		sP.setProcessable(sP.getWorldFrame());
		
	}

}
