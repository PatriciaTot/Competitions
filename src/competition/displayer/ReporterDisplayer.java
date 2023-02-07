package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.Reporter;

public abstract class ReporterDisplayer implements Displayer {
	
	protected Reporter reporter;
	
	public ReporterDisplayer(Reporter reporter) {
		this.reporter = reporter;
	}

	public String displayMessage(Competitor a, Competitor b) {
		return this.message(a, b);
	}

	protected abstract String message(Competitor a, Competitor b);

}
