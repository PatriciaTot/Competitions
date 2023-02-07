package mock;
import competition.competitor.Competitor;
import competition.observer.MatchObserver;

public class MockObserver implements MatchObserver {
	
	public int numberOfTimesReactToMatchCalled = 0;
	
	@Override
	public void reactToMatch(Competitor winner, Competitor loser) {
		this.numberOfTimesReactToMatchCalled++;
	}

	@Override
	public String getName() {
		return "observer";
	}

}
