package edu.sandiego.comp305;

import java.util.Map;

public class PortfolioProfitStrategy implements ScoringStrategy {

    private Map<String, Double> priceMap;

    public PortfolioProfitStrategy(Map<String, Double> priceMap) {
        this.priceMap = priceMap;
    }

    @Override
    public double calculateScore(Politician p, int week) {
        return p.getTradeHistory().getWeeklyProfit(week, priceMap);
    }
}