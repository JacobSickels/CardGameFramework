package framework;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
	
	private int numCards;
	private Deck deck;
	
	public Dealer(int numCards){
		this.numCards = numCards;
		deck = new Deck();
		//init();
	}
	
	public Dealer(){
		numCards = 52;
		deck = new Deck();
		//init();
	}
	
	public void init(){
		deck.shuffle();
	}
	
	public Card dealCard(){
		return deck.dealCard();
	}
	
	public int getCardsLeft(){
		return deck.cardsLeft();
	}
	
	public void shuffle(){
		deck.shuffle();
	}
	
	public void dealCardsToPlayers(List<Player> players){
		for(Player p : players)	{	
			for(int i = 0; i < numCards; i++){
				p.addCard(dealCard());
			}
		}
	}
	
	public void dealAllCards(List<Player> players){
		Card temp = null;
		while(getCardsLeft() > 0){
			for(Player p: players){
				
				if(getCardsLeft() == 0) break;
				
				temp = dealCard();
				
				if(temp == null ) break;
				p.addCard(temp);
			}
		}
	}
	
	public boolean removeCard(int index){
		return deck.removeCard(index);
	}
	
	public List<Card> dealDeckToList(){
		
		List<Card> deckStack = new ArrayList<Card>();

		for(int i = 0; i < getCardsLeft() - 1; i++){
			deckStack.add(dealCard());
		}
		
		return deckStack;
	}
	
}
