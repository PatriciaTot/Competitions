package mock;

import java.util.List;

import competition.competition.GroupStage;
import competition.competition.League;
import competition.competitor.Competitor;

public class MockGroupStage extends GroupStage {

	public MockGroupStage(List<Competitor> competitors, int numberOfGroups) {
		super(competitors, numberOfGroups);
	}
	
	public int numberOfTimesPlayMatchCalled = 0;
	
	@Override
	public void playMatch(Competitor a, Competitor b) {
		for (League league : this.getGroups()) {
			this.numberOfTimesPlayMatchCalled++;
			league.playMatch(a,b);
		}
	}

}
