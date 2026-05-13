package edu.sandiego.comp305;

/**
 * Represents one matchup between the two teams
 */
public class Match {

    private final Team playerOneTeam;

    private final Team playerTwoTeam;

    private double playerOneScore;

    private double playerTwoScore;

    /**
     * Creates one matchup between the two teams
     *
     * @param playerOneTeam player one's team
     * @param playerTwoTeam player two's team
     */
    public Match(final Team playerOneTeam, final Team playerTwoTeam) {
        this.playerOneTeam = new Team(playerOneTeam);
        this.playerTwoTeam = new Team (playerTwoTeam);
    }

    /**
     * Calculates both team scores for the given week
     *
     * @param strategy the scoring strategy used to calculate
     * @param week the week being played
     */
    public void play(final ScoringStrategy strategy, final int week) {
        playerOneScore = playerOneTeam.calculateWeeklyScore(strategy, week);
        playerTwoScore = playerTwoTeam.calculateWeeklyScore(strategy, week);
    }

    /**
     * Gets the winning team
     *
     * @return the winning team, or null if tie
     */
    public MatchResults getWinner() {
        if (isTie()) {
            return MatchResults.TIE;
        }

        if (playerOneScore > playerTwoScore) {
            return MatchResults.PLAYER_ONE;
        }
        return MatchResults.PLAYER_TWO;
    }

    /**
     * not yet implemented
     * @return
     */
    public Politician getMVP() {
        return null;
    }

    /**
     * checks whether both teams have the same score
     *
     * @return true if tied, false if not tied
     */
    public boolean isTie() {
        return (playerOneScore == playerTwoScore);
    }

}
