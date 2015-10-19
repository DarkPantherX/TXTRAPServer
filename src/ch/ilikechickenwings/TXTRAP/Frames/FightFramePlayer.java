package ch.ilikechickenwings.TXTRAP.Frames;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Item;

public class FightFramePlayer implements Processable, Runnable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ServerProtocol onTurn;
	private ServerProtocol notOnTurn;
	private WorldFrame worldFrame;
	private boolean defendersFirstTurn=true;
	private boolean fightRunning=true;
	private long turnTime=15000;
	private long timeOld=0;
	
	public FightFramePlayer(ServerProtocol sP, ServerProtocol pa, WorldFrame worldFrame1, boolean b){

		onTurn=pa;
		notOnTurn=sP;
		worldFrame=worldFrame1;
		sP.getPlayer().getCity().getEntities().remove(sP);
		pa.getPlayer().getCity().getEntities().remove(pa);
		
		sP.setProcessable(this);
		pa.setProcessable(this);
		
		notOnTurn.sendMessage(new NetInput("You started a fight with another player, awaint accept. You can still cancle the fight with 'decline'", Console.standardEvent,true,true));
		onTurn.sendMessage(new NetInput("You have been asked to fight! Type 'accept' to accept the fight or 'decline' to decline the fight",Console.standardEvent,true,true));
	}
	
	
	
	@Override
	public void run() {
		long timeNow=System.currentTimeMillis();
		
		while(fightRunning){
			
			if(timeOld+turnTime<timeNow){
				
				
					notOnTurn.sendMessage(new NetInput("Time is up, your turn!"));
					onTurn.sendMessage(new NetInput("Time is up, enemies turn!"));
				
					turnChange();
				
				timeOld=timeNow;
				
			}else{
				try {
					Thread.sleep(500L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		
	}

	private void turnChange() {
		ServerProtocol temp=onTurn;
		onTurn=notOnTurn;
		notOnTurn=temp;
		
		onTurn.sendMessage(new NetInput("Your turn!"));
		notOnTurn.sendMessage(new NetInput("Your turn ended!"));

	}



	@Override
	public void processInput(String[] s, ServerProtocol sP) {
		if(defendersFirstTurn){
			if(s[0].toLowerCase().equals("decline")||s[0].toLowerCase().equals("surrender")){
				notOnTurn.sendMessage(new NetInput("Player declined the fight"));
				onTurn.sendMessage(new NetInput("Player declined the fight"));
				notOnTurn.getPlayer().getCity().getEntities().remove(notOnTurn);
				onTurn.getPlayer().getCity().getEntities().remove(onTurn);
				onTurn.setProcessable(worldFrame);
				notOnTurn.setProcessable(worldFrame);
				
			}else if(s[0].toLowerCase().equals("accept")&&sP.equals(onTurn)){
				//new Thread(this).start();
				defendersFirstTurn=false;
				notOnTurn.sendMessage(new NetInput("The fight begins! Your enemy, you have 15 secs to do something, type help for help"));
				onTurn.sendMessage(new NetInput("The fight begins! Your turn, you have 15 secs to do something, type help for help"));
			}else{
				sP.sendMessage(new NetInput("Not a usable command!",Console.errorOutput));
	
			}
			
		}else{
			
			if(sP.equals(onTurn)){
				
				switch(s[0].toLowerCase()){
				case "surrender":
					surrender(sP,onTurn);
					break;
				case "attack":
					notOnTurn.getPlayer().getDamaged(sP.getPlayer().getDamage());
					onTurn.sendMessage(new NetInput("You attacked your enemy with "
							+ "\nEnemy life left: " + notOnTurn.getPlayer().getHealth() + " of "+ notOnTurn.getPlayer().getMaxHealth()+sP.getPlayer().getPrimaryAttribute().getPoints()+" damage",Console.standardEvent));
					
					notOnTurn.sendMessage(new NetInput("You took damaged by your enemy with "
							+ "\nLife life left: " + notOnTurn.getPlayer().getHealth() + " of "+ notOnTurn.getPlayer().getMaxHealth()+sP.getPlayer().getPrimaryAttribute().getPoints()+" damage",Console.errorOutput));

					if(!notOnTurn.getPlayer().isAlive()){
						fightRunning=false;
						onTurn.setProcessable(worldFrame);
						onTurn.getPlayer().getCity().getEntities().add(sP.getPlayer());
						onTurn.sendMessage(new NetInput("You won",Console.standardEvent));
						lostFight(notOnTurn,onTurn);
					}else{
						turnChange();
					}
					
					
					break;
				case "help":
					sP.sendMessage(new NetInput("Commands available:"
							+ "\nWhile your turn:"
							+ "\n ->help - get your commands"
							+ "\n ->attack - attacks the enemy with your standard attack"
							+ "\n ->surrender - give up and get away with your life"
							+ "\nWhile not your turn:"
							+ "\n ->help - get your commands"
							+ "\n ->surrender -give up and get away with your life"));
					break;
				case "skip":
					onTurn.sendMessage(new NetInput("You skipped your turn",Console.standardEvent));
					notOnTurn.sendMessage(new NetInput("Your enemy skipped his turn",Console.standardEvent));
					turnChange();
					
					
					break;
					default:
						sP.sendMessage(new NetInput("Command not found",Console.errorOutput));

						break;
				}
				
				
			}else{
				
				switch(s[0].toLowerCase()){
				case "surrender":
					surrender(sP,onTurn);
					
					break;
				case "help":
					sP.sendMessage(new NetInput("Commands available:"
							+ "\nWhile your turn:"
							+ "\n ->help - get your commands"
							+ "\n ->attack - attacks the enemy with your standard attack"
							+ "\n ->surrender - give up and get away with your life"
							+ "\nWhile not your turn:"
							+ "\n ->help - get your commands"
							+ "\n ->surrender -give up and get away with your life"));
					break;
					
				default:
					sP.sendMessage(new NetInput("It's not your turn",Console.errorOutput));
					break;
				}
				
				
			}
			
			
			
		}
		
	}

	private void lostFight(ServerProtocol looser, ServerProtocol winner) {
		looser.setProcessable(worldFrame);
		looser.getPlayer().killPlayer(winner.getPlayer(), looser);
		
	}



	@Override
	public String getStartInput() {
		return "NP";
	}


	
	
	
	private void surrender(ServerProtocol p1, ServerProtocol p2){
		
		onTurn.getPlayer().getCity().getEntities().add(onTurn.getPlayer());
		notOnTurn.getPlayer().getCity().getEntities().add(notOnTurn.getPlayer());
		p2.getPlayer().setHealth(onTurn.getPlayer().getMaxHealth());
		onTurn.setProcessable(worldFrame);
		notOnTurn.setProcessable(worldFrame);
		

		for(Item i : p1.getPlayer().getInventory()){
			if(i.getName().toLowerCase().equals("gold")){
				i.setQuantity(i.getQuantity()/2);
			}
		}
		boolean done=false;
		for(Item i : p2.getPlayer().getInventory()){
			if(i.getName().toLowerCase().equals("gold")){
				i.setQuantity(i.getQuantity()+100);
				done=true;
			}
		}
		
		if(!done){
			p2.getPlayer().getInventory().add(new Item("Gold",100,1));
		}
		
		
		p1.sendMessage(new NetInput("You lost half of your gold, but got away with your life!",Console.errorOutput));
		p2.sendMessage(new NetInput("Your enemy run away, but you got some gold!",Console.errorOutput));
		
	}
	


}
