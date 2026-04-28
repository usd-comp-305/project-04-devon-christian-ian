package edu.sandiego.comp305;

import java.util.*;
import java.time.temporal.IsoFields;

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
        List<Trade> weeklyTrades = new ArrayList<>();

        for (Trade trade : trades) {
            int tradeWeek = trade.getDate().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);

            if (tradeWeek == week) {
                weeklyTrades.add(trade);
            }
        }

        return weeklyTrades;
    }

    public double getWeeklyProfit(int week, Map<String, Double> prices) {
        double totalProfit = 0.0;

        List<Trade> weeklyTrades = getWeeklyTrades(week);

        for (Trade trade : weeklyTrades) {
            String ticker = trade.getTicker();

            if (prices.containsKey(ticker)) {
                double laterPrice = prices.get(ticker);
                totalProfit += trade.calculateEstimatedProfit(laterPrice);
            }
        }

        return totalProfit;
    }
}