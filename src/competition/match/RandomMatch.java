package competition.match;

import competition.competitor.Competitor;
import competition.competition.Competition;
import java.util.Random;

/** In this class, the winner of a match is obtained randomly */
public class RandomMatch extends AbstractMatch {

	public RandomMatch(Competition competition) {
		super(competition);
	}

	/**
	 * A simple random generator to choose the winner between the two competitors
	 */
	public Competitor chooseWinner(Competitor a, Competitor b) {
		Random alea = new Random();
		return alea.nextDouble() <= 0.5 ? a : b;
	}
	
}
