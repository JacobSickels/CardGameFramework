package framework;

import java.util.ArrayList;
import java.util.List;

public abstract class Game {

	protected List<Player> players;
	protected Dealer dealer;
	
	public abstract boolean playRound();
	public abstract void displayWinner();
	
	public Game(int numPlayers, int numCards){
		players = new ArrayList<Player>(numPlayers);
		dealer = new Dealer(numCards);
	}
	
	public Game(int numPlayers){
		players = new ArrayList<Player>(numPlayers);
		dealer = new Dealer();
	}
	
	public Game(){
		players = new ArrayList<Player>();
		dealer = new Dealer();
	}
	
	
	private void execGameLoop(){
		int roundNum = 1;
		
		do {
			System.out.println("Round " + roundNum + ": ");
			roundNum++;
		}while(playRound());
		
		displayWinner();
	}
	
	public void start(){
		execGameLoop();
	}
	
	
}
