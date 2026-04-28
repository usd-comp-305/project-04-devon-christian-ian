package edu.sandiego.comp305;

/**
 * Defines a scoring strategy.
 */
public interface ScoringStrategy {

    /**
     * Calculates score.
     *
     * @param politician the politician
     * @param week the week
     * @return score
     */
    public abstract double calculateScore(
            final Politician politician, final int week);

}
