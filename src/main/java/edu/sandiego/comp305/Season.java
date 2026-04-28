package edu.sandiego.comp305;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a season.
 */
public class Season {

    /**
     * Creates a season.
     *
     * @param teams the teams
     */
    public Season(final List<Team> teams) {
    }

    /**
     * Generates the schedule.
     */
    public void generateSchedule() {
    }

    /**
     * Runs the season.
     *
     * @param strategy the scoring strategy
     */
    public void runSeason(final ScoringStrategy strategy) {
    }

    /**
     * Updates standings.
     *
     * @param match the match
     */
    public void updateStandings(final Match match) {
    }

    /**
     * Gets standings.
     *
     * @return standings
     */
    public Map<Team, Integer> getStandings() {
        return new HashMap<>();
    }

    /**
     * Gets champion.
     *
     * @return champion
     */
    public Team getChampion() {
        return null;
    }
}
