package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team.
 */
public class Team {

    /**
     * Creates a team.
     *
     * @param name the team name
     */
    public Team(final String name) {
    }

    /**
     * Adds a politician.
     *
     * @param politician the politician
     */
    public void addPolitician(final Politician politician) {
    }

    /**
     * Gets roster.
     *
     * @return roster
     */
    public List<Politician> getRoster() {
        return new ArrayList<>();
    }

    /**
     * Calculates weekly score.
     *
     * @param strategy the scoring strategy
     * @param week the week
     * @return weekly score
     */
    public double calculateWeeklyScore(
            final ScoringStrategy strategy,
            final int week) {
        return 0.0;
    }

    /**
     * Gets MVP.
     *
     * @param strategy the scoring strategy
     * @param week the week
     * @return MVP
     */
    public Politician getMVP(
            final ScoringStrategy strategy,
            final int week) {
        return null;
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return null;
    }
}
