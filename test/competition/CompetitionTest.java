package competition;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competition.Competition;
import competition.competitor.Competitor;
import competition.observer.BookMaker;
import competition.observer.MatchObserver;
import competition.observer.Reporter;
import competition.observer.UnknownObserverException;

public abstract class CompetitionTest {
	
	protected Competition competition;
	protected List<Competitor> competitors;
	
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
		this.competition = this.createCompetition();
	}
	
	@Test
	public void testGetObservers() {
		MatchObserver reporter = new Reporter(null);
		MatchObserver bookMaker = new BookMaker(null, this.competitors);
		List<MatchObserver> observersList = List.of(reporter, bookMaker);
		
		competition.addObserver(reporter);
		competition.addObserver(bookMaker);
		
		Assertions.assertEquals(observersList, competition.getObservers());
	}
	
	@Test
	public void testAddObserver() {
		Assertions.assertTrue(competition.getObservers().isEmpty());
		MatchObserver reporter = new Reporter(null);
		competition.addObserver(reporter);
		Assertions.assertTrue(competition.getObservers().contains(reporter));
		Assertions.assertTrue(competition.getCurrentMatch().getObservers().contains(reporter));
		Assertions.assertEquals(1, competition.getObservers().size());
		MatchObserver bookMaker = new BookMaker(null, this.competitors);
		competition.addObserver(bookMaker);
		Assertions.assertTrue(competition.getObservers().contains(bookMaker));
		Assertions.assertTrue(competition.getCurrentMatch().getObservers().contains(bookMaker));
		Assertions.assertEquals(2, competition.getObservers().size());
	}
	
	@Test
	public void testRemoveObserverOK() throws UnknownObserverException {
		MatchObserver reporter = new Reporter(null);
		competition.addObserver(reporter);
		Assertions.assertTrue(competition.getObservers().contains(reporter));
		Assertions.assertTrue(competition.getCurrentMatch().getObservers().contains(reporter));
		competition.removeObserver(reporter);
		Assertions.assertFalse(competition.getObservers().contains(reporter));
		Assertions.assertFalse(competition.getCurrentMatch().getObservers().contains(reporter));
	}
	
	@Test
	public void testRemoveObserverNotPresent() {
		MatchObserver reporter = new Reporter(null);
		Assertions.assertTrue(competition.getObservers().isEmpty());
		Assertions.assertThrows(UnknownObserverException.class, () -> competition.removeObserver(reporter));
	}
	
	@Test
	public void testGetCompetitors() {
		Assertions.assertEquals(this.createListCompetitors(), competition.getCompetitors());
	}
	
	@Test
	public void testGetPoints() {
		Map<Competitor, Integer> expectedPoints = new LinkedHashMap<>();
		expectedPoints.put(c1, 0);
		expectedPoints.put(c2, 0);
		expectedPoints.put(c3, 0);
		expectedPoints.put(c4, 0);
		
		Assertions.assertEquals(expectedPoints, competition.getPoints());
	}
	
	@Test
	public void testAddPoints() {
		Assertions.assertEquals(0, competition.getScoreOfCompetitor(c1));
		
		competition.addPoints(c1);
		
		Assertions.assertEquals(1, competition.getScoreOfCompetitor(c1));
	}
	
	@Test
	public void testgetScoreOfCompetitor() {
		for(Competitor c : this.competitors) {
			Assertions.assertEquals(0, competition.getScoreOfCompetitor(c));
		}
	}
	
	@Test
	public void testRanking() {
		// add 3 points to c1
		competition.addPoints(c1);
		competition.addPoints(c1);
		competition.addPoints(c1);
		Assertions.assertEquals(3, competition.getScoreOfCompetitor(c1));
		
		// add 2 points to c3
		competition.addPoints(c3);
		competition.addPoints(c3);
		Assertions.assertEquals(2, competition.getScoreOfCompetitor(c3));
		
		// add 1 points to c4
		competition.addPoints(c4);
		Assertions.assertEquals(1, competition.getScoreOfCompetitor(c4));
		
		// add 0 point to c2
		Assertions.assertEquals(0, competition.getScoreOfCompetitor(c2));
		
		Map<Competitor, Integer> expectedRanking = new LinkedHashMap<>();
		expectedRanking.put(c1, 3);
		expectedRanking.put(c3, 2);
		expectedRanking.put(c4, 1);
		expectedRanking.put(c2, 0);
				
		Assertions.assertEquals(expectedRanking, competition.ranking());
	}
	
	protected abstract Competition createCompetition();
}
