package competition.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import competition.competition.League;
import competition.competitor.Competitor;

/** This strategy selects the last of each group for the final phase */

public class SelectionType3 implements SelectionStrategy {
	
	public List<Competitor> selectFinalists(List<League> groups) {
		List<Competitor> finalists = new ArrayList<>();
		
		for(League league : groups) {
			Map<Competitor, Integer> points = league.ranking();
			int count = 1;
			for(Map.Entry<Competitor, Integer> entry : points.entrySet()) {
				if(count == points.size()) {
					Competitor last = entry.getKey();
					finalists.add(last);
				}
				count++;
			}
		}
		return finalists;
	}

}