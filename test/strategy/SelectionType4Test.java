package strategy;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competition.League;
import competition.competitor.Competitor;
import competition.strategy.SelectionType4;

public class SelectionType4Test {
	
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
		
		League l1 = new League(List.of(c1, c2, c3, c4));
		League l2 = new League(List.of(c5, c6, c7, c8));
		
		return List.of(l1, l2);
	}
	
	@Test
	public void testCreateTheRightAmountOfFinalists() {
		SelectionType4 selection = new SelectionType4();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		// The number of finalists should be 4
		Assertions.assertEquals(4, finalists.size());
	}
	
	@Test
	public void testReturnTheRightListOfCompetitors() {
		SelectionType4 selection = new SelectionType4();
		List<Competitor> finalists = selection.selectFinalists(this.groups);
		
		List<Competitor> expectedFinalists = List.of(c1, c2, c5, c6);
		
		// The finalists should be c1, c2, c5, c6
		Assertions.assertEquals(expectedFinalists, finalists);
	}

}
