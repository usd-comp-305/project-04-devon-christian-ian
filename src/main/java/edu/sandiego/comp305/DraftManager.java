package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the fantasy draft process for teams selecting politicians.
 */
public class DraftManager {

    /**
     * Number of weekly data buckets in one calendar year of trade data.
     */
    private static final int DATA_WEEKS_IN_YEAR = 52;

    /**
     * Pool of politicians eligible to be drafted.
     */
    private final List<Politician> availablePlayers;

    /**
     * Teams participating in the draft.
     */
    private final List<Team> teams;

    /**
     * Order in which teams pick players.
     */
    private final List<Team> draftOrder;

    /**
     * Maximum number of politicians per team.
     */
    private final int rosterLimit;

    /**
     * Strategy used to calculate draft-board scores.
     */
    private final ScoringStrategy scoringStrategy;

    /**
     * Reads user input during the draft.
     */
    private final Scanner scanner;

    /**
     * Creates a draft manager.
     *
     * @param players available politicians
     * @param teams teams participating in the draft
     * @param rosterLimit maximum politicians per team
     * @param scoringStrategy scoring strategy used to show yearly profit score
     */
    public DraftManager(
            final List<Politician> players,
            final List<Team> teams,
            final int rosterLimit,
            final ScoringStrategy scoringStrategy) {

        this.availablePlayers = new ArrayList<>(players);
        this.teams = new ArrayList<>(teams);
        this.draftOrder = new ArrayList<>(teams);
        this.rosterLimit = rosterLimit;
        this.scoringStrategy = scoringStrategy;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Randomizes the initial draft order.
     */
    public void generateDraftOrder() {
        Collections.shuffle(draftOrder);
    }

    /**
     * Runs a snake draft until every team reaches the roster limit.
     */
    public void runSnakeDraft() {
        generateDraftOrder();

        int round = 1;

        while (!allTeamsFull()) {
            List<Team> currentOrder = new ArrayList<>(draftOrder);

            if (round % 2 == 0) {
                Collections.reverse(currentOrder);
            }

            System.out.println("\n--- Draft Round " + round + " ---");

            for (Team team : currentOrder) {
                if (team.getRoster().size() < rosterLimit) {
                    runTurn(team);
                }
            }

            round++;
        }

        System.out.println("\nDraft complete!");
    }

    /**
     * Assigns a politician to a team and removes them from the draft pool.
     *
     * @param team team drafting the politician
     * @param politician politician being drafted
     */
    public void pickPlayer(
            final Team team,
            final Politician politician) {

        if (team == null || politician == null) {
            return;
        }

        if (team.getRoster().size() >= rosterLimit) {
            return;
        }

        if (!availablePlayers.contains(politician)) {
            return;
        }

        team.addPolitician(politician);
        availablePlayers.remove(politician);
    }

    /**
     * Runs one team's draft turn.
     *
     * @param team team making the pick
     */
    private void runTurn(final Team team) {
        boolean drafted = false;

        while (!drafted) {
            System.out.println("\n" + team.getName() + "'s pick");
            System.out.print("View politicians by party (ALL, DEM, REP): ");

            String filter = scanner.nextLine().trim().toUpperCase();

            printDraftBoard(filter);

            System.out.print("Enter politician ID to draft: ");

            try {
                int id = Integer.parseInt(scanner.nextLine());
                Politician selected = findAvailablePoliticianById(id);

                if (selected == null) {
                    System.out.println("Invalid pick. That politician is not available.");
                } else if (!matchesFilter(selected, filter)) {
                    System.out.println("That politician does not match the selected party filter.");
                } else {
                    pickPlayer(team, selected);
                    System.out.println(team.getName() + " drafted " + selected.getName());
                    drafted = true;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a valid politician ID.");
            }
        }
    }

    /**
     * Prints available politicians matching the chosen party filter.
     *
     * @param filter party filter entered by the user
     */
    private void printDraftBoard(final String filter) {
        System.out.println("\nAvailable Politicians:");

        for (Politician politician : availablePlayers) {
            if (matchesFilter(politician, filter)) {
                double yearlyProfitScore = calculateYearlyProfitScore(politician);

                System.out.println(
                        politician.getIdNumber()
                                + ". "
                                + politician.getName()
                                + " | "
                                + politician.getParty()
                                + " | Yearly Profit Score: "
                                + yearlyProfitScore);
            }
        }
    }

    /**
     * Calculates a politician's total score across one year of trade data.
     *
     * @param politician politician being scored
     * @return total yearly profit score
     */
    private double calculateYearlyProfitScore(final Politician politician) {
        double totalScore = 0.0;

        for (int week = 1; week <= DATA_WEEKS_IN_YEAR; week++) {
            totalScore += scoringStrategy.calculateScore(politician, week);
        }

        return totalScore;
    }

    /**
     * Checks whether a politician matches the selected party filter.
     *
     * @param politician politician being checked
     * @param filter selected filter
     * @return true if the politician matches the filter
     */
    private boolean matchesFilter(
            final Politician politician,
            final String filter) {

        if (filter.equals("ALL")) {
            return true;
        }

        return politician.getParty().equalsIgnoreCase(filter);
    }

    /**
     * Finds an available politician by ID number.
     *
     * @param id politician ID number
     * @return matching politician, or null if unavailable
     */
    private Politician findAvailablePoliticianById(final int id) {
        for (Politician politician : availablePlayers) {
            if (politician.getIdNumber() == id) {
                return politician;
            }
        }

        return null;
    }

    /**
     * Checks whether every team has reached the roster limit.
     *
     * @return true if every team is full
     */
    private boolean allTeamsFull() {
        for (Team team : teams) {
            if (team.getRoster().size() < rosterLimit) {
                return false;
            }
        }

        return true;
    }
}