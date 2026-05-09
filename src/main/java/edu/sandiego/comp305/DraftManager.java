package edu.sandiego.comp305;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the fantasy draft process for teams selecting politicians.
 */
public class DraftManager {

    private final List<Politician> availablePlayers;

    private final List<Team> teams;

    private final List<Team> draftOrder;

    private final int rosterLimit;

    private final Scanner scanner;

    /**
     * Creates a draft manager.
     *
     * @param players available politicians
     * @param teams teams participating in the draft
     * @param rosterLimit maximum politicians per team
     */
    public DraftManager(
            final List<Politician> players,
            final List<Team> teams,
            final int rosterLimit) {
        this.availablePlayers = new ArrayList<>(players);
        this.teams = new ArrayList<>(teams);
        this.draftOrder = new ArrayList<>(teams);
        this.rosterLimit = rosterLimit;
        this.scanner = new Scanner(System.in, StandardCharsets.UTF_8);
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
            final List<Team> currentOrder = getDraftOrderForRound(round);
            System.out.println("\n--- Draft Round " + round + " ---");

            for (final Team team : currentOrder) {
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
    public void pickPlayer(final Team team, final Politician politician) {
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
     * Gets the draft order for a specific round.
     *
     * @param round current draft round
     * @return draft order for the round
     */
    private List<Team> getDraftOrderForRound(final int round) {
        final List<Team> currentOrder = new ArrayList<>(draftOrder);

        if (round % 2 == 0) {
            Collections.reverse(currentOrder);
        }

        return currentOrder;
    }

    /**
     * Runs one team's draft turn.
     *
     * @param team team making the pick
     */
    private void runTurn(final Team team) {
        boolean drafted = false;

        while (!drafted) {
            printTurnHeader(team);

            final String filter = readPartyFilter();
            printDraftBoard(filter);

            final Integer selectedId = readPoliticianId();

            if (selectedId != null) {
                drafted = tryDraftPolitician(team, selectedId, filter);
            }
        }
    }

    /**
     * Prints the header for one team's turn.
     *
     * @param team team making the pick
     */
    private void printTurnHeader(final Team team) {
        System.out.println("\n" + team.getName() + "'s pick");
    }

    /**
     * Reads the user's party filter.
     *
     * @return selected party filter
     */
    private String readPartyFilter() {
        System.out.print("View politicians by party (ALL, DEM, REP): ");
        return scanner.nextLine().trim().toUpperCase();
    }

    /**
     * Reads the politician ID entered by the user.
     *
     * @return politician ID, or null if invalid input
     */
    private Integer readPoliticianId() {
        System.out.print("Enter politician ID to draft: ");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException exception) {
            System.out.println(
                    "Invalid input. Please enter a valid politician ID.");
            return null;
        }
    }

    /**
     * Attempts to draft the selected politician.
     *
     * @param team team making the pick
     * @param id selected politician ID
     * @param filter selected party filter
     * @return true if the pick was successful
     */
    private boolean tryDraftPolitician(
            final Team team,
            final int id,
            final String filter) {
        final Politician selected = findAvailablePoliticianById(id);

        if (selected == null) {
            System.out.println(
                    "Invalid pick. That politician is not available.");
            return false;
        }

        if (!matchesFilter(selected, filter)) {
            System.out.println(
                    "That politician does not match the selected filter.");
            return false;
        }

        pickPlayer(team, selected);
        System.out.println(team.getName() + " drafted " + selected.getName());
        return true;
    }

    /**
     * Prints available politicians matching the chosen party filter.
     *
     * @param filter party filter entered by the user
     */
    private void printDraftBoard(final String filter) {
        System.out.println("\nAvailable Politicians:");
        System.out.println("ID | Name | Party");

        for (final Politician politician : availablePlayers) {
            if (matchesFilter(politician, filter)) {
                System.out.println(
                        politician.getIdNumber()
                                + " | "
                                + politician.getName()
                                + " | "
                                + politician.getParty());
            }
        }
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
        for (final Politician politician : availablePlayers) {
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
        for (final Team team : teams) {
            if (team.getRoster().size() < rosterLimit) {
                return false;
            }
        }

        return true;
    }
}





