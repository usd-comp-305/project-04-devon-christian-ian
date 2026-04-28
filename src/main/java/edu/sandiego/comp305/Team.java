package edu.sandiego.comp305;

import java.util.List;

public class Team {

    private String name;
    private List<Politician> roster;

    public Team(String name) {}

    public void addPolitician(Politician p) {}

    public List<Politician> getRoster() {
        return null;
    }

    public double calculateWeeklyScore(ScoringStrategy strategy, int week) {
        return 0.0;
    }

    public Politician getMVP(ScoringStrategy strategy, int week) {
        return null;
    }

    public String getName() {
        return null;
    }
}