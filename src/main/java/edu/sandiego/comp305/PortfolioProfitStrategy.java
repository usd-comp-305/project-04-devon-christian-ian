package edu.sandiego.comp305;

import java.util.List;

/**
 * Simplified portfolio-style scoring strategy.
 *
 * This strategy uses the existing Trade.calculateEstimatedProfit method
 * to calculate each trade's contribution to a politician's weekly score.
 */
public class PortfolioProfitStrategy implements ScoringStrategy {

    /**
     * Calculates a politician's weekly score.
     *
     * @param politician politician being scored
     * @param week week of season
     * @return weekly score
     */
    @Override
    public double calculateScore(final Politician politician, final int week) {
        if (politician == null || politician.getTradeHistory() == null) {
            return 0.0;
        }

        double totalScore = 0.0;

        List<Trade> weeklyTrades = politician.getTradeHistory().getWeeklyTrades(week);

        for (Trade trade : weeklyTrades) {
            totalScore += trade.calculateEstimatedProfit(0.0);
        }

        return totalScore;
    }
}