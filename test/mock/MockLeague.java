package mock;
import java.util.List;

import competition.competition.League;
import competition.competitor.Competitor;

public class MockLeague extends League {
	
	public MockLeague(List<Competitor> competitors) {
		super(competitors);
	}

	public int numberOfTimesPlayMatchCalled = 0;
	
	@Override
	public void playMatch(Competitor a, Competitor b) {
		this.numberOfTimesPlayMatchCalled++;
		super.playMatch(a,b);
	}
	
}
