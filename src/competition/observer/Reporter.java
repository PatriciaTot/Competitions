package competition.observer;

import competition.competitor.Competitor;
import competition.displayer.Displayer;

public class Reporter implements MatchObserver {

	protected String name;
	protected Displayer displayer;
	
	public Reporter(String name){
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Displayer getDisplayer() {
		return this.displayer;
	}

	public void setDisplayer(Displayer displayer) {
		this.displayer = displayer;
	}

	public void reactToMatch(Competitor winner, Competitor loser) {		
		System.out.println(this.displayer.displayMessage(winner, loser));
	}
	
	public String toString() {
		return this.name;
	}

}
