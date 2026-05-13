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
        final PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy();

        final double result = strategy.calculateScore(null, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_nullTradeHistory_returnsZero() {
        final Politician politician = mock(Politician.class);

        when(politician.getTradeHistory()).thenReturn(null);

        final PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy();

        final double result = strategy.calculateScore(politician, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_noWeeklyTrades_returnsZero() {
        final Politician politician = mock(Politician.class);
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyTrades(5)).thenReturn(new ArrayList<>());

        final PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy();

        final double result = strategy.calculateScore(politician, 5);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_validPolitician_sumsBuyAndSellTradeScores() {
        final Politician politician = mock(Politician.class);
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        final Trade buyTrade = mock(Trade.class);
        final Trade sellTrade = mock(Trade.class);

        final List<Trade> weeklyTrades = new ArrayList<>();
        weeklyTrades.add(buyTrade);
        weeklyTrades.add(sellTrade);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyTrades(5)).thenReturn(weeklyTrades);

        when(buyTrade.getType()).thenReturn(TradeType.BUY);
        when(buyTrade.getEstimatedAmount()).thenReturn(1000.0);
        when(buyTrade.getPrice()).thenReturn(100.0);

        when(sellTrade.getType()).thenReturn(TradeType.SELL);
        when(sellTrade.getEstimatedAmount()).thenReturn(2500.0);
        when(sellTrade.getPrice()).thenReturn(50.0);

        final PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy();

        final double result = strategy.calculateScore(politician, 5);

        assertEquals(1500.0, result);
    }
}
