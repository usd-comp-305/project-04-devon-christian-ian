package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Season {

    private final Map<Team, Integer> standings;

    private final List<Team> teams;

    private final List<Week> weeks;

    /**
     *Creates a season with all teams starting at zero
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
     *
     */
    public void generateSchedule() {
        final List<Match> matches = new ArrayList<>();
        matches.add(new Match(teams.get(0), teams.get(1)));

        weeks.add(new Week(1, matches));
    }

    /**
     *
     * @param strategy
     */
    public void runSeason(final ScoringStrategy strategy) {
        for (final Week week : weeks) {
            week.simulateWeek(strategy);
        }
    }

    /**
     *
     * @param match
     */
    public void updateStandings(final Match match) {
        if (match.isTie()) {
            return;
        }

        final Team winner = match.getWinner();

        if (!standings.containsKey(winner)) {
            return;
        }

        standings.put(winner, standings.get(winner) + 1);
    }

    /**
     *
     * @return the standings
     */
    public Map<Team, Integer> getStandings() {
        return new HashMap<>(standings);
    }

    /**
     *
     * @return
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
     *
     * @return
     */
    public List<Week> getWeeks() {
        return new ArrayList<>(weeks);
    }
}
