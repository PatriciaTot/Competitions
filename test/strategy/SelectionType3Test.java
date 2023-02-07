package strategy;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competition.League;
import competition.competitor.Competitor;
import competition.strategy.SelectionType3;

public class SelectionType3Test {
	
	private List<League> groups =  this.createListLeague();
	
	@BeforeEach
	public void init() {
		this.groups = this.createListLeague();
	}

	private Competitor c1;
	private Competitor c2;
	private Competitor c3;
	private Competitor c4;
	private Competitor c5;
	private Competitor c6;
	private Competitor c7;
	private Competitor c8;
	
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
		SelectionType3 selection = new SelectionType3();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		// The number of finalists should be 4
		Assertions.assertEquals(4, finalists.size());
	}
	
	@Test
	public void testReturnTheRightListOfCompetitors() {
		SelectionType3 selection = new SelectionType3();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		List<Competitor> expectedFinalists = List.of(c2, c4, c6, c8);
		
		// The finalists should be c2, c4, c6 and c8
		Assertions.assertEquals(expectedFinalists, finalists);
	}

}
