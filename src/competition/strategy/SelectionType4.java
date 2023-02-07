package competition.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import competition.competition.League;
import competition.competitor.Competitor;

/** This strategy selects the top two from each group */

public class SelectionType4 implements SelectionStrategy {

	@Override
	public List<Competitor> selectFinalists(List<League> groups) {
		List<Competitor> finalists = new ArrayList<>();
		
		int numberOfCompetitorsPerLeague = 2;
		for(League league : groups) {
        	Map<Competitor, Integer> points = league.ranking();
        	int i = 0;
        	for(Map.Entry<Competitor, Integer> entry : points.entrySet()) {
        		if (i < numberOfCompetitorsPerLeague) {
        			finalists.add(entry.getKey());
        			i++;
        		}
        	}
		}
		return finalists;
	}

}
