package ch.ilikechickenwings.TXTRAP.Entity;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public class Whore extends Human{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int timesHadSex=0;
	
	public Whore(float health, String name, City city, String responseLine1, Place place) {
		super(health, name, city, responseLine1,place);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String getResponseLine() {
		// TODO Auto-generated method stub
		
		String t=null;
		if(timesHadSex<2){
			t=" time";
		}else{
			t=" times";
		}
		
		return "I was fucked "+timesHadSex+t;
	}



	/**
	 * @return the timesHadSex
	 */
	public int getTimesHadSex() {
		return timesHadSex;
	}



	/**
	 * @param timesHadSex the timesHadSex to set
	 */
	public void setTimesHadSex(int timesHadSex) {
		this.timesHadSex = timesHadSex;
	}


}
