package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    void getMatchesEncapsulationCheck() {
        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        final Match match = new Match(firstTeam, secondTeam);

        final Week week = new Week(1, List.of(match));
        week.getMatches().clear();

        assertEquals(1, week.getMatches().size());
        assertTrue(week.getMatches().contains(match));
    }

    @Test
    void simulateWeekPlaysEachMatch() {
        final Match firstMatch = mock(Match.class);
        final Match secondMatch = mock(Match.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);

        final Week week = new Week(1, List.of(firstMatch, secondMatch));

        week.simulateWeek(strategy);

        verify(firstMatch).play(strategy, 1);
        verify(secondMatch).play(strategy, 1);
    }
}
