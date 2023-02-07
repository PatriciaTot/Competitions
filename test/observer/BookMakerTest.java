package observer;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competitor.Competitor;
import competition.displayer.BookMakerDisplayer1;
import competition.displayer.Displayer;
import competition.observer.BookMaker;

public class BookMakerTest {
	
	protected BookMaker bookMaker;
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
		this.bookMaker = new BookMaker("Unibet", this.competitors);
	}
	
	@Test
	public void testgetOddOfCompetitor() {
		for(Competitor c : this.competitors) {
			Assertions.assertTrue(bookMaker.getOdds().get(c) >= 1 && bookMaker.getOdds().get(c) <= 5);
		}
	}
	
	@Test
	public void testGetName() {
		Assertions.assertEquals("Unibet", bookMaker.getName());
	}
	
	@Test
	public void testAddOdds() {
		Integer oddBefore = bookMaker.getOdds().get(this.c1);
		
		bookMaker.addOdds(c1);
		
		Assertions.assertEquals(1, bookMaker.getOdds().get(c1) - oddBefore);
	}
	
	@Test
	public void testsubtractOdds() {
		Integer oddBefore = bookMaker.getOdds().get(this.c2);
		
		bookMaker.subtractOdds(c2);
		
		if (oddBefore > 1)
			Assertions.assertEquals(1, oddBefore - bookMaker.getOdds().get(c2));
		else
			Assertions.assertEquals(0, oddBefore - bookMaker.getOdds().get(c2));
	}
	
	@Test
	public void testGetDisplayer() {
		Displayer displayer = new BookMakerDisplayer1(bookMaker);
		bookMaker.setDisplayer(displayer);
		Assertions.assertEquals(displayer, bookMaker.getDisplayer());
	}
	
}
