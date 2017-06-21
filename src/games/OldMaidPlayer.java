package games;

import java.util.List;

import framework.Card;
import framework.Player;

public class OldMaidPlayer extends Player {
		
	public OldMaidPlayer(String name){
		this.name = name;
	}

	@Override
	public void play(List<Card> cardsToUse, List<Card> pileDrawFrom) {
		
		hand.addCard(pileDrawFrom.get(0));
		System.out.println("\t" + name + " drew a " + pileDrawFrom.get(0));
		pileDrawFrom.remove(0);
		removePair();
		
	}
	
	public boolean removePair(){
		
		
		for(int i = 0; i < hand.getCardCount() - 1; i++){
			for(int j = i + 1; j < hand.getCardCount(); j++){
			
				if(hand.getCard(i).getValue() == hand.getCard(j).getValue()){
					System.out.println("REMOVING PAIRS :" + hand.getCard(i) + " and " + hand.getCard(j));
					Card temp1 = hand.getCard(i);
					Card temp2 = hand.getCard(j);
					hand.removeCard(temp1);
					hand.removeCard(temp2);
					
					
					return true;
				}
			}
		}
		return false;
		
	}
	
	public void removeAllPairs(){
		while(removePair()){
			System.out.println("REMOVED PAIRS");
		}
	}

}
