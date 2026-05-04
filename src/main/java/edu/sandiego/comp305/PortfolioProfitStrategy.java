package edu.sandiego.comp305;

import java.util.HashMap;
import java.util.Map;

/**
 * Scores politicians based on estimated portfolio profit or loss.
 */
public class PortfolioProfitStrategy implements ScoringStrategy {

    /**
     * Maps each stock ticker to its simulated later price.
     */
    private Map<String, Double> priceMap;

    /**
     * Creates a portfolio profit scoring strategy.
     *
     * @param priceMap maps stock tickers to later/current prices
     */
    public PortfolioProfitStrategy(final Map<String, Double> priceMap) {
        this.priceMap = new HashMap<>(priceMap);
    }

    /**
     * Calculates one politician's portfolio-profit score for a simulated week.
     *
     * This method keeps the Strategy Pattern in place. The strategy decides
     * that scoring is based on portfolio profit, then asks the politician's
     * TradeHistory to calculate that weekly profit using the available prices.
     *
     * @param politician the politician being scored
     * @param week the simulated week number
     * @return the politician's total estimated profit for that week
     */
    @Override
    public double calculateScore(
            final Politician politician,
            final int week) {

        if (politician == null) {
            return 0.0;
        }

        TradeHistory tradeHistory = politician.getTradeHistory();

        if (tradeHistory == null) {
            return 0.0;
        }

        return tradeHistory.getWeeklyProfit(week, priceMap);
    }
}
