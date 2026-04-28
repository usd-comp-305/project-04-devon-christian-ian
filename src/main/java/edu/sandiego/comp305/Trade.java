package edu.sandiego.comp305;

import java.time.LocalDate;


public class Trade {


    public Trade(
            final LocalDate date,
            final String ticker,
            final double price,
            final double estimatedAmount,
            final TradeType type) {
    }


    public double calculateEstimatedProfit(final double laterPrice) {
        return 0.0;
    }

    public LocalDate getDate() {
        return null;
    }


    public String getTicker() {
        return null;
    }
}