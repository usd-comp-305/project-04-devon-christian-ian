package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Week {

    private final List<Match> matches;

    /**
     *
     * @param weekNumber
     */
    public Week(final int weekNumber, final List<Match> matches) {
        this.matches = new ArrayList<>();
    }

    /**
     *
     * @param strategy
     */
    public void simulateWeek(final ScoringStrategy strategy) {
    }

    /**
     *
     * @return
     */
    public List<Match> getMatches() {
        return new ArrayList<Match>(matches);
    }

}
