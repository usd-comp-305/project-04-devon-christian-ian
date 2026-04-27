package edu.sandiego.comp305;

import java.util.*;

public class TradeHistory {

    private List<Trade> trades;

    public TradeHistory() {
        this.trades = new ArrayList<>();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public List<Trade> getWeeklyTrades(int week) {
        return trades;
    }

    public double getWeeklyProfit(int week, Map<String, Double> prices) {
        double total = 0;

        List<Trade> weeklyTrades = getWeeklyTrades(week);

        for (Trade trade : weeklyTrades) {
            String ticker = trade.getTicker();

            if (!prices.containsKey(ticker)) continue;

            double laterPrice = prices.get(ticker);

            total += trade.calculateEstimatedProfit(laterPrice);
        }

        return total;
    }
}