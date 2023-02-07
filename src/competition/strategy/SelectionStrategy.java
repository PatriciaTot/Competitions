package competition.strategy;

import java.util.List;
import competition.competition.League;
import competition.competitor.Competitor;

public interface SelectionStrategy {
	
	/** selects the competitors to compete in the final phase of the Master */
	public List<Competitor> selectFinalists(List<League> groups);	 

}
