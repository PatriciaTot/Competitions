package competition;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import competition.competition.Competition;
import competition.competition.League;
import competition.competition.NotPowerOfTwoException;
import competition.observer.MatchObserver;
import competition.observer.Reporter;
import competition.observer.UnknownObserverException;
import mock.MockGroupStage;
import mock.MockObserver;

public class GroupStageTest extends CompetitionTest {
	
	private MockGroupStage groupStage;

	@Test
	public void testCreateTwoGroupsOfTwoCompetitors() {
		List<League> groups = groupStage.selectGroups(this.competitors);

		for (League league : groups) {
			Assertions.assertEquals(2, league.getCompetitors().size());
		}
	}
	
	@Test
	public void testGetNumberOfGroups() {
		Assertions.assertEquals(2, groupStage.getNumberOfGroups());
	}
	
	@Override
	@Test
	public void testAddObserver() {
		Assertions.assertTrue(groupStage.getObservers().isEmpty());
		MatchObserver reporter = new Reporter(null);
		groupStage.addObserver(reporter);
		Assertions.assertTrue(groupStage.getObservers().contains(reporter));
		for (League league : groupStage.getGroups()) {
			Assertions.assertTrue(league.getObservers().contains(reporter));
		}
	}
	
	@Override
	@Test
	public void testRemoveObserverOK() throws UnknownObserverException {
		MatchObserver reporter = new Reporter(null);
		groupStage.addObserver(reporter);
		Assertions.assertTrue(groupStage.getObservers().contains(reporter));
		groupStage.removeObserver(reporter);
		Assertions.assertFalse(groupStage.getObservers().contains(reporter));
		for (League league : groupStage.getGroups()) {
			Assertions.assertFalse(league.getObservers().contains(reporter));
		}
	}
	
	@Test
	public void testObserverReactToEveryMatch() throws NotPowerOfTwoException {
		int numberOfCompetitors = this.competitors.size();
		int numberOfGroups = groupStage.getNumberOfGroups();
		// in each group of the group stage, there are numberOfCompetitorsByGroup competitors
		int numberOfCompetitorsByGroup = numberOfCompetitors / numberOfGroups;
		
		// the number of matches in the group stage
		int numberOfMatches = numberOfGroups * (numberOfCompetitorsByGroup * (numberOfCompetitorsByGroup - 1));
		
		MockObserver observer = new MockObserver();
		
		groupStage.addObserver(observer);
		
		groupStage.play(this.competitors);
		
		Assertions.assertEquals(numberOfMatches, observer.numberOfTimesReactToMatchCalled);
	}


	@Override
	protected Competition createCompetition() {
		this.groupStage = new MockGroupStage(this.competitors, 2);
		return this.groupStage;
	}
	
}
