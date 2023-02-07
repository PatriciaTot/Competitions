package competition.observer;

import competition.competitor.Competitor;

/** Competitions are now so popular that the media and gamblers are interested in them. */

public interface MatchObserver {
	
	/**
	 * The reporter gives the name of the winner and the loser. Then their score at the end of the match.
	 */
	public void reactToMatch(Competitor winner, Competitor loser);
	
	public String getName();
	
}
