package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.Reporter;

public class ReporterDisplayer2 extends ReporterDisplayer {

	public ReporterDisplayer2(Reporter reporter) {
		super(reporter);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String message(Competitor a, Competitor b) {
		String msg = this.reporter + " : " + a + " won the match over " + b + ".";
		return msg;
	}

}
