package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests for DraftManager class.
 */
class DraftManagerTest {

    @Test
    void pickPlayer_validPick_addsPoliticianToTeam() {
        Politician politician = mock(Politician.class);

        Team team = new Team("Team One");

        List<Politician> players = new ArrayList<>();
        players.add(politician);

        List<Team> teams = new ArrayList<>();
        teams.add(team);

        ScoringStrategy strategy = mock(ScoringStrategy.class);

        DraftManager draftManager = new DraftManager(
                players,
                teams,
                1,
                strategy
        );

        draftManager.pickPlayer(team, politician);

        assertEquals(1, team.getRoster().size());
        assertEquals(politician, team.getRoster().get(0));
    }

    @Test
    void pickPlayer_fullRoster_doesNotAddSecondPlayer() {
        Politician first = mock(Politician.class);
        Politician second = mock(Politician.class);

        Team team = new Team("Team One");

        List<Politician> players = new ArrayList<>();
        players.add(first);
        players.add(second);

        List<Team> teams = new ArrayList<>();
        teams.add(team);

        ScoringStrategy strategy = mock(ScoringStrategy.class);

        DraftManager draftManager = new DraftManager(
                players,
                teams,
                1,
                strategy
        );

        draftManager.pickPlayer(team, first);
        draftManager.pickPlayer(team, second);

        assertEquals(1, team.getRoster().size());
        assertEquals(first, team.getRoster().get(0));
    }

    @Test
    void pickPlayer_unavailablePlayer_notAdded() {
        Politician available = mock(Politician.class);
        Politician unavailable = mock(Politician.class);

        Team team = new Team("Team One");

        List<Politician> players = new ArrayList<>();
        players.add(available);

        List<Team> teams = new ArrayList<>();
        teams.add(team);

        ScoringStrategy strategy = mock(ScoringStrategy.class);

        DraftManager draftManager = new DraftManager(
                players,
                teams,
                2,
                strategy
        );

        draftManager.pickPlayer(team, unavailable);

        assertEquals(0, team.getRoster().size());
    }

    @Test
    void pickPlayer_samePoliticianTwice_onlyAddsOnce() {
        Politician politician = mock(Politician.class);

        Team firstTeam = new Team("Team One");
        Team secondTeam = new Team("Team Two");

        List<Politician> players = new ArrayList<>();
        players.add(politician);

        List<Team> teams = new ArrayList<>();
        teams.add(firstTeam);
        teams.add(secondTeam);

        ScoringStrategy strategy = mock(ScoringStrategy.class);

        DraftManager draftManager = new DraftManager(
                players,
                teams,
                1,
                strategy
        );

        draftManager.pickPlayer(firstTeam, politician);
        draftManager.pickPlayer(secondTeam, politician);

        assertEquals(1, firstTeam.getRoster().size());
        assertEquals(0, secondTeam.getRoster().size());
    }
}