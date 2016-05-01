package ch.ilikechickenwings.TXTRAP.Entity;

import java.util.Random;

import ch.ilikechickenwings.TXTRAP.City;
import ch.ilikechickenwings.TXTRAP.Places.Place;

public abstract class Human extends Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Saves the city the human lives in */
	private City city;
	private Place place;
	private String responseLine;
	private PrimaryAttribute primaryAttribute;
	
	private Item rightHand;
	private Item leftHand;



	private String greeting;
	private String[] optionList;
	private String[] answerList;
	
	/** Has the name of the human */
	private String name;
	
	public Human(float health, String name, City city, String responseLine1, Place place2){
		super(health);
		setName(name);
		setCity(city);
		setResponseLine(responseLine1);
		setPlace(place2);
		responseLine.concat("");
		setPrimaryAttribute(new PrimaryAttribute(10));
		
	}
	
	public double getDamage() {
		double val=getPrimaryAttribute().getAttackDamage();
		
		if(getRightHand()!=null){
			val=val+getRightHand().getDamageValue();
		}
		Random r= new Random();
		if(r.nextInt(10)<3){
			if(r.nextInt(10)==0){
				val=val*2;
			}
			val=val*1.5;
		}else{
			int temp=r.nextInt((int)val);
			if(temp<(int)(val/9)){
				if(r.nextInt(2)==0){
					val=val+temp;
				}else{
					val=val-temp;
					
				}
			}
			
		}
		
		
		return (double)((int)val);
	}
	
	
	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}




	/**
	 * @return the place
	 */
	public Place getPlace() {
		return place;
	}




	/**
	 * @param place the place to set
	 */
	public void setPlace(Place place) {
		this.place = place;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}







	/**
	 * @return the responseLine
	 */
	public abstract String getResponseLine();




	/**
	 * @param responseLine the responseLine to set
	 */
	public void setResponseLine(String responseLine) {
		this.responseLine = responseLine;
	}




	/**
	 * @return the primaryAttribute
	 */
	public PrimaryAttribute getPrimaryAttribute() {
		return primaryAttribute;
	}




	/**
	 * @param primaryAttribute the primaryAttribute to set
	 */
	public void setPrimaryAttribute(PrimaryAttribute primaryAttribute) {
		this.primaryAttribute = primaryAttribute;
	}




	/**
	 * @return the rightHand
	 */
	public Item getRightHand() {
		return rightHand;
	}




	/**
	 * @param rightHand the rightHand to set
	 */
	public void setRightHand(Item rightHand) {
		this.rightHand = rightHand;
	}




	/**
	 * @return the leftHand
	 */
	public Item getLeftHand() {
		return leftHand;
	}




	/**
	 * @param leftHand the leftHand to set
	 */
	public void setLeftHand(Item leftHand) {
		this.leftHand = leftHand;
	}




	/**
	 * @return the greeting
	 */
	public String getGreeting() {
		return greeting;
	}



	/**
	 * @param greeting the greeting to set
	 */
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}



	/**
	 * @return the optionList
	 */
	public String[] getOptionList() {
		return optionList;
	}



	/**
	 * @param optionList the optionList to set
	 */
	public void setOptionList(String[] optionList) {
		this.optionList = optionList;
	}

	/**
	 * @return the answerList
	 */
	public String[] getAnswerList() {
		return answerList;
	}

	/**
	 * @return the n-th element of the answerList
	 */
	public String getAnswer(int n) {
		if(n-1 < answerList.length)
			return answerList[n-1];
		else
			return "";
	}

	/**
	 * @return the n-th element of the optionList
	 */
	public String getOption(int n) {
		if(n-1 < optionList.length)
			return optionList[n-1];
		else
			return "";
	}


	/**
	 * @param answerList the answerList to set
	 */
	public void setAnswerList(String[] answerList) {
		this.answerList = answerList;
	}
	
	
}
