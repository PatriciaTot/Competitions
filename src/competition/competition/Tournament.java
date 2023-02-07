package competition.competition;

import java.util.ArrayList;
import java.util.List;
import competition.competitor.Competitor;

/** Direct elimination tournaments are held over several rounds. */
public class Tournament extends Competition {

	public Tournament(List<Competitor> competitors) {
		super(competitors);
	}
	
	/**
	 * Plays each round of the tournament
	 * Only competitors who have won their match in one round participate in the next round.
	 * @param competitors
	 * @return the list of the finalists of this round
	 */
	public List<Competitor> playOneRound(List<Competitor> competitors) {
		// A temporary list to keep the winner of each match
		List<Competitor> temporary = new ArrayList<>();

		for(int i = 0; i < competitors.size() - 1; i += 2) {
			Competitor c1 = competitors.get(i);
			Competitor c2 = competitors.get(i+1) ;
			this.playMatch(c1, c2);
			// The winner is the player with the highest score
			Competitor winner = this.points.get(c1) >= this.points.get(c2) ? c1 : c2;
			// We add the winner to the temporary list
			temporary.add(winner);
		}
		return temporary;
	}

	/**
	 * when the tournament can be played
	 * The winners of each match play each other until only one is left, and then the next round begins.
	 * @param competitors
	 */
	public void playTournament(List<Competitor> competitors) {
		if ( powerOfTwo(competitors.size()) ) {
			List<Competitor> provisory = this.playOneRound(competitors);
			playTournament(provisory);
		}
	}

	/**
	 *  organizes the tournament in both cases, if it can be played or not
	 * @throws NotPowerOfTwoException if the number of player is not a power of two
	 */
	public void play(List<Competitor> competitors) throws NotPowerOfTwoException {
		if ( !powerOfTwo(competitors.size()))
			throw new NotPowerOfTwoException("The number of players should be a power of two(2).");
		playTournament(competitors);
	}

	public static boolean powerOfTwo(int n) {
		if (n == 0)
			return false;

		while(n != 1) {
			if (n % 2 != 0)
				return false;
			n = n/2;
		}

		return true;
	}
	
	public String toString() {
		return "tournament";
	}	
	
}
