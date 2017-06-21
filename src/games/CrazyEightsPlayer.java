package games;

import java.util.List;
import java.util.Random;

import framework.Card;
import framework.Deck;
import framework.Player;

public class CrazyEightsPlayer extends Player {


	public CrazyEightsPlayer(){
		super();
	}

	public CrazyEightsPlayer(String n){
		super(n);
	}

	public void removeDuplicates(Card c){
		if(c == null) return;

		for(int i = 0; i < hand.getCardCount(); i++){
			if(hand.getCard(i).getValue() == c.getValue()){
				System.out.println(name + " PLAYED A " + hand.getCard(i));
				hand.removeCard(i);
			}
		}
	}

	@Override
	public void play(List<Card> cardsToUse, List<Card> pileDrawFrom) {

		boolean foundMatch = false;

		Card topCard = cardsToUse.get(0); // If this is null the discard pile is empty

		Card myCard = hasMatchingValueOrSuit(topCard);

		if(myCard != null) foundMatch = true;

		//The player didn't have a matching card or a matching suit
		while(!foundMatch){

			//If deck is empty make new deck
			if(pileDrawFrom.isEmpty()){
				Deck d = new Deck();
				d.shuffle();
				int dl = d.cardsLeft();
				for(int i = 0; i < dl; i++){
					pileDrawFrom.add(d.dealCard());
				}
			}

			//Drawing Card from the Deck then adding it to hand
			myCard = pileDrawFrom.remove(0); //<< If this pile becomes empty what do we do?
			hand.addCard(myCard);

			System.out.println(this.name +  " DREW A " + myCard);

			// We shouldn't have to loop through the hand again to see if there are
			// matches when we are only adding one card. So temporarily store the new card
			// and check if it's value or suit match the top of discard pile
			if(myCard.compareValue(topCard) || myCard.compareSuit(topCard)){
				foundMatch = true;
			}

		}


		System.out.println(this.name + " played " + myCard);

		//check for playing an 8
		if(myCard.getValue() == 8){
			hand.removeCard(myCard);
			cardsToUse.add(0, getRandomSuit());
		}
		else {
			//Playing card
			hand.removeCard(myCard);
			cardsToUse.add(0, myCard);
		}

		//Checking and removing multiple cards of same value
		if(myCard.compareValue(topCard))
			removeDuplicates(myCard);

		//Remove reference for garbage collection
		myCard = null;

	}

	public Card getRandomSuit(){
		Random rand = new Random();
		Card c = new Card(8, rand.nextInt(4));

		System.out.println(name + " PLAYED AN 8 AND CHOSE " + c.getSuitAsString());

		return c;
	}

}
