package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.mock;

/**
 * Tests for DraftManager class.
 */
class DraftManagerTest {

    @Test
    void pickPlayer_validPick_addsPoliticianToTeam() {
        final Politician politician = mock(Politician.class);
        final Team team = new Team("Team One");

        final List<Politician> players = new ArrayList<>();
        players.add(politician);

        final List<Team> teams = new ArrayList<>();
        teams.add(team);

        final DraftManager draftManager =
                new DraftManager(players, teams, 1);

        draftManager.pickPlayer(team, politician);

        assertEquals(1, team.getRoster().size());
        assertEquals(politician, team.getRoster().get(0));
    }

    @Test
    void pickPlayer_fullRoster_doesNotAddSecondPlayer() {
        final Politician first = mock(Politician.class);
        final Politician second = mock(Politician.class);
        final Team team = new Team("Team One");

        final List<Politician> players = new ArrayList<>();
        players.add(first);
        players.add(second);

        final List<Team> teams = new ArrayList<>();
        teams.add(team);

        final DraftManager draftManager =
                new DraftManager(players, teams, 1);

        draftManager.pickPlayer(team, first);
        draftManager.pickPlayer(team, second);

        assertEquals(1, team.getRoster().size());
        assertEquals(first, team.getRoster().get(0));
    }

    @Test
    void pickPlayer_unavailablePlayer_notAdded() {
        final Politician available = mock(Politician.class);
        final Politician unavailable = mock(Politician.class);
        final Team team = new Team("Team One");

        final List<Politician> players = new ArrayList<>();
        players.add(available);

        final List<Team> teams = new ArrayList<>();
        teams.add(team);

        final DraftManager draftManager =
                new DraftManager(players, teams, 2);

        draftManager.pickPlayer(team, unavailable);

        assertEquals(0, team.getRoster().size());
    }

    @Test
    void pickPlayer_samePoliticianTwice_onlyAddsOnce() {
        final Politician politician = mock(Politician.class);

        final Team firstTeam = new Team("Team One");
        final Team secondTeam = new Team("Team Two");

        final List<Politician> players = new ArrayList<>();
        players.add(politician);

        final List<Team> teams = new ArrayList<>();
        teams.add(firstTeam);
        teams.add(secondTeam);

        final DraftManager draftManager =
                new DraftManager(players, teams, 1);

        draftManager.pickPlayer(firstTeam, politician);
        draftManager.pickPlayer(secondTeam, politician);

        assertEquals(1, firstTeam.getRoster().size());
        assertEquals(0, secondTeam.getRoster().size());
    }
}
