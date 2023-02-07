package competition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import competition.competition.Competition;
import competition.competition.NotPowerOfTwoException;
import competition.competitor.Competitor;
import mock.MockObserver;
import mock.MockTournament;

public class TournamentTest extends CompetitionTest {

	private MockTournament tournament;
	
	@Test
	public void shouldCallPlayMatchThreeTimeWhenFourCompetitors() throws NotPowerOfTwoException {
		tournament.play(this.competitors);
		
		Assertions.assertEquals(3, tournament.numberOfTimesPlayMatchCalled);
	}
	
	@Test
	public void shouldNeverPlayMatchWhenNoCompetitor() {
		List<Competitor> noCompetitor = new ArrayList<>();
		
		Assertions.assertThrows(NotPowerOfTwoException.class, () -> tournament.play(noCompetitor));
		Assertions.assertEquals(0, tournament.numberOfTimesPlayMatchCalled);
	}
	
	@Test
	public void createRightNumberOfMatches() {	
		// If we have n competitors, a tournament plays n-1 matches
		int numberOfCompetitors = competitors.size(); // Here, 4
		int numberOfMatches = numberOfCompetitors - 1; // Here, 3
		
		try {
			tournament.play(competitors);
		} catch (NotPowerOfTwoException e) {
			e.printStackTrace();
		}
		
		Assertions.assertEquals(numberOfMatches, tournament.numberOfTimesPlayMatchCalled);
	}
	
	@Test
	public void testPlayTournamentNotOkIfNumberOfPlayersIsNotPowerOfTwo() {
		// 3 players, not a power of two
		 Competitor c1 = new Competitor("c1");
		 Competitor c2 = new Competitor("c2");
		 Competitor c3 = new Competitor("c3");
		List<Competitor> competitors = List.of(c1, c2, c3);
		
		Assertions.assertThrows(NotPowerOfTwoException.class, () -> tournament.play(competitors));		
	}
	
	@Test
	public void testObserverReactToEveryMatch() throws NotPowerOfTwoException {		
		// If we have n-1 matches, the observers will be notified n(n-1) times.
		MockObserver observer = new MockObserver();
		
		tournament.addObserver(observer);
		
		tournament.play(this.competitors);
		
		int numberOfCompetitors = competitors.size();
		int numberOfMatches = numberOfCompetitors - 1;;
		
		Assertions.assertEquals(numberOfMatches, observer.numberOfTimesReactToMatchCalled);
	}
	
	protected Competition createCompetition() {
		this.tournament = new MockTournament(this.competitors);
		return this.tournament;
	}
	
}
