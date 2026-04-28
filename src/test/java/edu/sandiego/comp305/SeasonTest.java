package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {

    @Test
    void getStandingsStartsAllAtZero() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");

        teams.add(firstTeam);
        teams.add(secondTeam);

        final Season season = new Season(teams);

        assertEquals(0, season.getStandings().get(firstTeam));
        assertEquals(0, season.getStandings().get(secondTeam));
        assertEquals(2, season.getStandings().size());
    }

}
