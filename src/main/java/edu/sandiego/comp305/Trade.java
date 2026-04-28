package edu.sandiego.comp305;

import java.time.LocalDate;

/**
 *
 */
public class Trade {

    /**
     *
     * @param date
     * @param ticker
     * @param price
     * @param estimatedAmount
     * @param type
     */
    public Trade(
            final LocalDate date,
            final String ticker,
            final double price,
            final double estimatedAmount,
            final TradeType type) {
    }

    /**
     *
     * @param laterPrice
     * @return
     */
    public double calculateEstimatedProfit(final double laterPrice) {
        return 0.0;
    }

    /**
     *
     * @return
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     *
     * @return
     */
    public String getTicker() {
        return null;
    }
}
