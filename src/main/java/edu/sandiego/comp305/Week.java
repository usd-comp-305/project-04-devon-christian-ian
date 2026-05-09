package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Week {

    private final int weekNumber;

    private final List<Match> matches;

    /**
     *
     * @param weekNumber
     */
    public Week(final int weekNumber, final List<Match> matches) {
        this.weekNumber = weekNumber;
        this.matches = new ArrayList<>(matches);
    }

    /**
     *
     * @param strategy
     */
    public void simulateWeek(final ScoringStrategy strategy) {
        for (final Match match : matches) {
            match.play(strategy, weekNumber);
        }
    }

    /**
     *
     * @return
     */
    public List<Match> getMatches() {
        return new ArrayList<>(matches);
    }

}
