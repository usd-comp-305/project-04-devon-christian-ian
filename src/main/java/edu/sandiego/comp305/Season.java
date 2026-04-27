package edu.sandiego.comp305;

import java.util.List;
import java.util.Map;

public class Season {

    private List<Team> teams;
    private List<Week> weeks;
    private Map<Team, Integer> standings;

    public Season(List<Team> teams) {
    }

    public void generateSchedule() {
    }

    public void runSeason(ScoringStrategy strategy) {
    }

    public void updateStandings(Match match) {
    }

    public Map<Team, Integer> getStandings() {
        return null;
    }

    public Team getChampion() {
        return null;
    }
}