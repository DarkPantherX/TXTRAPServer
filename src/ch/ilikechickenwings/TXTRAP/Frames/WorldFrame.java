package ch.ilikechickenwings.TXTRAP.Frames;

import java.io.Serializable;
import java.util.ArrayList;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Console;
import ch.ilikechickenwings.TXTRAP.NetInput;
import ch.ilikechickenwings.TXTRAP.ServerProtocol;
import ch.ilikechickenwings.TXTRAP.Entity.Entity;
import ch.ilikechickenwings.TXTRAP.Entity.Human;
import ch.ilikechickenwings.TXTRAP.Entity.Item;
import ch.ilikechickenwings.TXTRAP.Entity.Player;
import ch.ilikechickenwings.TXTRAP.Entity.Whore;
import ch.ilikechickenwings.TXTRAP.Frames.Processable;
import ch.ilikechickenwings.TXTRAP.Interface.SaveListener;
import ch.ilikechickenwings.TXTRAP.Interface.WorkTimer;
import ch.ilikechickenwings.TXTRAP.Places.Market;
import ch.ilikechickenwings.TXTRAP.Places.Place;
import ch.ilikechickenwings.TXTRAP.Places.PostOffice;
import ch.ilikechickenwings.TXTRAP.Places.Whorehouse;

