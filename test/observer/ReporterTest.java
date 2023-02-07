package observer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import competition.displayer.Displayer;
import competition.displayer.ReporterDisplayer1;
import competition.observer.Reporter;

public class ReporterTest {
	
	protected Reporter reporter;
	
	@BeforeEach
	public void init() {
		this.reporter = new Reporter("TF1");
	}
	
	@Test
	public void testGetName() {
		Assertions.assertEquals("TF1", reporter.getName());
	}
	
	@Test
	public void testGetDisplayer() {
		Displayer displayer = new ReporterDisplayer1(reporter);
		reporter.setDisplayer(displayer);
		Assertions.assertEquals(displayer, reporter.getDisplayer());
	}
	
}
