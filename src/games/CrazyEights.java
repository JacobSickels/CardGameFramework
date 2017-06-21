package games;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import framework.Card;
import framework.Game;
import framework.Player;

public class CrazyEights extends Game {

	List<Card> discard, deckStack;

	String[] tempNames = {"Bennett", "Jacob", "Kristen", "Sarah"};

	public CrazyEights(int numPlayers, int numCards){
		super(numPlayers, numCards);

		for(int i = 0; i < numPlayers; i++){
			Player p = new CrazyEightsPlayer(tempNames[i]);
			players.add(p);
		}

		discard = new ArrayList<Card>();
		deckStack = new ArrayList<Card>();
		dealer.shuffle();
		dealer.dealCardsToPlayers(players);
		deckStack = dealer.dealDeckToList();
		
		//We need to deal 1 card to discard to start the game off
		discard.add(deckStack.remove(0));
	}

	@Override
	public boolean playRound() {

		for(Player p : players){


			System.out.println("\t" + p.getName() + "'s Hand: " + p.printHand());
			System.out.println("\t DISCARD: " + discard.get(0));
			p.play(discard, deckStack);



			if(p.getCardCount() == 0){
				return false; //False means that the round is over
			}
		}

		return true; //True means the game continues to play

	}

	@Override
	public void displayWinner() {

		System.out.println("\n==================== \n\tRESULTS \n====================");
		
		for(Player p : players){
			if(p.getCardCount() == 0)
				System.out.println(p + " is the winner");
			else
				System.out.println(p.toString());
		}

	}


}
