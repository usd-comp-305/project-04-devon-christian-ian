package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides analytics for a Politician's trades,
 * and functions as a wrapper for computations needed
 * in the scoring strategies.
 */
public class TradeHistory {

    private final Map<Integer, List<Trade>> weeklyTrades;

    public TradeHistory(final List<Trade> allTrades) {
        weeklyTrades = new HashMap<>();

        for (int week = 1; week <= 12; week++) {
            weeklyTrades.put(week, new ArrayList<>());
        }

        for (final Trade indTrade : allTrades) {
            final int month = indTrade.getDate().getMonthValue();
            final List<Trade> listForMonth = weeklyTrades.get(month);
            listForMonth.add(indTrade);
        }
    }

    /**
     * Returns all trades that fall within the given week index.
     *
     * @param weekNumber The given month to collect trades for.
     * @return All of the politician's trades for this week.
     */
    public List<Trade> getWeeklyTrades(final int weekNumber) {
        if (weekNumber < 1 || weekNumber > 12) {
            return new ArrayList<>();
        }

        return weeklyTrades.get(weekNumber);
    }

    /**
     * Returns all trades across all weeks.
     *
     * @return all trades for this politician.
     */
    public List<Trade> getTrades() {
        final List<Trade> allTrades = new ArrayList<>();

        for (int week = 1; week <= 12; week++) {
            allTrades.addAll(weeklyTrades.get(week));
        }

        return allTrades;
    }
}
