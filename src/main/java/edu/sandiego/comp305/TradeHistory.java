package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Provides analytics for a Politician's trades,
 * and functions as a wrapper for computations needed in the scoring strategies.
 * This helps separate the Trade and Politician classes.
 */
public class TradeHistory {

    private final Map<Integer, List<Trade>> weeklyTrades;

    public TradeHistory(final List<Trade> allTrades) {
        weeklyTrades = new HashMap<>();

        for(int week = 1; week <= 12; week++) {
            weeklyTrades.put(week, new ArrayList<>());
        }

        for (Trade indTrade : allTrades) {
            int month = indTrade.getDate().getMonthValue();
            List<Trade> listForMonth = weeklyTrades.get(month);
            listForMonth.add(indTrade);
        }
    }
    /**
     * Returns all trades that fall within the given week index.
     * Week 0 index = January, Week 1 index = February
     *
     * @param weekNumber = The given month to collect trades for.
     * @return All of the politician's trades for this week of data.
     */
    public List<Trade> getWeeklyTrades(final int weekNumber) {
        if (weekNumber < 1 || weekNumber > 12) {
            return new ArrayList<>();
        }
        return weeklyTrades.get(weekNumber);
    }

    /**
     * Used to provide all trades from this year for the given politician
     * @return all trades across all weeks
     */
    public List<Trade> getTrades() {
        List<Trade> allTrades = new ArrayList<>();

        for(int week = 1; week <=12; week++) {
            allTrades.addAll(weeklyTrades.get(week));
        }
        return allTrades;
    }
}
