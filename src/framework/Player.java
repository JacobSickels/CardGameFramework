package framework;

import java.util.List;
import java.util.Random;

public abstract class Player {
	
	protected String name;	
	protected Hand hand;
	
	public abstract void play(List<Card> cardsToUse, List<Card> pileDrawFrom);
	
	public Player(){
		name = "";
		hand = new Hand();
	}
	
	public Player(String n){
		name = n;
		hand = new Hand();
	}
	
	public void addCard(Card c){
		hand.addCard(c);
	}
	
	/*
	 * Returns a matching card if it has a matching value
	 * 
	 * Returns null if nothing is found or if input card is null
	 */
	public Card hasMatchingValue(Card input){
		if(input == null) return null;
		
		for(int i = 0; i < hand.getCardCount(); i++){
			if(input.getValue() == hand.getCard(i).getValue())
				return hand.getCard(i);
		}
		
		return null;
	}
	
	
	/*
	 * Returns a matching card if it has a matching suit
	 * 
	 * Returns null if nothing is found or if input card is null
	 */
	public Card hasMatchingSuit(Card input){
		if(input == null) return null;
		
		for(int i = 0; i < hand.getCardCount(); i++){
			if(input.getSuit() == hand.getCard(i).getSuit())
				return hand.getCard(i);
		}
		
		return null;
	}
	
	/*
	 * Returns a matching card if it has a matching suit OR value
	 * 
	 * Returns null if nothing is found or if input card is null
	 */
	public Card hasMatchingValueOrSuit(Card input){
		if(input == null) return null;
		
		for(int i = 0; i < hand.getCardCount(); i++){
			if(input.getSuit() == hand.getCard(i).getSuit())
				return hand.getCard(i);
			if(input.getValue() == hand.getCard(i).getValue())
				return hand.getCard(i);
		}
		
		return null;
	}
	
	
	public boolean isHandEmpty(){
		return hand.getCardCount() == 0;
	}
	
	public int getCardCount(){
		return hand.getCardCount();
	}
	
	public Card getCard(int index){
		return hand.getCard(index);
	}
	
	public boolean removeCard(Card c){
		if(c == null) return false;
		hand.removeCard(c);
		return true;
	}
	
	public Card getRandomCard(){
		Random rand = new Random();
		return getCard(rand.nextInt(hand.getCardCount()));
	}
	
	public boolean removeMultiCards(List<Card> list){
		if(list == null) return false;
		if(list.size() == 0) return false;
		
		for(Card c : list){
			removeCard(c);
		}
		
		return true;
	}
	
	public String printHand(){
		return hand.toString();
	}
	
	public String getName(){
		return name;
	}
	
	public String toString(){
		return name + " " + hand.toString();
	}
}
