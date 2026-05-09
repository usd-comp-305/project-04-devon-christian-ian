package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for Team class.
 */
class TeamTest {

    @Test
    void constructor_setsName() {
        Team team = new Team("Team One");

        assertEquals("Team One", team.getName());
    }

    @Test
    void constructor_startsWithEmptyRoster() {
        Team team = new Team("Team One");

        assertEquals(0, team.getRoster().size());
    }

    @Test
    void addPolitician_validPolitician_addsToRoster() {
        Team team = new Team("Team One");
        Politician politician = mock(Politician.class);

        team.addPolitician(politician);

        assertEquals(1, team.getRoster().size());
        assertEquals(politician, team.getRoster().get(0));
    }

    @Test
    void addPolitician_nullPolitician_doesNotAddToRoster() {
        Team team = new Team("Team One");

        team.addPolitician(null);

        assertEquals(0, team.getRoster().size());
    }

    @Test
    void calculateWeeklyScore_emptyRoster_returnsZero() {
        Team team = new Team("Team One");
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        double result = team.calculateWeeklyScore(strategy, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateWeeklyScore_validRoster_sumsPoliticianScores() {
        Team team = new Team("Team One");

        Politician first = mock(Politician.class);
        Politician second = mock(Politician.class);
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        team.addPolitician(first);
        team.addPolitician(second);

        when(strategy.calculateScore(first, 3)).thenReturn(10.0);
        when(strategy.calculateScore(second, 3)).thenReturn(25.0);

        double result = team.calculateWeeklyScore(strategy, 3);

        assertEquals(35.0, result);
    }

    @Test
    void getMVP_emptyRoster_returnsNull() {
        Team team = new Team("Team One");
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        Politician result = team.getMVP(strategy, 1);

        assertNull(result);
    }

    @Test
    void getMVP_validRoster_returnsHighestScoringPolitician() {
        Team team = new Team("Team One");

        Politician lowScorer = mock(Politician.class);
        Politician highScorer = mock(Politician.class);
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        team.addPolitician(lowScorer);
        team.addPolitician(highScorer);

        when(strategy.calculateScore(lowScorer, 2)).thenReturn(5.0);
        when(strategy.calculateScore(highScorer, 2)).thenReturn(20.0);

        Politician result = team.getMVP(strategy, 2);

        assertEquals(highScorer, result);
    }

    @Test
    void getBust_emptyRoster_returnsNull() {
        Team team = new Team("Team One");
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        Politician result = team.getBust(strategy, 1);

        assertNull(result);
    }

    @Test
    void getBust_validRoster_returnsLowestScoringPolitician() {
        Team team = new Team("Team One");

        Politician lowScorer = mock(Politician.class);
        Politician highScorer = mock(Politician.class);
        ScoringStrategy strategy = mock(ScoringStrategy.class);

        team.addPolitician(lowScorer);
        team.addPolitician(highScorer);

        when(strategy.calculateScore(lowScorer, 4)).thenReturn(-10.0);
        when(strategy.calculateScore(highScorer, 4)).thenReturn(30.0);

        Politician result = team.getBust(strategy, 4);

        assertEquals(lowScorer, result);
    }
}