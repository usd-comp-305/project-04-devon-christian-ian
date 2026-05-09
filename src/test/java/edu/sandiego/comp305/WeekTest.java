package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WeekTest {

    @Test
    void getMatchesReturnsMatches() {
        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        final Match match = new Match(firstTeam, secondTeam);

        final Week week = new Week(1, List.of(match));

        assertEquals(1, week.getMatches().size());
        assertTrue(week.getMatches().contains(match));
    }
}
