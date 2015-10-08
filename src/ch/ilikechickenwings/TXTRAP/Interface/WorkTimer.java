package ch.ilikechickenwings.TXTRAP.Interface;

import java.io.Serializable;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class WorkTimer implements Runnable, Processable, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient ServerProtocol sP;
	private int hours;
	private long beginTime;
	private boolean running=true;
	private WorldFrame worldFrame;
	
	
	
	public WorkTimer(ServerProtocol sP2, int hours1, WorldFrame wF){
		this.setsP(sP2);
		this.hours=hours1;
		this.beginTime=wF.getTime();
		this.worldFrame=wF;
		getsP().setProcessable(this);
		System.out.println("here");
		new Thread(this).start();
		getsP().sendMessage(new NetInput("You are now working " + hours +" hour(s)",Console.standardEvent,true,true));
		getsP().sendMessage(new NetInput("Type help for more informations",Console.standardOutput));
		
	}
	
	
	@Override
	public synchronized void run() {
		
		while(running){
			if(beginTime+(hours*60)<getsP().getWorldFrame().getTime()){
			if(!getsP().getSocket().isClosed()){
				boolean done=false;
				for(Item i : getsP().getPlayer().getInventory()){
					if(i.getName().toLowerCase().equals("gold")){
						i.setQuantity(i.getQuantity()+100*hours);
						done=true;
					}
					
				}
				if(!done){
					getsP().getPlayer().getInventory().add(new Item("Gold",100*hours,1));
					
				}
				getsP().setProcessable(worldFrame);
				running=false;
				
				getsP().sendMessage(new NetInput("You got " +hours*100+" Gold for your work",Console.standardEvent,true,true));
			}else{
				running=false;
			}
			}else{
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		
	}


	@Override
	public void processInput(String[] s,ServerProtocol sP1) {
		switch (s[0].toLowerCase()){
		case "help":
			sP1.sendMessage(new NetInput("You are working right now, you can't do anything right now. You can stop working but you won't get any gold for your work. Use 'stop' to stop working. Use 'status' to see how long you have left.",Console.standardOutput));
			break;
		case "stop":
			
			sP1.sendMessage(new NetInput("You stoped working before the work ended",Console.standardEvent,true,true));
			running=false;
			sP1.setProcessable(worldFrame);
			break;
		case "status":
			sP1.sendMessage(new NetInput("Work hours left: " + (beginTime+(hours*60)-worldFrame.getTime())+ " from "+ hours*60+" minutes",Console.standardOutput));
			break;
		default:
			sP1.sendMessage(new NetInput("Command not found",Console.errorOutput));
			break;
		
		
		}
		
	}


	@Override
	public String getStartInput() {
		
		return "You are working right now, press help for more informations";
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
	 * @return the sP
	 */
	public ServerProtocol getsP() {
		return sP;
	}


	/**
	 * @param sP the sP to set
	 */
	public void setsP(ServerProtocol sP) {
		this.sP = sP;
	}

}
