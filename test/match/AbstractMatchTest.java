package match;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competition.Competition;
import competition.competition.League;
import competition.competitor.Competitor;
import competition.match.AbstractMatch;
import competition.observer.BookMaker;
import competition.observer.MatchObserver;
import competition.observer.Reporter;
import competition.observer.UnknownObserverException;

public abstract class AbstractMatchTest {
	
	protected AbstractMatch match;
	protected List<Competitor> competitors;
	protected Competition competition;
	
	protected Competitor c1;
	protected Competitor c2;
	protected Competitor c3;
	protected Competitor c4;
	 
	private List<Competitor> createListCompetitors() {
		c1 = new Competitor("c1");
		c2 = new Competitor("c2");
		c3 = new Competitor("c3");
		c4 = new Competitor("c4");
		return List.of(c1, c2, c3, c4);
	}
	
	@BeforeEach
	public void init() {
		this.competitors = createListCompetitors();
		this.competition = new League(this.competitors);
		this.match = this.createMatch();
	}

	@Test
	public void testGetObservers() {
		MatchObserver reporter1 = new Reporter(null);
		MatchObserver reporter2 = new Reporter(null);
		List<MatchObserver> observersList = List.of(reporter1, reporter2);
		
		match.register(reporter1);
		match.register(reporter2);
		
		Assertions.assertEquals(observersList, match.getObservers());
	}
	
	@Test
	public void testRegisterObserver() {
		Assertions.assertTrue(match.getObservers().isEmpty());
		MatchObserver reporter = new Reporter(null);
		match.register(reporter);
		Assertions.assertTrue(match.getObservers().contains(reporter));
		Assertions.assertEquals(1, match.getObservers().size());
		MatchObserver bookMaker = new BookMaker(null, this.competitors);
		match.register(bookMaker);
		Assertions.assertTrue(match.getObservers().contains(bookMaker));
		Assertions.assertEquals(2, match.getObservers().size());
	}
	
	@Test
	public void testUnregisterObserverOK() throws UnknownObserverException {
		MatchObserver reporter = new Reporter(null);
		match.register(reporter);
		Assertions.assertTrue(match.getObservers().contains(reporter));
		match.unregister(reporter);
		Assertions.assertFalse(match.getObservers().contains(reporter));
	}
		
	protected abstract AbstractMatch createMatch();

}
