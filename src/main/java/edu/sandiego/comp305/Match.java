package edu.sandiego.comp305;

/**
 * Represents a match.
 */
public class Match {

    /**
     * Creates a match.
     *
     * @param home the home team
     * @param away the away team
     */
    public Match(final Team home, final Team away) {
    }

    /**
     * Plays the match.
     *
     * @param strategy the scoring strategy
     * @param week the week
     */
    public void play(final ScoringStrategy strategy, final int week) {
    }

    /**
     * Gets winner.
     *
     * @return winner
     */
    public Team getWinner() {
        return null;
    }

    /**
     * Gets MVP.
     *
     * @return MVP
     */
    public Politician getMVP() {
        return null;
    }

    /**
     * Checks for tie.
     *
     * @return true if tied
     */
    public boolean isTie() {
        return false;
    }
}
