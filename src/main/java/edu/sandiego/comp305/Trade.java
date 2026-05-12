package edu.sandiego.comp305;

import java.time.LocalDate;

/**
 * A single financial trade disclosed by a US politician.
 * Each Trade is immutable, and contains parsed values
 * pulled from the year-long trade data CSV.
 *
 * @param date The date the trade was executed
 * @param ticker The stock ticker symbol
 * @param price The price per share at the time of the trade
 * @param estimatedAmount The midpoint of the reported size range
 * @param type Whether the trade was a Buy or Sell, stored as an enum
 * @param politicianName Name of the politician who made the trade
 * @param party Political party of the politician
 */
public record Trade(LocalDate date,
                    String ticker,
                    double price,
                    double estimatedAmount,
                    TradeType type,
                    String politicianName,
                    String party) {

    public LocalDate getDate() {
        return date;
    }

    public String getTicker() {
        return ticker;
    }

    public double getPrice() {
        return price;
    }

    public double getEstimatedAmount() {
        return estimatedAmount;
    }

    public TradeType getType() {
        return type;
    }

    public String getPoliticianName() {
        return politicianName;
    }

    public String getParty() {
        return party;
    }
}
