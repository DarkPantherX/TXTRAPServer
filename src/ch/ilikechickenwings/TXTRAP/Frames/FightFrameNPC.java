package ch.ilikechickenwings.TXTRAP.Frames;


import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Item;

public class FightFrameNPC implements Processable, Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private ServerProtocol serverProtocol;
	private Human human;
	private WorldFrame worldFrame;
	private boolean yourTurn;
	private FightFrameNPC enemy;
	private boolean notEnded=true;
	
	public FightFrameNPC(ServerProtocol sP, Human h, WorldFrame wF, boolean yTurn){
	
		setServerProtocol(sP);
		setHuman(h);
		setWorldFrame(wF);
		setYourTurn(yTurn);
		
		sP.getPlayer().getCity().getEntities().remove(sP);
		sP.getPlayer().getCity().getEntities().remove(h);
		
		sP.setProcessable(this);
		
		new Thread(this).start();
		
		
	}
	
	
	@Override
	public void processInput(String[] s, ServerProtocol sP) {
		if(yourTurn){
		switch(s[0].toLowerCase()){
			
		case "attack":
			human.getDamaged(sP.getPlayer().getPrimaryAttribute().getPoints());
			sP.sendMessageToAll(new NetInput("You attacked your enemy with "+sP.getPlayer().getPrimaryAttribute().getPoints()+" damage",Console.standardEvent));
			sP.sendMessageToAll(new NetInput("Enemy life left: " + human.getHealth() + " of "+ human.getMaxHealth(),Console.standardEvent));

			if(!human.isAlive()){
				notEnded=false;
				sP.setProcessable(worldFrame);
				sP.getPlayer().getCity().getEntities().add(sP.getPlayer());
				sP.sendMessageToAll(new NetInput("You won",Console.standardEvent));
			}else{
				yourTurn=false;
			}
			
			
			break;
		case "skip":
			sP.sendMessageToAll(new NetInput("You skipped your turn",Console.standardEvent));
			yourTurn=false;
			
			break;
		case "surrender":
			sP.getPlayer().getCity().getEntities().add(sP.getPlayer());
			human.setHealth(human.getMaxHealth());
			sP.getPlayer().getCity().getEntities().add(human);
			sP.setProcessable(worldFrame);
			for(Item i : sP.getPlayer().getInventory()){
				if(i.getName().toLowerCase().equals("gold")){
					i.setQuantity(i.getQuantity()/2);
					sP.sendMessage(new NetInput("You lost half of your gold, but got away with your life!",Console.errorOutput));
				}
			}

			break;
		default:
			sP.sendMessage(new NetInput("Command not found, type 'help' for information"));
			break;
			}
		}else{
			sP.sendMessage(new NetInput("It's not your turn", Console.errorOutput));
			
		}
	}

	@Override
	public String getStartInput() {
		
		return "You can't be here, type 'surrender'";
	}

	
	@Override
	public void run() {
		
		while(notEnded){
			if(!yourTurn){
				serverProtocol.getPlayer().getDamaged(human.getPrimaryAttribute().getAttackDamage());
				serverProtocol.sendMessageToAll(new NetInput("You got attacked with: " + human.getPrimaryAttribute().getAttackDamage(),Console.standardEvent));
				serverProtocol.sendMessageToAll(new NetInput("Life left: " + serverProtocol.getPlayer().getHealth() + " of "+ serverProtocol.getPlayer().getMaxHealth(),Console.standardEvent));
				
				if(!serverProtocol.getPlayer().isAlive()){
					serverProtocol.getPlayer().killPlayer(human,serverProtocol);
					notEnded=false;
					serverProtocol.getPlayer().getCity().getEntities().add(human);
				}else{
					yourTurn=true;
					}
			}else{
			
			try {
				Thread.sleep(500L);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
	 * @return the yourTurn
	 */
	public boolean isYourTurn() {
		return yourTurn;
	}


	/**
	 * @param yourTurn the yourTurn to set
	 */
	public void setYourTurn(boolean yourTurn) {
		this.yourTurn = yourTurn;
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
	 * @return the enemy
	 */
	public FightFrameNPC getEnemy() {
		return enemy;
	}


	/**
	 * @param enemy the enemy to set
	 */
	public void setEnemy(FightFrameNPC enemy) {
		this.enemy = enemy;
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
