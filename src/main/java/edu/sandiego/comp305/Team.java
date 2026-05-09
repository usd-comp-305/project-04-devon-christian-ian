package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a fantasy draft team.
 */
public class Team {

    private final String name;
    private final List<Politician> roster;
    private Politician mvp;
    private Politician bust;

    /**
     * Creates a team.
     *
     * @param name the team name
     */
    public Team(final String name) {
        this.name = name;
        this.roster = new ArrayList<>();
        this.mvp = null;
        this.bust = null;
    }

    /**
     * Adds a politician to the roster.
     *
     * @param politician politician being added
     */
    public void addPolitician(final Politician politician) {
        if (politician != null) {
            roster.add(politician);
        }
    }

    /**
     * Gets the team roster.
     *
     * @return roster
     */
    public List<Politician> getRoster() {
        return roster;
    }

    /**
     * Calculates the team's weekly score.
     *
     * @param strategy scoring strategy
     * @param week week being scored
     * @return total weekly score
     */
    public double calculateWeeklyScore(final ScoringStrategy strategy, final int week) {
        double totalScore = 0.0;
        for (Politician politician : roster) {
            totalScore += strategy.calculateScore(politician, week);
        }
        return totalScore;
    }

    /**
     * Gets the highest-scoring politician for a week.
     *
     * @param strategy scoring strategy
     * @param week week being scored
     * @return highest-scoring politician
     */
    public Politician getMVP(final ScoringStrategy strategy, final int week) {
        if (roster.isEmpty()) {
            return null;
        }
        Politician bestPolitician = roster.get(0);
        double bestScore = strategy.calculateScore(bestPolitician, week);
        for (Politician politician : roster) {
            double score = strategy.calculateScore(politician, week);
            if (score > bestScore) {
                bestScore = score;
                bestPolitician = politician;
            }
        }
        this.mvp = bestPolitician;
        return mvp;
    }

    /**
     * Gets the lowest-scoring politician for a week.
     *
     * @param strategy scoring strategy
     * @param week week being scored
     * @return lowest-scoring politician
     */
    public Politician getBust(final ScoringStrategy strategy, final int week) {
        if (roster.isEmpty()) {
            return null;
        }

        Politician worstPolitician = roster.get(0);
        double worstScore = strategy.calculateScore(worstPolitician, week);

        for (Politician politician : roster) {
            double score = strategy.calculateScore(politician, week);

            if (score < worstScore) {
                worstScore = score;
                worstPolitician = politician;
            }
        }

        this.bust = worstPolitician;
        return bust;
    }

    /**
     * Gets the team name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}