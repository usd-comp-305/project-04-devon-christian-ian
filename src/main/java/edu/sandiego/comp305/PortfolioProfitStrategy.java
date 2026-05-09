package edu.sandiego.comp305;

import java.util.Map;

/**
 *
 */
public class PortfolioProfitStrategy implements ScoringStrategy {

    /**
     *
     * @param priceMap
     */
    public PortfolioProfitStrategy(final Map<String, Double> priceMap) {
    }

    /**
     *
     * @param politician
     * @param week
     * @return
     */
    @Override
    public double calculateScore(
            final Politician politician,
            final int week) {
        return 0.0;
    }
}
