package mock;

import java.util.List;

import competition.competition.Master;
import competition.competitor.Competitor;
import competition.strategy.SelectionStrategy;

public class MockMaster extends Master {

	public MockMaster(List<Competitor> competitors, SelectionStrategy strategy, int numberOfGroups) {
		super(competitors, strategy, numberOfGroups);
	}
	
	public int numberOfTimesPlayMatchCalled = 0;
	
	@Override
	public void playMatch(Competitor a, Competitor b) {
		this.numberOfTimesPlayMatchCalled++;
		super.playMatch(a,b);
	}
}
