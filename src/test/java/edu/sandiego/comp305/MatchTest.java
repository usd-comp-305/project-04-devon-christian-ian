package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatchTest {

    @Test
    void getWinnerReturnsWinner() {
        final Team playerOneTeam = mock(Team.class);
        final Team playerTwoTeam = mock(Team.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);
        final Match match = new Match(playerOneTeam, playerTwoTeam);

        when(playerOneTeam.calculateWeeklyScore(strategy, 1)).thenReturn(10.0);
        when(playerTwoTeam.calculateWeeklyScore(strategy, 1)).thenReturn(5.0);

        match.play(strategy, 1);

        assertSame(playerOneTeam, match.getWinner());
    }

    @Test
    void isTieReturnsTrue() {
        final Team playerOneTeam = mock(Team.class);
        final Team playerTwoTeam = mock(Team.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);
        final Match match = new Match(playerOneTeam, playerTwoTeam);

        when(playerOneTeam.calculateWeeklyScore(strategy, 1)).thenReturn(10.0);
        when(playerTwoTeam.calculateWeeklyScore(strategy, 1)).thenReturn(10.0);

        match.play(strategy, 1);

        assertTrue(match.isTie());
    }

    @Test
    void isTieFalse() {
        final Team playerOneTeam = mock(Team.class);
        final Team playerTwoTeam = mock(Team.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);
        final Match match = new Match(playerOneTeam, playerTwoTeam);

        when(playerOneTeam.calculateWeeklyScore(strategy, 1)).thenReturn(10.0);
        when(playerTwoTeam.calculateWeeklyScore(strategy, 1)).thenReturn(5.0);

        match.play(strategy, 1);

        assertFalse(match.isTie());
    }
}
