package competition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import competition.competition.Competition;
import competition.competition.NotPowerOfTwoException;
import competition.strategy.SelectionType1;
import mock.MockMaster;
import mock.MockObserver;

public class MasterTest extends CompetitionTest {
	
	private MockMaster master;
	
	@Test
	public void testCheckNumberOfGroupsCorrect() {
		int numberOfGroupsInMaster = master.getNumberOfGroups();
		master.getGroupStage().selectGroups(this.competitors);
		int numberOfGroupsInGroupPhase = master.getGroupStage().getNumberOfGroups();
		
		Assertions.assertEquals(numberOfGroupsInMaster, numberOfGroupsInGroupPhase);
	}
	
	@Test
	public void testObserverReactToEveryMatch() throws NotPowerOfTwoException {
		MockObserver observer = new MockObserver();
		
		master.addObserver(observer);
		
		// in each group of the group stage, there are numberOfCompetitorsByGroup competitors
		int numberOfCompetitorsByGroup = this.competitors.size() / master.getNumberOfGroups();
		
		// the number of matches in the group stage
		int numberOfMatchesInGroupStage = master.getNumberOfGroups() * (numberOfCompetitorsByGroup * (numberOfCompetitorsByGroup - 1));
		
		master.play();
		
		// the number of competitors in the final stage
		int numberOfCompetitorsInFinalStage = master.getFinalists().size();
		
		// the number of matches in the final stage
		int numberOfMatchesInFinalStage = numberOfCompetitorsInFinalStage - 1;

		int numberOfMatches = numberOfMatchesInGroupStage + numberOfMatchesInFinalStage;
		
		Assertions.assertEquals(numberOfMatches, observer.numberOfTimesReactToMatchCalled);
	}
	
	@Override
	protected Competition createCompetition() {
		this.master = new MockMaster(this.competitors, new SelectionType1(), 2);
		return this.master;
	}

}
