package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.Reporter;

public class ReporterDisplayer1 extends ReporterDisplayer {

	public ReporterDisplayer1(Reporter reporter) {
		super(reporter);
	}

	protected String message(Competitor a, Competitor b) {
		String msg = this.reporter + " : " + "What a wonderful victory for " + a + " ! " + b + " is au fond du trou !";
		return msg;
	}

}
