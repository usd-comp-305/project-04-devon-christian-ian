package edu.sandiego.comp305;

import java.util.List;

/**
 * Simulator class for the application.
 */
public final class Simulator {

    private static final String CSV_PATH =
            "src/main/resources/capitol_trades_final.csv";

    /**
     *
     */
    private Simulator() {
    }

    /**
     * Runs the application.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        // Load CSV
        final TradeScrubber tradeScrubber = new TradeScrubber();

        // Build politicians
        final PoliticianFactory factory =
                new PoliticianFactory(tradeScrubber);
        final List<Politician> politicians =
                factory.createPoliticianList(CSV_PATH);

        // Create starter teams
        final List<Team> teams = List.of(new Team("Team One"),
                new Team("Team Two"));

        // Run draft
        final DraftManager draftManager =
                new DraftManager(politicians, teams, 5);
        draftManager.generateDraftOrder();
        draftManager.runSnakeDraft();

        // Create scoring strategy
        final ScoringStrategy strategy = new PortfolioProfitStrategy();

        // Create season
        final Season season = new Season(teams);

        // Generate schedule
        season.generateSchedule();

        // Run season
        season.runSeason(strategy);

        // Print results
        FinalResults.print(season);
    }

    /**
     * Prints the season results.
     *
     * @param season the completed season
     */
    private static void printResults(final Season season) {
        final Team champion = season.getChampion();

        if (champion == null) {
            System.out.println("No champion found");
            return;
        }

        System.out.println("Champion: " + champion.getName());
    }

}
