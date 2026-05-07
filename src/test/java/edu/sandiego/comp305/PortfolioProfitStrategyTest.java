package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Tests for PortfolioProfitStrategy.
 */
class PortfolioProfitStrategyTest {

    @Test
    void calculateScore_nullPolitician_returnsZero() {
        PortfolioProfitStrategy strategy = new PortfolioProfitStrategy();

        double result = strategy.calculateScore(null, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_nullTradeHistory_returnsZero() {
        Politician politician = mock(Politician.class);

        when(politician.getTradeHistory()).thenReturn(null);

        PortfolioProfitStrategy strategy = new PortfolioProfitStrategy();

        double result = strategy.calculateScore(politician, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_noWeeklyTrades_returnsZero() {
        Politician politician = mock(Politician.class);
        TradeHistory tradeHistory = mock(TradeHistory.class);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyTrades(5)).thenReturn(new ArrayList<>());

        PortfolioProfitStrategy strategy = new PortfolioProfitStrategy();

        double result = strategy.calculateScore(politician, 5);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_validPolitician_sumsBuyAndSellTradeScores() {
        Politician politician = mock(Politician.class);
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Trade buyTrade = mock(Trade.class);
        Trade sellTrade = mock(Trade.class);

        List<Trade> weeklyTrades = new ArrayList<>();
        weeklyTrades.add(buyTrade);
        weeklyTrades.add(sellTrade);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyTrades(5)).thenReturn(weeklyTrades);

        when(buyTrade.getType()).thenReturn(TradeType.BUY);
        when(buyTrade.getEstimatedAmount()).thenReturn(1000.0);

        when(sellTrade.getType()).thenReturn(TradeType.SELL);
        when(sellTrade.getEstimatedAmount()).thenReturn(2500.0);

        PortfolioProfitStrategy strategy = new PortfolioProfitStrategy();

        double result = strategy.calculateScore(politician, 5);

        assertEquals(1500.0, result);
    }
}
