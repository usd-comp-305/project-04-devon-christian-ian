package edu.sandiego.comp305;

import java.util.Map;

/**
 * Scores politicians by portfolio profit.
 */
public class PortfolioProfitStrategy implements ScoringStrategy {

    /**
     * Creates a portfolio profit strategy.
     *
     * @param priceMap the price map
     */
    public PortfolioProfitStrategy(final Map<String, Double> priceMap) {
    }

    @Override
    public double calculateScore(
            final Politician politician,
            final int week) {
        return 0.0;
    }
}
