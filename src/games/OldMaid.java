package games;

import java.util.ArrayList;
import java.util.List;

import framework.Card;
import framework.Game;
import framework.Player;

public class OldMaid extends Game {
	
	List<Card> discard, randomCard;
	List<Player> donePlayers;
	
	String[] tempNames = {"Bennett", "Jacob", "Kristen", "Sarah"};
	
	boolean firstPlay = true;

	public OldMaid(int numPlayers){
		super(numPlayers);
		
		randomCard = new ArrayList<Card>();
		donePlayers = new ArrayList<Player>();
		
		for(int i = 0; i < numPlayers; i++){
			Player p = new OldMaidPlayer(tempNames[i]);
			players.add(p);
		}
		
		dealer.removeCard(50);
		dealer.shuffle();
		dealer.dealAllCards(players);	
		
		for(Player p : players){
			System.out.println(p);
			((OldMaidPlayer) p).removeAllPairs();
			System.out.println(p);
		}
		
		
	}

	@Override
	public boolean playRound() {
		
		for(int i = 0; i < players.size(); i++){

			System.out.println("\t" + players.get(i).getName() + "'s Hand: " + players.get(i).printHand());
			
			Card random = null;
			
			if(i == 0){
				random = players.get(players.size() - 1).getRandomCard();
				players.get(players.size() - 1).removeCard(random);
				
				if(hasNoCards(players.size() - 1)){
					donePlayers.add(players.get(players.size() - 1));
					players.remove(players.size() - 1);
				}
				
			}
			else{
				random = players.get(i - 1).getRandomCard();
				players.get(i - 1).removeCard(random);
				
				if(hasNoCards(i - 1)){
					donePlayers.add(players.get(i - 1));
					players.remove(i - 1);
					i--;
				}
			}
			
			randomCard.add(random);
			
			players.get(i).play(discard, randomCard);
			
			if(hasNoCards(i)) {
				donePlayers.add(players.get(i));
				players.remove(i);
				i--;
			}

			if(getCardsInPlay() == 1){
				return false;
			}
		}
		
		return true;
	}

	//Displaying Loser of game and players
	@Override
	public void displayWinner() {
		
		for(Player p : donePlayers){
			System.out.println(p + " IS A WINNER");
		}
		
		System.out.println(players.get(0) + " IS A LOSER");
		
	}
	
	public boolean hasNoCards(int i){
		if(players.get(i).getCardCount() == 0) return true;
		
		return false;
	}
	
	public int getCardsInPlay(){
		int count = 0;
		for(Player p : players){
			count += p.getCardCount();
		}
		return count;
	}

}
