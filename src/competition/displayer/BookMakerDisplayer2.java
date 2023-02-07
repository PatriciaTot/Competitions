package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.BookMaker;

public class BookMakerDisplayer2 extends BookMakerDisplayer {
	
	public BookMakerDisplayer2(BookMaker bookMaker) {
		super(bookMaker);
	}

	protected String message(Competitor a, Competitor b) {
		String msg = this.bookMaker + " : " + "Odds have changed ! " + this.bookMaker.displayOdds(a, b);
		return msg;
	}
	
}
