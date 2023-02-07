package competition.observer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import competition.competitor.Competitor;
import competition.displayer.Displayer;

public class BookMaker implements MatchObserver {
	
	protected String name;
	protected Map<Competitor, Integer> odds;
	protected List<Competitor> competitors;
	protected Displayer displayer;
	
	public BookMaker(String name, List<Competitor> competitors){
		this.name = name;
		this.competitors = competitors;
		
		this.odds = new LinkedHashMap<>();
		competitors.stream().forEach(c -> this.odds.put(c, new Random().nextInt(5)+1));
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Displayer getDisplayer() {
		return this.displayer;
	}

	public void setDisplayer(Displayer displayer) {
		this.displayer = displayer;
	}
	
	/**
	 * All the odds of the competitors
	 * @return points
	 */
	public Map<Competitor, Integer> getOdds() {
		return this.odds;
	}
	
	/**
	 * add odds to the competitor
	 * @param competitor
	 */
	public void addOdds(Competitor c) {
		this.odds.put(c, this.odds.get(c) + 1);
	}
	
	/**
	 * subtract odds to the competitor
	 * @param competitor
	 */
	public void subtractOdds(Competitor c) {
		int beforeOdd = this.odds.get(c);
		if (beforeOdd > 1)			
			this.odds.put(c, this.odds.get(c) - 1);
		else this.odds.put(c, beforeOdd);
	}
	
	public final void reactToMatch(Competitor winner, Competitor loser) {
		System.out.println(this.displayer.displayMessage(winner, loser));
	}
	
	public String displayOdds(Competitor winner, Competitor loser) {
		String msg;
		
		int winnerOddBefore = this.getOdds().get(winner);
		int loserOddBefore = this.getOdds().get(loser);
		
		this.addOdds(loser);
		this.subtractOdds(winner);
		
		int winnerOddAfter = this.getOdds().get(winner);
		int loserOddAfter = this.getOdds().get(loser);
		
		String msg1 = winner + " : " + winnerOddBefore + " -> " + winnerOddAfter + ". ";
		String msg2 = loser + " : " + loserOddBefore + " -> " + loserOddAfter + ". ";
		
		msg = msg1 + msg2;
				
		if (winnerOddAfter == loserOddAfter) {
			msg += winner + " and " + loser + " now have the same odd.";
		}
		
		if (winnerOddBefore == 1) {
			msg = winner + "'s odd remains 1. " + msg;
		}			
		
		return msg;
	}
	
	public String toString() {
		return this.name;
	}

}
