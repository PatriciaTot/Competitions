package competition.competition;

import java.util.ArrayList;
import java.util.List;
import competition.competitor.Competitor;
import competition.observer.MatchObserver;
import competition.observer.UnknownObserverException;

public class GroupStage extends Competition {

	private int numberOfGroups;
	private List<League> groups;

	public GroupStage(List<Competitor> competitors, int numberOfGroups) {
		super(competitors);
		this.numberOfGroups = numberOfGroups;
		this.groups = this.selectGroups(competitors);
	}
	
	/**
	 * return the number of groups playing in the first phase
	 * @return number
	 */
	public int getNumberOfGroups() {
		return this.numberOfGroups;
	}
	
	public List<League> getGroups() {
		return this.groups;
	}

	/**
	 * Divides the competitors into several teams
	 * @return the list of all the groups of the first phase
	 */
	public List<League> selectGroups(List<Competitor> competitors) {
		List<League> leagues = new ArrayList<>();
		
		// The number of groups to be created
		int numberOfCompetitorsByGroup = competitors.size() / this.numberOfGroups;
		
		for(int i = 0; i < this.numberOfGroups; i++) {
			List<Competitor> eachGroup = new ArrayList<>();
			for(int j = 0; j < numberOfCompetitorsByGroup; j++) {
				eachGroup.add(this.competitors.get(i * numberOfCompetitorsByGroup + j));
			}
			leagues.add(new League(eachGroup));
		}
		return leagues;
	}
	
	/**
	 * each group is a league
	 * @throws NotPowerOfTwoException
	 */
	public void play(List<Competitor> competitors) throws NotPowerOfTwoException {
		this.selectGroups(competitors);
		int groupNumber = 1;
		System.out.println("========================================== GROUP STAGE ==========================================\n");
		for(League league : this.groups) {
			System.out.print("\n");
			System.out.println("********************************************* GROUP " + groupNumber + " *********************************************\n");
			league.play();
			groupNumber++;
		}
		this.groupPhaseRanking();
	}
	
	/**
	 * Displays the ranking of each group
	 */
	public void groupPhaseRanking() {
		System.out.println("\n=========================================== GROUP RANKING ==========================================\n");
		int groupNumber = 1;
		for(League league : this.groups) {
			System.out.println("\n********************************************* GROUP " + groupNumber + " *********************************************\n");
			league.display();
			groupNumber++;
		}
	}
	
	@Override
	public void addObserver(MatchObserver observer) {
		this.observers.add(observer);
		for (League league : this.getGroups()) {
			league.addObserver(observer);
		}
	}
	
	@Override
	public void removeObserver(MatchObserver observer) throws UnknownObserverException {
		this.observers.remove(observer);
		for (League league : this.getGroups()) {
			league.removeObserver(observer);
		}
	} 
	
}
