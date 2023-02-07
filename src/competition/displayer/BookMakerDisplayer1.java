package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.BookMaker;

public class BookMakerDisplayer1 extends BookMakerDisplayer {
	
	public BookMakerDisplayer1(BookMaker bookMaker) {
		super(bookMaker);
	}

	protected String message(Competitor a, Competitor b) {
		String msg = this.bookMaker + " : " + a + "'s actual odd is " + this.bookMaker.getOdds().get(a) + " " + b + "'s actual odd is " + this.bookMaker.getOdds().get(b) + ".";
		return msg;
	}

}
