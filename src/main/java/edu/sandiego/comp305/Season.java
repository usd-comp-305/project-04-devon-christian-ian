package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A season of scheduled matchups between teams
 */
public class Season {

    private final Map<Team, Integer> standings;

    private final List<Team> teams;

    private final List<Week> weeks;

    /**
     * Creates a season with all teams starting at zero
     *
     * @param teams the teams ing the season
     */
    public Season(final List<Team> teams) {
        standings = new HashMap<>();
        this.teams = new ArrayList<>(teams);
        this.weeks = new ArrayList<>();

        for (final Team team : teams) {
            standings.put(team, 0);
        }
    }

    /**
     * Generates the season schedule
     */
    public void generateSchedule() {
        if (teams.size() < 2 || !weeks.isEmpty()) {
            return;
        }
        // Create 12 weeks, each with one match between the two teams
        for (int weekNumber = 1; weekNumber <= 12; weekNumber++) {
            final List<Match> matches = new ArrayList<>();
            matches.add(new Match(teams.get(0), teams.get(1)));
            weeks.add(new Week(weekNumber, matches));
        }
    }

    /**
     * Runs each schedule week using the provided scoring strategy
     *
     * @param strategy the scoring strategy used to simulate each week
     */
    public void runSeason(final ScoringStrategy strategy) {
        for (final Week week : weeks) {
            week.simulateWeek(strategy);

            for (Match weeklyMatch: week.getMatches()) {
                updateStandings(weeklyMatch);
            }
        }
    }

    /**
     * Updates standing based on the winner of a match
     *
     * @param match the completed match
     */
    public void updateStandings(final Match match) {
        final MatchResults results = match.getWinner();

        if (results == MatchResults.TIE) {
            return;
        }

        final Team winner;

        if (results == MatchResults.PLAYER_ONE) {
            winner = teams.get(0);
        } else {
            winner = teams.get(1);
        }

        standings.put(winner, standings.get(winner) + 1);
    }

    /**
     * Gets a copy of the current standings
     *
     * @return the standings
     */
    public Map<Team, Integer> getStandings() {
        return new HashMap<>(standings);
    }

    /**
     * Gets the team with the most wins
     *
     * @return the champion team
     */
    public Team getChampion() {
        if (teams.isEmpty()) {
            return null;
        }

        Team champion = teams.get(0);

        for (final Team team : teams) {
            if (standings.get(team) >
                    standings.get(champion)) {
                champion = team;
            }
        }

        return champion;
    }

    /**
     * Gets a copy of the scheduled weeks
     *
     * @return the scheduled weeks
     */
    public List<Week> getWeeks() {
        return new ArrayList<>(weeks);
    }
}
