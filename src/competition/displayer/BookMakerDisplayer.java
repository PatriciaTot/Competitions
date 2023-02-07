package competition.displayer;

import competition.competitor.Competitor;
import competition.observer.BookMaker;

public abstract class BookMakerDisplayer implements Displayer {
	
	protected BookMaker bookMaker;
	
	public BookMakerDisplayer(BookMaker bookMaker) {
		this.bookMaker = bookMaker;
	}
	
	public String displayMessage(Competitor a, Competitor b) {
		return this.message(a, b);
	}

	protected abstract String message(Competitor a, Competitor b);

}