public class WorldFrame implements Processable, Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ArrayList with city in the worlds
	 */
	private ArrayList<City> cities = new ArrayList<City>();
	private ArrayList<StringBuilder> letters = new ArrayList<StringBuilder>();
	private ArrayList<String> pvisits = new ArrayList<String>();

	private boolean gameRunning = true;
	private String name;

	public static ArrayList<ServerProtocol> players = new ArrayList<ServerProtocol>();

	/**
	 * Time for the game
	 */
	private long time = 0;

	/**
	 * Creates new World only with MainFrame
	 */
	public WorldFrame(String name1) {

		createWorld();
		new Thread(this).start();
		setName(name1);

	}

	private void createWorld() {
		City city = new City("Tamariel");

		PostOffice p = new PostOffice();

		
		Market m = new Market(this);
		m.getItems().add(new Item("Sword", 0, 50));
		city.getPlaces().add(m);
		city.getPlaces().add(p);
		Whore peter = new Whore(100,"Peter",city,"",null);
		peter.setGreeting("Hello!");
		String[] oL = {"The weather is great today!","I don't like you","I like your hat"};
		String[] aL = {"Yes, it is.", "Fuck off!","Who doesn't like hats?"};
		peter.setOptionList(oL);
		peter.setAnswerList(aL);
		city.getEntities().add(peter);
		cities.add(city);
		city = new City("Bananistan");
		p = new PostOffice();
		city.getPlaces().add(p);
		cities.add(city);
		city = new City("Eschenbach");
		
		p = new PostOffice();
		
		city.getPlaces().add(p);
		cities.add(city);
		
		city = new City("Schmerikon");
		
		p = new PostOffice();
		city.getPlaces().add(p);
		
		cities.add(city);
		city = new City("Ass");
		
		
		

		
		p = new PostOffice();
		city.getPlaces().add(p);
		
		Whorehouse h = new Whorehouse(this);
		Whore h1 = new Whore(100, "Anna", city, "", h);
		h.getHumans().add(h1);
		city.getPlaces().add(h);
		
		cities.add(city);
	}

	@Override
	public void processInput(String[] s, ServerProtocol sP) {

		switch (s[0].toLowerCase()) {
		case "attack":
			boolean done1=false;
			if(s.length>1){
				for(Entity ent1 : sP.getPlayer().getCity().getEntities()){
					if(ent1 instanceof Player){
						for(ServerProtocol pa : players){
							if(pa.getPlayer().getName().toLowerCase().equals(s[1].toLowerCase())){
								
								sP.sendMessage(new NetInput("You attacked: " + s[1]));
								new FightFramePlayer(sP, pa,this,false);
								
								done1=true;
								break;
								
							}
						}
						
						
					}else if(ent1 instanceof Human){
						Human h1= (Human) ent1;
						if(h1.getName().toLowerCase().equals(s[1].toLowerCase())){
							sP.sendMessage(new NetInput("You attacked: " + s[1]));
							new FightFrameNPC(sP, h1,this,false);

							done1=true;
							break;
							}
						}
					}
				
				if(!done1){
					sP.sendMessage(new NetInput("Nobody found with this name",Console.errorOutput));

				}
				}
			break;
		case "help":
			sP.sendMessage(new NetInput(
					"Available commands: \n map ->Showes Cities "
							+ "\n goto <cityname> ->You go to the chosen city"
							+ "\n status ->Tells you how many lifes you have left"
							+ "\n attack <target> ->Attacks a player or NPC"
							+ "\n talk <target> ->Talk to a NPC"
							+ "\n inventory -> Shoes you your inventory"
							+ "\n time ->shows the current time"
							+ "\n interact <place> ->Interact with a place in this city"
							+ "\n work <hours> ->Work to get 100 gold per hour"
							+ "\n s -> Say something to all player near you"
							+ "\n letter <playername> <text> -> write a letter to a player, he will see it, as soon as he goes to the post office"
							+ "\n lookaround -> shows you the places you can interact with and the people around you",
					Console.standardOutput));
			break;
		case "goto":
			if (s.length > 1) {
				boolean notFound = true;
				int i = cities.size() - 1;
				City c;
				while (notFound) {
					c = (City) cities.get(i);
					if (c.getCityName().toLowerCase()
							.equals(s[1].toLowerCase())) {
						notFound = false;
					}
					--i;
					if (!notFound) {
						sP.getPlayer().getCity().getEntities().remove(sP.getPlayer());
						sP.getPlayer().setCity(c);
						c.getEntities().add(sP.getPlayer());
						sP.sendMessage(new NetInput("You went to: "
								+ c.getCityName(), Console.standardEvent));
					} else if (i < 0) {
						break;
					}

				}
				if (notFound) {

					sP.sendMessage(new NetInput("Couldn't find you city",
							Console.errorOutput));
				}
			}
			break;
		case "map":
			sP.sendMessage(new NetInput("Cities here:", Console.standardOutput));
			City city;
			for (int in = 0; in < cities.size(); in++) {
				city = (City) cities.get(in);
				sP.sendMessage(new NetInput(" " + city.getCityName() + ",",
						Console.standardListOutput, false, true));
			}
			break;
		case "mine":
			break;
		case "sleep":
			break;

		case "status":
			sP.sendMessage(new NetInput("Your name is "
					+ sP.getPlayer().getName() + " the "
					+ sP.getPlayer().getGameClass(), Console.standardOutput));
			sP.sendMessage(new NetInput("Health: ", Console.standardOutput));
			int h1 = (int) (sP.getPlayer().getHealth()
					/ sP.getPlayer().getMaxHealth() * 10);
			for (int i1 = 0; i1 < 10; i1++) {
				if (h1 > 0) {
					sP.sendMessage(new NetInput("O", Console.standardOutput,
							false, true));
					h1--;
				} else {
					sP.sendMessage(new NetInput("X", Console.standardOutput,
							false, true));
				}

			}

			sP.sendMessage(new NetInput(" --> "
					+ Double.toString(sP.getPlayer().getHealth())
					+ "% life left", Console.standardOutput, false, true));

			sP.sendMessage(new NetInput(
					"You are in the great "
							+ sP.getPlayer().getCity().getCityName()
							+ " at the moment", Console.standardOutput));
			break;
		case "inventory":
			sP.sendMessage(new NetInput("In your Inventory is: ",
					Console.standardOutput));

			if (sP.getPlayer().getInventory().size() > 0) {
				for (Item mm : sP.getPlayer().getInventory()) {
					if (mm.getQuantity() == 0) {
						sP.getPlayer().getInventory().remove(mm);
					} else {
						sP.sendMessage(new NetInput("->"
								+ Integer.toString(mm.getQuantity()) + "x "
								+ mm.getName(), Console.standardListOutput));
					}
				}
			} else {
				sP.sendMessage(new NetInput("-->nothing<-- (poor as fuck...)",
						Console.standardListOutput));
			}

			break;
		case "time":
			int minTime = (int) time % 60;
			int hourTime = (int) (time / 60) % 24;
			int dayTime = (int) ((time / 60) / 24) % 30;
			int monthTime = (int) (((time / 60) / 24) / 30) % 12;
			long yearTime = (long) (((time / 60) / 24) / 30) / 12;

			sP.sendMessage(new NetInput("It is: ", Console.standardOutput));
			if (hourTime / 10 == 0) {
				sP.sendMessage(new NetInput(Integer.toString(0),
						Console.standardListOutput, false, true));
			}
			sP.sendMessage(new NetInput(hourTime + ":",
					Console.standardListOutput, false, true));
			if (minTime / 10 == 0) {
				sP.sendMessage(new NetInput(Integer.toString(0),
						Console.standardListOutput, false, true));
			}
			sP.sendMessage(new NetInput(minTime + " - ",
					Console.standardListOutput, false, true));
			sP.sendMessage(new NetInput(Integer.toString(dayTime + 1) + ".",
					Console.standardListOutput, false, true));
			sP.sendMessage(new NetInput(Integer.toString(monthTime + 1) + ".",
					Console.standardListOutput, false, true));
			sP.sendMessage(new NetInput(Integer.toString((int) yearTime + 1),
					Console.standardListOutput, false, true));

			break;

		case "s":
			StringBuilder buil = new StringBuilder();
			for (String str : s) {
				buil.append(str + " ");

			}
			buil.replace(0, 2, "");
			for (ServerProtocol sePr : players)
				if (sePr.getPlayer().getCity().getCityName().equals(sP.getPlayer().getCity().getCityName())) {
					sePr.sendMessage(new NetInput("<"+sP.getPlayer().getName()+">"
							+ ": " + buil.toString()));
				}
			break;
		case "interact":
			if (s.length > 1) {
				Place p1 = null;
				for (int i3 = 0; i3 < sP.getPlayer().getCity().getPlaces()
						.size(); i3++) {

					Place p = (Place) sP.getPlayer().getCity().getPlaces()
							.get(i3);
					if (s[1].toLowerCase().equals(p.getName().toLowerCase())) {
						p1 = p;
					}

					if (p1 != null) {
						sP.getPlayer().setPlace(p1);
						sP.getPlayer().getPlace().interact(sP);

					} else {
						sP.sendMessage(new NetInput("Place not found",
								Console.errorOutput));

					}
				}
			} else {
				sP.sendMessage(new NetInput("use: interact <place>",
						Console.errorOutput));
			}
			break;
		case "work":
			if (s.length == 2) {
				new WorkTimer(sP, Integer.parseInt(s[1]), this);
			} else {
				sP.sendMessage(new NetInput(
						"Command was used wrong: work <hours>"));
			}
			break;
		case "letter":
			if (s.length > 2) {
			boolean done = false;
				for(String sspp :pvisits){
					if(sspp.toLowerCase().equals(s[1].toLowerCase())){
						StringBuilder stribuil= new StringBuilder(s[1]+"-!-");
						
						for(int ix=2; ix<s.length;ix++){
							stribuil.append(s[ix]+" ");
							
						}
						stribuil.append("\nfrom: " + sP.getPlayer().getName());
						letters.add(stribuil);
						done=true;
						break;
					}
				}
				
				if(done){
					for(ServerProtocol ssPP : players){
						if(ssPP.getPlayer().getName().equals(s[1])){
							ssPP.sendMessage(new NetInput("You got an letter, go to the next post office to get it!",Console.errorOutput));
						}
						
						
					}
					
					
					
				}else{
					sP.sendMessage(new NetInput("No player with this name on this server!",Console.errorOutput));

				}
				
				
			} else {
				sP.sendMessage(new NetInput(
						"Command was used wrong: letter <player> <messages>..."));
			}
			break;
			
		case "lookaround":
			StringBuilder stringb= new StringBuilder("Places around in your location: ");
			for(Place p : sP.getPlayer().getCity().getPlaces()){
				stringb.append(p.getName()+", ");
			}
			stringb.append("\nPeople in your location:");
			for(Entity ent : sP.getPlayer().getCity().getEntities()){
				if(ent instanceof Player){
					stringb.append(((Player) ent).getName()+"(Player), ");

				}else if(ent instanceof Human){
				stringb.append(((Human) ent).getName()+", ");
				}
			}
			
			sP.sendMessage(new NetInput(stringb.toString(),Console.standardListOutput));
			break;

		case "talk":
			boolean done2=false;
			if(s.length>1){
				for(Entity ent1 : sP.getPlayer().getCity().getEntities()){
					if(ent1 instanceof Human){
						Human h2= (Human) ent1;
						if(h2.getName().toLowerCase().equals(s[1].toLowerCase())){
							sP.sendMessage(new NetInput("You are talking to: " + s[1]));
							new TalkFrameNPC(sP, h2,this);

							done2=true;
							break;
						}
					}
				}

				if(!done2){
					sP.sendMessage(new NetInput("Nobody found with this name",Console.errorOutput));

				}
			}

			break;

		case "offline":
			sP.sendMessage(new NetInput("You will disconnect now!",Console.errorOutput));
			sP.sendMessage(new NetInput("y!-!quit!-!y",Console.errorOutput));
			sP.close();
			break;
			
		default:
			sP.sendMessage(new NetInput("Command not found",
					Console.errorOutput));
			break;

		}

	}

	public void loadedGame(ServerProtocol sP) {
		sP.sendMessage(new NetInput("Loaded", Console.standardEvent, true, true));
		sP.sendMessageToAll(new NetInput(sP.getPlayer().getName()
				+ " entered the game",Console.startOutput));
		sP.sendMessage(new NetInput("Welcome back, " + sP.getPlayer().getName()
				+ " the " + sP.getPlayer().getGameClass(), Console.startOutput));
		sP.sendMessage(new NetInput("You are in "
				+ sP.getPlayer().getCity().getCityName(), Console.startOutput));
		sP.sendMessage(new NetInput(sP.getProcessable().getStartInput(),
				Console.startOutput));
		
		int anz=0;
		for(StringBuilder let : sP.getWorldFrame().getLetters()){
			if(let.toString().startsWith(sP.getPlayer().getName())){
				anz++;
				}
			}
		sP.sendMessage(new NetInput("You have "+anz+" new letter(s)",Console.errorOutput));
		  
		}

	@Override
	public void run() {

		new Thread(new SaveListener(this)).start();
		long timeOld = System.currentTimeMillis();
		while (gameRunning) {

			long timeNow = System.currentTimeMillis();

			if (timeOld + 1000 < timeNow) {
				time = time + ((timeNow / 1000) - (timeOld / 1000));
				timeOld = timeNow;
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public String getStartInput() {
		return "You roam the world freely, press help for more information";
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the cities
	 */
	public ArrayList<City> getCities() {
		return cities;
	}

	/**
	 * @param cities
	 *            the cities to set
	 */
	public void setCities(ArrayList<City> cities) {
		this.cities = cities;
	}

	/**
	 * @return the letters
	 */
	public ArrayList<StringBuilder> getLetters() {
		return letters;
	}

	/**
	 * @param letters the letters to set
	 */
	public void setLetters(ArrayList<StringBuilder> letters) {
		this.letters = letters;
	}

	/**
	 * @return the pvisits
	 */
	public ArrayList<String> getPvisits() {
		return pvisits;
	}

	/**
	 * @param pvisits the pvisits to set
	 */
	public void setPvisits(ArrayList<String> pvisits) {
		this.pvisits = pvisits;
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

}
