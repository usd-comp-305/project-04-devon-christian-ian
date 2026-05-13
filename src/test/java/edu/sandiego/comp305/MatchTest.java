package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MatchTest {

    @Test
    void getWinnerReturnsPlayerOne() {
        final Team playerOneTeam = new Team("Player One");
        final Team playerTwoTeam = new Team("Player Two");
        final Politician playerOnePolitician = mock(Politician.class);
        final Politician playerTwoPolitician = mock(Politician.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);

        playerOneTeam.addPolitician(playerOnePolitician);
        playerTwoTeam.addPolitician(playerTwoPolitician);

        when(strategy.calculateScore(playerOnePolitician, 1)).thenReturn(10.0);
        when(strategy.calculateScore(playerTwoPolitician, 1)).thenReturn(5.0);

        final Match match = new Match(playerOneTeam, playerTwoTeam);
        match.play(strategy, 1);

        assertEquals(MatchResults.PLAYER_ONE, match.getWinner());
    }

    @Test
    void getWinnerReturnPlayerTwo() {
        final Team playerOneTeam = new Team("Player One");
        final Team playerTwoTeam = new Team("Player Two");
        final Politician playerOnePolitician = mock(Politician.class);
        final Politician playerTwoPolitician = mock(Politician.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);

        playerOneTeam.addPolitician(playerOnePolitician);
        playerTwoTeam.addPolitician(playerTwoPolitician);

        when(strategy.calculateScore(playerOnePolitician, 1)).thenReturn(5.0);
        when(strategy.calculateScore(playerTwoPolitician, 1)).thenReturn(10.0);

        final Match match = new Match(playerOneTeam, playerTwoTeam);
        match.play(strategy, 1);

        assertEquals(MatchResults.PLAYER_TWO, match.getWinner());
    }

    @Test
    void getWinnerIsTie() {
        final Team playerOneTeam = new Team("Player One");
        final Team playerTwoTeam = new Team("Player Two");
        final Politician playerOnePolitician = mock(Politician.class);
        final Politician playerTwoPolitician = mock(Politician.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);

        playerOneTeam.addPolitician(playerOnePolitician);
        playerTwoTeam.addPolitician(playerTwoPolitician);

        when(strategy.calculateScore(playerOnePolitician, 1)).thenReturn(5.0);
        when(strategy.calculateScore(playerTwoPolitician, 1)).thenReturn(5.0);

        final Match match = new Match(playerOneTeam, playerTwoTeam);
        match.play(strategy, 1);

        assertEquals(MatchResults.TIE, match.getWinner());
        assertTrue(match.isTie());
    }

    @Test
    void isTieFalse() {
        final Team playerOneTeam = new Team("Player One");
        final Team playerTwoTeam = new Team("Player Two");
        final Politician playerOnePolitician = mock(Politician.class);
        final Politician playerTwoPolitician = mock(Politician.class);
        final ScoringStrategy strategy = mock(ScoringStrategy.class);

        playerOneTeam.addPolitician(playerOnePolitician);
        playerTwoTeam.addPolitician(playerTwoPolitician);

        when(strategy.calculateScore(playerOnePolitician, 1)).thenReturn(5.0);
        when(strategy.calculateScore(playerTwoPolitician, 1)).thenReturn(10.0);

        final Match match = new Match(playerOneTeam, playerTwoTeam);
        match.play(strategy, 1);

        assertFalse(match.isTie());
    }
}
