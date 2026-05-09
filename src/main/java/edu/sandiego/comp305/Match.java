package edu.sandiego.comp305;

/**
 *
 */
public class Match {

    private final Team playerOneTeam;
    private final Team playerTwoTeam;

    private double playerOneScore;
    private double playerTwoScore;

    /**
     *
     * @param playerOneTeam
     * @param playerTwoTeam
     */
    public Match(final Team playerOneTeam, final Team playerTwoTeam) {
        this.playerOneTeam = playerOneTeam;
        this.playerTwoTeam = playerTwoTeam;
    }

    /**
     *
     * @param strategy
     * @param week
     */
    public void play(final ScoringStrategy strategy, final int week) {
        playerOneScore = playerOneTeam.calculateWeeklyScore(strategy, week);
        playerTwoScore = playerTwoTeam.calculateWeeklyScore(strategy, week);
    }

    /**
     *
     * @return
     */
    public Team getWinner() {
        if (playerOneScore > playerTwoScore) {
            return playerOneTeam;
        }
        return playerTwoTeam;
    }

    /**
     *
     * @return
     */
    public Politician getMVP() {
        return null;
    }

    /**
     *
     * @return
     */
    public boolean isTie() {
        return false;
    }
}
