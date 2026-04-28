package edu.sandiego.comp305;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Season {

    /**
     *
     * @param teams
     */
    public Season(final List<Team> teams) {
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
     * @return
     */
    public Map<Team, Integer> getStandings() {
        return new HashMap<>();
    }

    /**
     *
     * @return
     */
    public Team getChampion() {
        return null;
    }
}
