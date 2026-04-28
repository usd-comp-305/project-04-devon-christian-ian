package edu.sandiego.comp305;

public class Match {

    private Team home;
    private Team away;
    private double homeScore;
    private double awayScore;
    private Politician mvp;

    public Match(Team home, Team away) {
    }

    public void play(ScoringStrategy strategy, int week) {
    }

    public Team getWinner() {
        return null;
    }

    public Politician getMVP() {
        return null;
    }

    public boolean isTie() {
        return false;
    }
}