package competition;
import competition.competition.Competition;
import competition.competitor.Competitor;
import mock.MockLeague;
import mock.MockObserver;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LeagueTest extends CompetitionTest {
	
	private MockLeague league;
	
	@Test
	public void testShouldCallPlayMatchTwelveTimesWhenFourCompetitors() {
		league.play(competitors);
		
		Assertions.assertEquals(12, league.numberOfTimesPlayMatchCalled);
	}
	
	
	@Test
	public void testShouldNeverPlayMatchWhenNoCompetitor() {
		List<Competitor> noCompetitor = new ArrayList<>();
		
		league.play(noCompetitor);
		
		Assertions.assertEquals(0, league.numberOfTimesPlayMatchCalled);
	}
	
	@Test
	public void createRightNumberOfMatches() {		
		// If we have n competitors, a league plays n(n-1) games
		int numberOfCompetitors = competitors.size(); // Here, 4
		int numberOfMatches = numberOfCompetitors * (numberOfCompetitors - 1); // Here, 12
		
		league.play(competitors);
		
		Assertions.assertEquals(numberOfMatches, league.numberOfTimesPlayMatchCalled);
	}
	
	@Test
	public void testObserverReactToEveryMatch() {		
		// If we have n(n-1) matches, the observers will be notified n(n-1) times.
		MockObserver observer = new MockObserver();
		
		league.addObserver(observer);
		
		league.play(this.competitors);
		
		int numberOfCompetitors = competitors.size();
		int numberOfMatches = numberOfCompetitors * (numberOfCompetitors - 1);
		
		Assertions.assertEquals(numberOfMatches, observer.numberOfTimesReactToMatchCalled);
	}
	
	@Override
	protected Competition createCompetition() {
		this.league = new MockLeague(this.competitors);
		return this.league;
	}

}
