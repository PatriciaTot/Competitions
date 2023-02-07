package competition.competition;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import competition.competitor.Competitor;
import competition.match.AbstractMatch;
import competition.match.RandomMatch;
import competition.observer.MatchObserver;
import competition.observer.UnknownObserverException;
import competition.util.MapUtil;

/** A sport competition is defined as matches played between competitors. */
public abstract class Competition {
	
	protected List<Competitor> competitors;
	protected Map<Competitor, Integer> points;
	protected AbstractMatch currentMatch;
	protected List<MatchObserver> observers;
	
	// constructor
	public Competition(List<Competitor> competitors) {
		this.competitors = competitors;
		
		this.currentMatch = new RandomMatch(this);
		
		this.points = new LinkedHashMap<>();		
		// At the beginning, all the competitors have 0 point
		competitors.stream().forEach(c -> this.points.put(c, 0));
		
		this.observers = new ArrayList<>();
	}

	/**
	 * All the competitors
	 * @return list of competitors
	 */
	public List<Competitor> getCompetitors() {
		return this.competitors;
	}
	
	/**
	 * All the observers
	 * @return
	 */
	public List<MatchObserver> getObservers() {
		return this.observers;
	}
	
	/**
	 * All the points of the competitors
	 * @return points
	 */
	public Map<Competitor, Integer> getPoints() {
		return this.points;
	}
	
	/**
	 * the match played
	 * @return
	 */
	public AbstractMatch getCurrentMatch() {
		return this.currentMatch;
	}
	
	/**
	 * changes the match
	 * @param abstractMatch
	 */
	public void setCurrentMatch(AbstractMatch abstractMatch) {
	    this.currentMatch = abstractMatch;
	}
	
	/**
	 * add points to the competitor
	 * @param competitor
	 */
	public void addPoints(Competitor competitor) {
		this.points.put(competitor, this.points.get(competitor) + 1);
	}
	
	/**
	 * return the score of the competitor
	 * @param competitor
	 * @return points
	 */
	public Integer getScoreOfCompetitor(Competitor competitor) {
		return this.points.get(competitor);
	}
	
	/**
	 * Organizes the players of the competition in several matches
	 * Each match is played
	 * A competition is finished when all its matches have been played
	 */
	public void play() throws NotPowerOfTwoException {
		this.play(this.competitors);
	}
	
	public void display() {
		Map<Competitor, Integer> score = this.ranking();
		for (Competitor c : score.keySet())
			System.out.println(c + " - " + score.get(c));
	}

	/**
	 * establishes the ranking of the competition to display the competitors and their number of points 
	 * @return the score of each competitor
	 */
	public Map<Competitor, Integer> ranking() {
		return MapUtil.sortByDescendingValue(this.points);
	}
	
	/**
	 * plays a match between two competitors
	 */
	public void playMatch(Competitor a, Competitor b) {
	    this.currentMatch.playMatch(a,b);
	    System.out.println("------------------------------------------------------------------------------------------------------\n");
	}
	
	public void addObserver(MatchObserver observer) {
		this.observers.add(observer);
		this.currentMatch.register(observer);
	}
	
	public void removeObserver(MatchObserver observer) throws UnknownObserverException {
		this.observers.remove(observer);
		this.currentMatch.unregister(observer);
	}
	
	public abstract void play(List<Competitor> competitors) throws NotPowerOfTwoException;
	
}
