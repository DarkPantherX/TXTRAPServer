package ch.ilikechickenwings.TXTRAP.Frames;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;

public class FightFramePlayer implements Processable, Runnable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean playersTurn=false;
	private ServerProtocol attacker;
	private ServerProtocol defender;
	private WorldFrame worldFrame;
	private boolean defendersFirstTurn=true;
	
	public FightFramePlayer(ServerProtocol sP, ServerProtocol pa, WorldFrame worldFrame1, boolean b){
		setAttacker(sP);
		setDefender(pa);
		setWorldFrame(worldFrame1);
		sP.getPlayer().getCity().getEntities().remove(sP);
		pa.getPlayer().getCity().getEntities().remove(pa);
		
		attacker.sendMessage(new NetInput("You started a fight with another player, awaint accept. You can still cancle the fight with 'decline'", Console.standardEvent,true,true));
		defender.sendMessage(new NetInput("You have been asked to fight! Type 'accept' to accept the fight or 'decline' to decline the fight",Console.standardEvent,true,true));
	}
	
	
	
	@Override
	public void run() {
		
		
	}

	@Override
	public void processInput(String[] s, ServerProtocol sP) {
		if(defendersFirstTurn){
			if(s[0].toLowerCase().equals("decline")){
				attacker.sendMessage(new NetInput("Player declined the fight"));
				defender.sendMessage(new NetInput("Player declined the fight"));
				attacker.getPlayer().getCity().getEntities().remove(attacker);
				defender.getPlayer().getCity().getEntities().remove(defender);
				
			}else if(s[0].toLowerCase().equals("accept")){
				new Thread(this).start();
				defendersFirstTurn=false;
				attacker.sendMessage(new NetInput("The fight begins! Your enemy, you have 15 secs to do something, type help for help"));
				defender.sendMessage(new NetInput("The fight begins! Your turn, you have 15 secs to do something, type help for help"));
			}
			
		}else{
			
			
			
			
			
		}
		
	}

	@Override
	public String getStartInput() {
		// TODO Auto-generated method stub
		return null;
	}



	/**
	 * @return the playersTurn
	 */
	public boolean isPlayersTurn() {
		return playersTurn;
	}



	/**
	 * @param playersTurn the playersTurn to set
	 */
	public void setPlayersTurn(boolean playersTurn) {
		this.playersTurn = playersTurn;
	}



	/**
	 * @return the attacker
	 */
	public ServerProtocol getAttacker() {
		return attacker;
	}



	/**
	 * @param attacker the attacker to set
	 */
	public void setAttacker(ServerProtocol attacker) {
		this.attacker = attacker;
	}



	/**
	 * @return the defender
	 */
	public ServerProtocol getDefender() {
		return defender;
	}



	/**
	 * @param defender the defender to set
	 */
	public void setDefender(ServerProtocol defender) {
		this.defender = defender;
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

}
