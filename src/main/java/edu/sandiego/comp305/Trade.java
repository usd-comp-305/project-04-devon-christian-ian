package edu.sandiego.comp305;

import java.time.LocalDate;

public class Trade {

    private LocalDate date;
    private String ticker;
    private double price;
    private double estimatedAmount;
    private TradeType type;

    public Trade(LocalDate date, String ticker, double price, double estimatedAmount, TradeType type) {
        this.date = date;
        this.ticker = ticker;
        this.price = price;
        this.estimatedAmount = estimatedAmount;
        this.type = type;
    }

    public double calculateEstimatedProfit(double laterPrice) {
        return 0;
    }

    public String getTicker() {
        return ticker;
    }
}