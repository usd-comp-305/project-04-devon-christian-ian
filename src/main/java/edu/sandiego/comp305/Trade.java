package edu.sandiego.comp305;

import java.time.LocalDate;

/**
 * Represents a trade in the draft system.
 */
public class Trade {

    /**
     * Creates a trade.
     *
     * @param date the trade date
     * @param ticker the trade ticker
     * @param price the trade price
     * @param estimatedAmount the estimated trade amount
     * @param type the trade type
     */
    public Trade(
            final LocalDate date,
            final String ticker,
            final double price,
            final double estimatedAmount,
            final TradeType type) {
    }

    /**
     * Calculates estimated profit.
     *
     * @param laterPrice the later price
     * @return the estimated profit
     */
    public double calculateEstimatedProfit(final double laterPrice) {
        return 0.0;
    }

    /**
     * Gets the trade date.
     *
     * @return the trade date
     */
    public LocalDate getDate() {
        return null;
    }

    /**
     * Gets the trade ticker.
     *
     * @return the trade ticker
     */
    public String getTicker() {
        return null;
    }
}
