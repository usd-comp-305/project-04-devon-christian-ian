package edu.sandiego.comp305;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Season {

    private final Map<Team, Integer> standings;
    private List<Team> teams;
    /**
     *Creates a season with all teams starting at zero
     * @param teams the teams ing the season
     */
    public Season(final List<Team> teams) {
        standings = new HashMap<>();
        this.teams = teams;

        for (Team team : teams) {
            standings.put(team,0);
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
    }

    /**
     *
     * @return the standings
     */
    public Map<Team, Integer> getStandings() {
        return standings;
    }

    /**
     *
     * @return
     */
    public Team getChampion() {
        return teams.get(0);
    }
}
