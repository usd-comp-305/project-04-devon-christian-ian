package edu.sandiego.comp305;

import java.util.List;
import java.util.Map;

/**
 * Handles printing and summarizing final season results.
 */
public final class FinalResults {

    private FinalResults() {
    }

    /**
     * Prints all final results for the completed season.
     *
     * @param season the completed season
     */
    public static void print(final Season season) {
        printChampion(season);
        printStandings(season);
        printWeeklyResults(season);
        printTeamRosters(season);
    }

    /**
     * Prints the champion of the season.
     *
     * @param season the completed season
     */
    private static void printChampion(final Season season) {
        final Team champion = season.getChampion();

        System.out.println("\n=== Champion ===");

        if (champion == null) {
            System.out.println("No champion found");
        } else {
            System.out.println(champion.getName());
        }
    }

    /**
     * Prints the final standings based on total wins.
     *
     * @param season the completed season
     */
    private static void printStandings(final Season season) {
        System.out.println("\n=== Final Standings (Wins) ===");

        final Map<Team, Integer> standings = season.getStandings();

        for (final Map.Entry<Team, Integer> entry : standings.entrySet()) {
            System.out.println(entry.getKey().getName()
                    + ": " + entry.getValue() + " wins");
        }
    }

    /**
     * Prints the results of each scheduled week.
     *
     * @param season the completed season
     */
    private static void printWeeklyResults(final Season season) {
        System.out.println("\n=== Weekly Results ===");

        final List<Week> weeks = season.getWeeks();

        for (final Week eachWeek : weeks) {
            System.out.println("Week " + eachWeek.getWeekNumber());

            for (final Match eachMatch : eachWeek.getMatches()) {
                final MatchResults result = eachMatch.getWinner();

                switch (result) {
                    case PLAYER_ONE:
                        System.out.println("  Winner: Player One Team");
                        break;
                    case PLAYER_TWO:
                        System.out.println("  Winner: Player Two Team");
                        break;
                    case TIE:
                        System.out.println("  Result: Tie");
                        break;
                    default:
                        System.out.println("  Uh oh! Unknown result");
                }
            }
        }
    }

    /**
     * Prints each team's roster.
     *
     * @param season the completed season
     */
    private static void printTeamRosters(final Season season) {
        System.out.println("\n=== Team Rosters ===");

        for (final Team team : season.getStandings().keySet()) {
            System.out.println(team.getName() + ":");

            for (final Politician politician : team.getRoster()) {
                System.out.println("  - " + politician.getName()
                        + " (" + politician.getParty() + ")");
            }
        }
    }
}
