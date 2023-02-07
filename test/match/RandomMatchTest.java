package match;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import competition.competition.Competition;
import competition.competitor.Competitor;
import competition.match.AbstractMatch;
import competition.match.RandomMatch;

public class RandomMatchTest extends AbstractMatchTest {
	
	private RandomMatch randomMatch;
	private Competition competition;
	
	@Test
	public void testChooseWinner() {
		Competitor c1 = new Competitor("c1");
		Competitor c2 = new Competitor("c2");
		
		Competitor winner = randomMatch.chooseWinner(c1, c2);
		
		assertTrue(winner.equals(c1) || winner.equals(c2));
	}
	
	protected AbstractMatch createMatch() {
		this.randomMatch = new RandomMatch(competition);
		return this.randomMatch;
	}
	
}
