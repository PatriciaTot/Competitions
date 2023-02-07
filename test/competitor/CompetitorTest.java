package competitor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.competitor.Competitor;

public class CompetitorTest {

	private Competitor c1;
	private Competitor c2;

	@BeforeEach
	public void before() {
		this.c1 = new Competitor("Patricia");
		this.c2 = new Competitor("William");
	}

	@Test
	public void testGetName() {
		Assertions.assertEquals("Patricia", c1.getName());
		Assertions.assertEquals("William", c2.getName());
	}
	
	@Test
	public void testCompetitorWithSameNamesAreEqual() {
		Competitor c3 = new Competitor("Patricia");
		Assertions.assertTrue(c1.equals(c3));
	}
	
	@Test
	public void testCompetitorWithDifferentNamesAreNotEqual() {
		Assertions.assertFalse(c1.equals(c2));
	}

}
