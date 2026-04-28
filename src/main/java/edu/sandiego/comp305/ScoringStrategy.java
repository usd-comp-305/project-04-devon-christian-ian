package edu.sandiego.comp305;

/**
 *
 */
public interface ScoringStrategy {

    /**
     *
     * @param politician
     * @param week
     * @return
     */
    public abstract double calculateScore(
            final Politician politician, final int week);

}
