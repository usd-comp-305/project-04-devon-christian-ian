package edu.sandiego.comp305;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Season {

    private final Map<Team, Integer> standings;
    /**
     *
     * @param teams
     */
    public Season(final List<Team> teams) {
        standings = new HashMap<>();

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
        return null;
    }
}
