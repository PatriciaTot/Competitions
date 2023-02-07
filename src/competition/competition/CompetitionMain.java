package competition.competition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import competition.competitor.Competitor;
import competition.displayer.BookMakerDisplayer1;
import competition.displayer.BookMakerDisplayer2;
import competition.displayer.Displayer;
import competition.displayer.ReporterDisplayer1;
import competition.displayer.ReporterDisplayer2;
import competition.observer.BookMaker;
import competition.observer.Reporter;

import static java.lang.Integer.parseInt;
import competition.strategy.*;

public class CompetitionMain {
	
	public static void main(String[] args) throws Exception {
		
		String choice = "";
		Competition competition = null;
		
		// We create arrays with the competitors' names
		String[] names = {"Portugal", "Cameroon", "Brazil", "France", "Netherlands", "Belgium", "Switzerland",
				"Serbia", "England", "Iran", "USA", "Wales", "Argentina", "Congo", "Mexico", "Poland", "Australia",
				 "Denmark", "Tunisia", "Spain", "Ecuador", "Costa Rica", "Germany", "Japan", "Senegal", 
				"Canada", "Morocco", "Croatia", "Ghana", "Qatar", "Uruguay", "South Korea"};
				
		List<Competitor> leagueOf4Competitors = new ArrayList<>();
		List<Competitor> tournamentOf4Competitors = new ArrayList<>();
		List<Competitor> masterOf16Competitors = new ArrayList<>();
		List<Competitor> masterOf24Competitors = new ArrayList<>();
		List<Competitor> masterOf32Competitors = new ArrayList<>();
		
		// We add each competitor to the league
		for(int i = 0; i < 4; i++) {
			leagueOf4Competitors.add(new Competitor(names[i]));
		}

		// We add each competitor to the tournament
		for(int i = 0; i < 4; i++) {
			tournamentOf4Competitors.add(new Competitor(names[i]));
		}
		
		// We add each competitor to the Master of 16 competitors
		for(int i = 0; i < 16; i++) {
			masterOf16Competitors.add(new Competitor(names[i]));
		}
		
		// We add each competitor to the Master of 24 competitors
		for(int i = 0; i < 24; i++) {
			masterOf24Competitors.add(new Competitor(names[i]));
		}
		
		// We add each competitor to the Master of 24 competitors
		for(int i = 0; i < 32; i++) {
			masterOf32Competitors.add(new Competitor(names[i]));
		}

		try (Scanner scanner = new Scanner(System.in)) {
			while (choice.equals("")) {
				System.out.println("Please, select a competition : ");
				System.out.println("Choice          1  :  \"League\"");
				System.out.println("                2  :  \"Master\"");
				System.out.println("                3  :  \"Tournament\"");		
				System.out.println("                4  :  \"Exit\"");
				String choix = scanner.nextLine();
				try {
					System.out.println("\n");
					switch (parseInt(choix)) {
						case 1:
							competition = new League(leagueOf4Competitors);
							choice = choix;
							break;
						case 2:
							choice = choix;
							break;
						case 3:
							competition = new Tournament(tournamentOf4Competitors);
							choice = choix;
							break;
						case 4:
							System.out.println("Exit");
							System.exit(-1);
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter a valid number.");
				}
			}
			
			
			if(choice.equals("2")) {
				System.out.println("Please, select a Master : \n");
				System.out.println("Choice          1  :  \"Master Type 1\"");
				System.out.println("                2  :  \"Master Type 2\"");
				System.out.println("                3  :  \"Master Type 3\"");
				System.out.println("                4  :  \"Master Type 4\"");
				System.out.println("                5  :  \"Exit\"");
				String choix = scanner.nextLine();
				try {
					System.out.println("\n");
					
					// the competitors are shuffled so that groups are created randomly
					Collections.shuffle(masterOf16Competitors);
					Collections.shuffle(masterOf24Competitors);
					
					switch (parseInt(choix)) {
						case 1:
							competition = new Master(masterOf16Competitors, new SelectionType1(), 4);
							choice = choix;
							break;
						
						case 2:
							competition = new Master(masterOf24Competitors, new SelectionType2(), 3);
							choice = choix;
							break;
						case 3:
							competition = new Master(masterOf32Competitors, new SelectionType3(), 4);
							choice = choix;
							break;
						case 4:
							competition = new Master(masterOf16Competitors, new SelectionType4(), 4);
							choice = choix;
							break;
						case 5:
							System.out.println("Exit");
							System.exit(-1);
					}
				}
				catch (NumberFormatException e) {
					System.out.println("Please enter a valid number.");
				}			
			}

		}
		
		// The reporters
		Reporter reporter1 = new Reporter("Canal +");
		Reporter reporter2 = new Reporter("Daniel Riolo");
		
		Displayer reporterDisplayer1 = new ReporterDisplayer1(reporter1);
		Displayer reporterDisplayer2 = new ReporterDisplayer2(reporter2);
		
		reporter1.setDisplayer(reporterDisplayer1);
		reporter2.setDisplayer(reporterDisplayer2);
		
		// The bookMakers
		BookMaker bookMaker1 = new BookMaker("Unibet", competition.getCompetitors());
		BookMaker bookMaker2 = new BookMaker("Winamax", competition.getCompetitors());
		
		
		Displayer bookMakerDisplayer1 = new BookMakerDisplayer1(bookMaker1);
		Displayer bookMakerDisplayer2 = new BookMakerDisplayer2(bookMaker2);
		
		bookMaker1.setDisplayer(bookMakerDisplayer1);
		bookMaker2.setDisplayer(bookMakerDisplayer2);
		
		competition.addObserver(reporter1);
		competition.addObserver(reporter2);
		competition.addObserver(bookMaker1);
		competition.addObserver(bookMaker2);
		
		if(competition instanceof League || competition instanceof Tournament) {
			competition.play();
			System.out.println("\n*** Ranking ***\n");
			competition.display();
		}
		
		else {
			competition.play();
		}
	}
}