package mock;
import java.util.List;

import competition.competition.Tournament;
import competition.competitor.Competitor;

public class MockTournament extends Tournament {

	public MockTournament(List<Competitor> competitors) {
		super(competitors);
	}
	
	public int numberOfTimesPlayMatchCalled = 0;
	
	@Override
	public void playMatch(Competitor a, Competitor b) {
		this.numberOfTimesPlayMatchCalled++;
		super.playMatch(a,b);
	}

}
