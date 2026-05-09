package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a team.
 */
public class Team {

    private final String name;

    private final List<Politician> roster;

    /**
     * Creates a team.
     *
     * @param name the team name
     */
    public Team(final String name) {
        this.name = name;
        this.roster = new ArrayList<>();
    }

    /**
     * Adds a politician.
     *
     * @param politician the politician
     */
    public void addPolitician(final Politician politician) {
        if (politician != null) {
            roster.add(politician);
        }
    }

    /**
     * Gets roster.
     *
     * @return roster
     */
    public List<Politician> getRoster() {
        return new ArrayList<>(roster);
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
        double totalScore = 0.0;

        for (final Politician politician : roster) {
            totalScore += strategy.calculateScore(politician, week);
        }

        return totalScore;
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
        if (roster.isEmpty()) {
            return null;
        }

        Politician bestPolitician = roster.get(0);
        double bestScore = strategy.calculateScore(bestPolitician, week);

        for (final Politician politician : roster) {
            final double score = strategy.calculateScore(politician, week);

            if (score > bestScore) {
                bestScore = score;
                bestPolitician = politician;
            }
        }

        return bestPolitician;
    }

    /**
     * Gets name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}


