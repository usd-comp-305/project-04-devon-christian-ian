package edu.sandiego.comp305;

/**
 * Defines how a politician earns points during a simulated week.
 */
public interface ScoringStrategy {

    /**
     * Calculates the score for one politician during one simulated week.
     *
     * @param politician the politician being scored
     * @param week the simulated week number
     * @return the politician's score for that week
     */
    public abstract double calculateScore(
            final Politician politician,
            final int week);
}
