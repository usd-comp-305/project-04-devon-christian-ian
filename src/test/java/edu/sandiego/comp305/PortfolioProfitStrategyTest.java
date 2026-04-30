package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PortfolioProfitStrategyTest {

    @Test
    void calculateScore_nullPolitician_returnsZero() {
        PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy(new HashMap<>());

        double result = strategy.calculateScore(null, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_nullTradeHistory_returnsZero() {
        Politician politician = mock(Politician.class);

        when(politician.getTradeHistory()).thenReturn(null);

        PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy(new HashMap<>());

        double result = strategy.calculateScore(politician, 1);

        assertEquals(0.0, result);
    }

    @Test
    void calculateScore_validPolitician_returnsWeeklyProfit() {
        Politician politician = mock(Politician.class);
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Map<String, Double> prices = new HashMap<>();
        prices.put("AAPL", 200.0);

        Map<String, Double> copiedPrices = new HashMap<>(prices);

        when(politician.getTradeHistory()).thenReturn(tradeHistory);
        when(tradeHistory.getWeeklyProfit(5, copiedPrices))
                .thenReturn(300.0);

        PortfolioProfitStrategy strategy =
                new PortfolioProfitStrategy(prices);

        double result = strategy.calculateScore(politician, 5);

        assertEquals(300.0, result);
    }
}