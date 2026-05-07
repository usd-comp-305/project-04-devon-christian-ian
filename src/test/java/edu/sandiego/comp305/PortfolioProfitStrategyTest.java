package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    void calculateScore_validPolitician_sumsWeeklyTradeScores() {
        Politician politician = mock(Politician.class);
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Trade firstTrade = mock(Trade.class);
        Trade secondTrade = mock(Trade.class);

        List<Trade> weeklyTrades = new ArrayList<>();
        weeklyTrades.add(firstTrade);
        weeklyTrades.add(secondTrade);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyTrades(5)).thenReturn(weeklyTrades);

        when(firstTrade.calculateEstimatedProfit(0.0)).thenReturn(-1000.0);
        when(secondTrade.calculateEstimatedProfit(0.0)).thenReturn(2500.0);

        PortfolioProfitStrategy strategy = new PortfolioProfitStrategy();

        double result = strategy.calculateScore(politician, 5);

        assertEquals(1500.0, result);
    }
}