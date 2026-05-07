package edu.sandiego.comp305;

import java.util.List;

/**
 * Simplified portfolio-style scoring strategy.
 *
 * Buy trades contribute negative score.
 * Sell trades contribute positive score.
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
            totalScore += calculateTradeScore(trade);
        }

        return totalScore;
    }

    /**
     * Calculates one trade's contribution to the weekly score.
     *
     * @param trade trade being scored
     * @return trade score contribution
     */
    private double calculateTradeScore(final Trade trade) {
        if (trade == null) {
            return 0.0;
        }

        if (trade.getType() == TradeType.BUY) {
            return -trade.getEstimatedAmount();
        }

        if (trade.getType() == TradeType.SELL) {
            return trade.getEstimatedAmount();
        }

        return 0.0;
    }
}
