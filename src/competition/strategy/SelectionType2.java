package competition.strategy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import competition.competition.League;
import competition.competitor.Competitor;
import competition.util.MapUtil;

/** This strategy selects for the final phase only the first 2 of each group plus the best 2 thirds of all groups. */

public class SelectionType2 implements SelectionStrategy {
	
	public List<Competitor> selectFinalists(List<League> groups) {
		List<Competitor> finalists = new ArrayList<>();
		Map<Competitor, Integer> thirdCompetitors = new LinkedHashMap<>();
		
        for(League league : groups) {
        	Map<Competitor, Integer> points = league.ranking();
        	int i = 0;
        	for(Map.Entry<Competitor, Integer> entry : points.entrySet()) {
        		if (i < 3) {
        			if (i == 2) {
        				Competitor third = entry.getKey();
        				thirdCompetitors.put(third, points.get(third));
        				i++;
        			}
        			else {
        				finalists.add(entry.getKey());
        				i++;
        			}
        		}
        	}
        }
       
        int i = 0;
        Map<Competitor, Integer> thirdRanking = MapUtil.sortByDescendingValue(thirdCompetitors);
        for(Map.Entry<Competitor, Integer> entry : thirdRanking.entrySet()) {
    		if (i < 2) {
    			Competitor third = entry.getKey();
    			finalists.add(third);
    			i++;
    		}
        }
        
        return finalists;
	}
	
}