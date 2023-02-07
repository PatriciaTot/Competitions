package competition.competition;

import java.util.ArrayList;
import java.util.List;

import competition.competitor.Competitor;
import competition.observer.MatchObserver;
import competition.observer.UnknownObserverException;
import competition.strategy.SelectionStrategy;

public class Master extends Competition {
	
	private SelectionStrategy strategy;
	protected GroupStage groupStage;
	protected int numberOfGroups;
	protected Tournament tournament;
	protected List<Competitor> finalists;

	public Master(List<Competitor> competitors, SelectionStrategy strategy, int numberOfGroups) {
		super(competitors);
		this.strategy = strategy;
		this.numberOfGroups = numberOfGroups;
		
		this.groupStage = new GroupStage(competitors, numberOfGroups);
		
		this.finalists = new ArrayList<>();
		this.tournament = new Tournament(this.finalists);
	}
	
	/**
	 * return the number of groups
	 */
	public SelectionStrategy getStrategy() {
		return this.strategy;
	}
	
	/**
	 * return the number of groups
	 */
	public int getNumberOfGroups() {
		return this.numberOfGroups;
	}

	public Tournament getTournament() {
		return this.tournament;
	}

	public List<Competitor> getFinalists() {
		return this.finalists;
	}

	/**
	 * changes the strategy
	 * @param strategy
	 */
	public void setStrategy(SelectionStrategy strategy) {
		this.strategy = strategy;
	}
	
	public GroupStage getGroupStage() {
		return this.groupStage;
	}
	
	/**
	 * @return All the group playing in the group stage
	 */
	public List<League> getGroups() {
		return this.groupStage.getGroups();
	}
	
	/**
	 * The final phase will take place in the form of a tournament
	 * The winner of the Master is the finishing competitor who wins the final phase.
	 * @param finalist competitors who have won the group phase
	 * @throws NotPowerOfTwoException
	 */
	public void play(List<Competitor> competitors) throws NotPowerOfTwoException {
		System.out.println("-----------------------------------------------------------------------------------------------------\n");
		
		System.out.println("\nThe finalists are : \n" + competitors + "\n");
		
		System.out.println("\n============================================ FINAL STAGE ============================================\n");
		
		this.tournament = new Tournament(competitors);
		for(MatchObserver observer : this.getObservers()) {
			this.tournament.addObserver(observer);
		}
		this.tournament.play();
		
		System.out.println("\n======================================= 🎉🎉 FINAL RANKING 🎉🎉 ======================================\n");
		tournament.display();
	}
	
	/**
	 * Organizes the players of the Master in several groups
	 * Each group plays a league
	 * Some competitors of each groups are selected, depending on the strategy of selection
	 * A ranking of the groups is made
	 * A ranking of the final phase is made
	 * The winner of the Master is the finishing competitor who wins the final phase.
	 */
	@Override
	public void play() throws NotPowerOfTwoException {
		this.groupStage.play(competitors);
		this.finalists = this.strategy.selectFinalists(this.getGroups());
		this.play(this.finalists);
	}
	
	@Override
	public void addObserver(MatchObserver observer) {
		super.addObserver(observer);
		this.groupStage.addObserver(observer);
	}
	
	@Override
	public void removeObserver(MatchObserver observer) throws UnknownObserverException {
		super.removeObserver(observer);
		this.groupStage.removeObserver(observer);
	}
	
}
