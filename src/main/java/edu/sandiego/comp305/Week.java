package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 * One scheduled week in a season
 */
public class Week {

    private final int weekNumber;

    private final List<Match> matches;

    /**
     * Creates a week with scheduled matches
     *
     * @param weekNumber the week in the season
     *
     * @param matches the matches scheduled for this week
     */
    public Week(final int weekNumber, final List<Match> matches) {
        this.weekNumber = weekNumber;
        this.matches = new ArrayList<>(matches);
    }

    /**
     * Simulates every match scheduled for this week
     *
     * @param strategy the scoring strategy used for each match
     */
    public void simulateWeek(final ScoringStrategy strategy) {
        for (final Match match : matches) {
            match.play(strategy, weekNumber);
        }
    }

    /**
     * Gets a copy of the matches scheduled for this week
     *
     * @return th scheduled matches
     */
    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

    /**
     * Gets the week number.
     *
     * @return the week number
     */
    public int getWeekNumber() {
        return weekNumber;
    }
}
