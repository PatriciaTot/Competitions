package competition.competition;

import java.util.List;
import competition.competitor.Competitor;

/** Leagues are played in two-legged matches */
public class League extends Competition {

	public League(List<Competitor> competitors) {
		super(competitors);
	}
	
	/**
	 * Organizes the teams. Each competitor meets twice each other competitor of the league.
	 * @param competitors
	 */
	public void play(List<Competitor> competitors) {
		for(Competitor a : competitors) {
			for(Competitor b : competitors) {
				if ( !(a.equals(b)) ) {
					this.playMatch(a,b);
				}
			}
		}
	}
	
	public String toString() {
		return "league";
	}
	
}
