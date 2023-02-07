package competition.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import competition.competition.League;
import competition.competitor.Competitor;

/** This strategy only keeps the best of each group for the second phase */

public class SelectionType1 implements SelectionStrategy {

	public List<Competitor> selectFinalists(List<League> groups) {
		List<Competitor> finalists = new ArrayList<>();
		
		for(League league : groups) {
			Map<Competitor, Integer> points = league.ranking();
			Iterator<Competitor> iterator = points.keySet().iterator();
			Competitor first = iterator.next();
			finalists.add(first);
		}
		
		return finalists;
	}
	
}