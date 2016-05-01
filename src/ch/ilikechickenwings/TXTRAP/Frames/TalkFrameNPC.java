package ch.ilikechickenwings.TXTRAP.Frames;


import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.Entity.Entity;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;

public class TalkFrameNPC implements Processable, Runnable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	private ServerProtocol serverProtocol;
	private Human human;
	private WorldFrame worldFrame;
	private boolean notEnded=true;

	public TalkFrameNPC(ServerProtocol sP, Human h, WorldFrame wF){
	
		setServerProtocol(sP);
		setHuman(h);
		setWorldFrame(wF);
		
		sP.getPlayer().getCity().getEntities().remove(sP);
		sP.getPlayer().getCity().getEntities().remove(h);
		
		sP.setProcessable(this);
		
		sP.sendMessage(new NetInput("Talking",Console.startOutput,true,true));
		
		new Thread(this).start();
		
		
	}
	
	
	@Override
	public void processInput(String[] s, ServerProtocol sP) {
		switch(s[0].toLowerCase()){

		case "help":
			sP.sendMessage(new NetInput(
					"Available commands: "
                            + "\n attack -> You attack your opponent"
							+ "\n answer <option number> -> answer your opponent"
							+ "\n leave -> Stops the interaction",
					Console.standardOutput));
			break;

		case "attack":
            sP.sendMessage(new NetInput("You attacked: " + human.getName()));
            new FightFrameNPC(serverProtocol, human,worldFrame,false);
            break;

        case "answer":
            if(s.length == 2){
                int option;
                try{
                    option = Integer.parseInt(s[1],10);
                    sP.sendMessage(new NetInput(sP.getPlayer().getName() + ": " + human.getOption(option),Console.standardEvent));
                    sP.sendMessage(new NetInput(human.getName() + ": " + human.getAnswer(option),Console.standardEvent));


                }catch (NumberFormatException exc){
                    sP.sendMessage(new NetInput(s[1] +" is not a valid option!",Console.errorOutput));
                }


            }else{
                sP.sendMessage(new NetInput("Command was used wrong: answer <option number>",Console.errorOutput));
            }
            break;

		case "leave":
			sP.getPlayer().getCity().getEntities().add(sP.getPlayer());
			sP.getPlayer().getCity().getEntities().add(human);
			sP.setProcessable(worldFrame);
			sP.sendMessage(new NetInput("You stopped talking to " + human.getName(),Console.errorOutput));
			break;
		default:
			sP.sendMessage(new NetInput("Command not found, type 'help' for information"));
			break;
			}

	}

	@Override
	public String getStartInput() {
		
		return "NP";
	}

	
	@Override
	public void run() {
        serverProtocol.sendMessage(new NetInput(human.getName() + ": " + human.getGreeting(),Console.standardEvent));

        serverProtocol.sendMessage(new NetInput("\nOptions for answer:",Console.standardEvent));
        int i = 1;
        for(String answer : human.getOptionList()){
            serverProtocol.sendMessage(new NetInput(i + ") " + answer,Console.standardEvent));
            i++;
        }

		
	}
	

	/**
	 * @return the serverProtocol
	 */
	public ServerProtocol getServerProtocol() {
		return serverProtocol;
	}


	/**
	 * @param serverProtocol the serverProtocol to set
	 */
	public void setServerProtocol(ServerProtocol serverProtocol) {
		this.serverProtocol = serverProtocol;
	}


	/**
	 * @return the human
	 */
	public Human getHuman() {
		return human;
	}


	/**
	 * @param human the human to set
	 */
	public void setHuman(Human human) {
		this.human = human;
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
	 * @return the notEnded
	 */
	public boolean isNotEnded() {
		return notEnded;
	}


	/**
	 * @param notEnded the notEnded to set
	 */
	public void setNotEnded(boolean notEnded) {
		this.notEnded = notEnded;
	}



}
