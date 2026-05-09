package edu.sandiego.comp305;

import java.util.List;

/**
 * Portfolio-style scoring strategy.
 *
 * Trades are scored using estimated trade value and price data.
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
     * Estimated amount represents the approximate dollar size
     * of the trade. Price is used to estimate how many shares
     * were involved in the transaction.
     *
     * @param trade trade being scored
     * @return trade score contribution
     */
    private double calculateTradeScore(final Trade trade) {

        if (trade == null || trade.getPrice() <= 0.0) {
            return 0.0;
        }

        double estimatedShares = trade.getEstimatedAmount() / trade.getPrice();

        double score = estimatedShares * trade.getPrice();

        if (trade.getType() == TradeType.BUY) {
            return -score;
        }

        if (trade.getType() == TradeType.SELL) {
            return score;
        }

        return 0.0;
    }
}
