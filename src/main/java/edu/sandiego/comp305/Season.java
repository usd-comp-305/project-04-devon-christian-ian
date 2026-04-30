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

    /**
     *Creates a season with all teams starting at zero
     * @param teams the teams ing the season
     */
    public Season(final List<Team> teams) {
        standings = new HashMap<>();
        this.teams = new ArrayList<>(teams);

        for (final Team team : teams) {
            standings.put(team, 0);
        }
    }

    /**
     *
     */
    public void generateSchedule() {
    }

    /**
     *
     * @param strategy
     */
    public void runSeason(final ScoringStrategy strategy) {
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
}
