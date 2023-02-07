package competition.match;

import java.util.ArrayList;
import java.util.List;
import competition.competition.Competition;
import competition.competitor.Competitor;
import competition.observer.MatchObserver;
import competition.observer.UnknownObserverException;

/** There can be different types of matches. */
public abstract class AbstractMatch implements ObservableMatch {

	protected Competition competition;
	
	protected Competitor winner;
	protected Competitor loser;
	
	protected List<MatchObserver> matchObservers;
	

	public AbstractMatch(Competition competition) {
		this.competition = competition;
		
		this.matchObservers = new ArrayList<>();
	}	

	public Competition getCompetition() {
		return this.competition;
	}
	
	public Competitor getWinner() {
		return this.winner;
	}
	
	public Competitor getLoser() {
		return this.loser;
	}
	
	/**
	 * All the observers
	 * @return
	 */
	public List<MatchObserver> getObservers() {
		return this.matchObservers;
	}
	
	/**
	 * A winner is chosen and earns points.
	 * A description of the match is displayed.
	 * @param a one competitor
	 * @param b another competitor
	 */
	public void playMatch(Competitor a, Competitor b) {
		if (this.chooseWinner(a, b).equals(a)) {
			this.winner = a;
			this.loser = b;
		}
		else {
			this.winner = b;
			this.loser = a;
		}
		
		this.competition.addPoints(this.winner);
		
		this.displayMatch(a, b);
		
		this.notifyObservers();
	}
	
	public void displayMatch(Competitor a, Competitor b) {
		System.out.println(a + " vs " + b + " --> " + this.winner + " wins!");
	}
	
	public synchronized void register(MatchObserver matchObserver) {
		if( !this.matchObservers.contains(matchObserver) )
			this.matchObservers.add(matchObserver);
	}
	
	public synchronized void unregister(MatchObserver matchObserver) throws UnknownObserverException {
		if ( !this.matchObservers.contains(matchObserver))
			throw new UnknownObserverException("Observer not present.");
		
		this.matchObservers.remove(matchObserver);
	}
	
	/**
	 * Journalists can therefore attend the competitions and broadcast the results of the games
	 * BookMakers change the odds of the competitors according to the results of the competition depending on the results of the competition's matches
	 */
	public synchronized void notifyObservers() {
		for (MatchObserver matchObserver : this.getObservers()) {
			matchObserver.reactToMatch(this.winner, this.loser);
		}
	}
	
	public abstract Competitor chooseWinner(Competitor a, Competitor b);
	
}
