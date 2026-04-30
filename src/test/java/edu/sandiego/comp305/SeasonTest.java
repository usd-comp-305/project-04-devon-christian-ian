package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void getChampionReturnsOneTeam() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        teams.add(firstTeam);

        final Season season = new Season(teams);

        assertSame(firstTeam, season.getChampion());
    }

    @Test
    void getChampionReturnsWinningTeam() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        teams.add(firstTeam);
        teams.add(secondTeam);

        final Season season = new Season(teams);
        final Match match = mock(Match.class);

        when(match.getWinner()).thenReturn(secondTeam);
        season.updateStandings(match);

        assertSame(secondTeam, season.getChampion());
    }

    @Test
    void seasonDoesNotChangeTeamList() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        teams.add(firstTeam);

        final Season season = new Season(teams);
        teams.add(secondTeam);

        assertEquals(1, season.getStandings().size());
        assertSame(firstTeam, season.getChampion());
    }

    @Test
    void getChampionReturnsNull() {
        final List<Team> teams = new ArrayList<>();
        final Season season = new Season(teams);

        assertNull(season.getChampion());
    }

    @Test
    void getChampionReturnsFirstTeamWhenTied() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        teams.add(firstTeam);
        teams.add(secondTeam);

        final Season season = new Season(teams);

        assertSame(firstTeam, season.getChampion());
    }

    @Test
    void updateStandingAddsOne() {
        final List<Team> teams = new ArrayList<>();

        final Team firstTeam = new Team("First");
        final Team secondTeam = new Team("Second");
        teams.add(firstTeam);
        teams.add(secondTeam);

        final Season season = new Season(teams);
        final Match match = mock(Match.class);

        when(match.getWinner()).thenReturn(firstTeam);

        season.updateStandings(match);

        assertEquals(1, season.getStandings().get(firstTeam));
        assertEquals(0, season.getStandings().get(secondTeam));
    }

}
