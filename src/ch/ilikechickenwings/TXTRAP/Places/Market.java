package ch.ilikechickenwings.TXTRAP.Places;

import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Frames.WorldFrame;

public class Market extends Place{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Item> items = new ArrayList<Item>();
	
	
	public Market(WorldFrame wF){
		setName("Market");
		setWorldFrame(wF);
		
		
	}
	
	
	@Override
	public void processInput(String[] s, ServerProtocol sP) {
		
		switch (s[0].toLowerCase()){
			case "help":
				sP.sendMessage(new NetInput("Available commands:"
						+"\n showitems -> shows the items to sell"
						+"\n buy <item name> <optinal: quantity> -> buys the item"
						+"\n sell <item name> <optional: quantity> -> shows the items to sell"
						+"\n inventory -> shows your inventory"
						+"\n leave -> Stops the interaction"));
				break;
			case "showitems":
				sP.sendMessage(new NetInput("On the market available is: ", Console.standardOutput));
				
				if(items.size()>0){
					for(Item it : items){
					
						sP.sendMessage(new NetInput("->"+it.getName()+" - "+it.getPrice()+" Gold", Console.standardListOutput));
					}
				}else{
					sP.sendMessage(new NetInput("-->nothing<-- (market is poor as fuck)", Console.standardListOutput));
				}
				break;
			case "buy":
				if(s.length==3){
				boolean done=false;
				ArrayList<Item> pItems=sP.getPlayer().getInventory();
					for(Item m : items){
					if(s[1].toLowerCase().equals(m.getName().toLowerCase())){
						for(Item mp : pItems){
							if(mp.getName().toLowerCase().equals("gold")){
								if(mp.getQuantity()>=Integer.parseInt(s[2])*m.getPrice()){
									mp.setQuantity((int)(mp.getQuantity()-Integer.parseInt(s[2])*m.getPrice()));
									
									for(Item nm : pItems){
						
										if(nm.getName().equals(m.getName())){
											nm.setQuantity(nm.getQuantity()+Integer.parseInt(s[2]));
											done=true;
										}
									}
									if(!done){
										pItems.add(new Item(m.getName(),Integer.parseInt(s[2]),m.getPrice()));	
										done=true;
									}
									break;
								}
							
								
							}
							
						}
						
					
							}
					if(done){
						sP.sendMessage(new NetInput("You bought "+s[2]+"x "+s[1],Console.standardEvent));
						break;
					}
						}
					
				if(!done){
					sP.sendMessage(new NetInput("Can't buy this",Console.errorOutput));
				}
				
				}else{
					sP.sendMessage(new NetInput("Commend was used wrong: buy <item> <quantity>",Console.errorOutput));
				}
				break;
			case "sell":
				
				if(s.length==3){
				 boolean done=false;
				ArrayList<Item> pItems=sP.getPlayer().getInventory();
					for(Item m : pItems){
					if(s[1].toLowerCase().equals(m.getName().toLowerCase())){
							
								if(m.getQuantity()>=Integer.parseInt(s[2])){
									m.setQuantity(m.getQuantity()-Integer.parseInt(s[2]));
									
									for(Item nm : pItems){
						
										if(nm.getName().toLowerCase().equals("gold")){
											nm.setQuantity((int)(nm.getQuantity()+Integer.parseInt(s[2])*(m.getPrice()/2)));
											done=true;
											break;
										}
									}
									
									
								}
					
							}
					if(done){
						sP.sendMessage(new NetInput("You sold "+s[2] +"x "+m.getName()));
						break;
					}
						}
					if(!done){
						sP.sendMessage(new NetInput("Can't sell item",Console.errorOutput));
					}
				}else{
					sP.sendMessage(new NetInput("Commend was used wrong: sell <item> <quantity>",Console.errorOutput));
				}
				
				break;
			case "inventory":
				sP.sendMessage(new NetInput("In your Inventory is: ", Console.standardOutput));
					if(sP.getPlayer().getInventory().size()>0){
						for(Item mm : sP.getPlayer().getInventory()){
						
						if(mm.getQuantity()<0){
							sP.getPlayer().getInventory().remove(mm);
				
						}else{
							sP.sendMessage(new NetInput("->"+Integer.toString(mm.getQuantity())+"x "+mm.getName(), Console.standardListOutput));
						}
						}
					}else{
						sP.sendMessage(new NetInput("-->nothing<-- (poor as fuck...)", Console.standardListOutput));
					}
				
					break;
			case "leave":
				stopInteract(sP);
				sP.getPlayer().setPlace(null);
				sP.sendMessage(new NetInput("You left the market",Console.standardEvent,true,true));
				break;
			default:
				sP.sendMessage(new NetInput("Command not found",Console.errorOutput));
				break;
		
		
		
		
		}
	}
	
	
	
	@Override
	public String getStartInput() {
		return "You are at the market, type 'help' for more information";
	}

	@Override
	public void interact(ServerProtocol sP) {
		sP.setProcessable(this);
		sP.sendMessage(new NetInput("Welcome to the market, write 'help' for more information",Console.standardOutput,true,true));
		
		
	}


	@Override
	public void stopInteract(ServerProtocol sP) {
		sP.setProcessable(sP.getWorldFrame());
		
	}


	/**
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}


	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}

}
