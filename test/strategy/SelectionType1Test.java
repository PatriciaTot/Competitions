package strategy;
import java.util.List;

import competition.competition.League;
import competition.competitor.Competitor;
import competition.strategy.SelectionType1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SelectionType1Test {
	
	private List<League> groups =  this.createListLeague();

	private Competitor c1;
	private Competitor c2;
	private Competitor c3;
	private Competitor c4;
	private Competitor c5;
	private Competitor c6;
	private Competitor c7;
	private Competitor c8;
	
	@BeforeEach
	public void init() {
		this.groups = this.createListLeague();
	}
	
	private List<League> createListLeague() {
		c1 = new Competitor("c1");
		c2 = new Competitor("c2");
		c3 = new Competitor("c3");
		c4 = new Competitor("c4");
		c5 = new Competitor("c5");
		c6 = new Competitor("c6");
		c7 = new Competitor("c7");
		c8 = new Competitor("c8");
		
		League l1 = new League(List.of(c1, c2));
		League l2 = new League(List.of(c3, c4));
		League l3 = new League(List.of(c5, c6));
		League l4 = new League(List.of(c7, c8));
		
		return List.of(l1, l2, l3, l4);
	}
	
	@Test
	public void testCreateTheRightAmountOfFinalists() {
		SelectionType1 selection = new SelectionType1();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		// The number of finalists should be 4
		Assertions.assertEquals(4, finalists.size());
	}
	
	@Test
	public void testReturnTheRightListOfCompetitors() {
		SelectionType1 selection = new SelectionType1();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		List<Competitor> expectedFinalists = List.of(c1, c3, c5, c7);
		
		// The finalists should be c1, c3, c5 and c7
		Assertions.assertEquals(expectedFinalists, finalists);
	}
	
}
