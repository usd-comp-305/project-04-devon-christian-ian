package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeHistoryTest {

    private Trade mockTrade(int month) {
        Trade trade = mock(Trade.class);
        LocalDate date = mock(LocalDate.class);

        when(date.getMonthValue()).thenReturn(month);
        when(trade.getDate()).thenReturn(date);

        return trade;
    }

    @Test
    void tradesPlacedInCorrectWeek() {
        Trade janTestTrade = mockTrade(1);
        Trade febTestTrade = mockTrade(2);

        TradeHistory testHistory = new TradeHistory(List.of(janTestTrade,febTestTrade));

        assertEquals(List.of(febTestTrade), testHistory.getWeeklyTrades(2));
    }

    @Test
    void emptyListReturnedForInvalidWeek() {
        Trade janTestTrade = mockTrade(1);

        TradeHistory testHistory = new TradeHistory(List.of(janTestTrade));

        assertTrue(testHistory.getWeeklyTrades(0).isEmpty());
    }

    @Test
    void getTradesReturnsMultipleTrades() {
        Trade testTradeJan = mockTrade(1);
        Trade testTradeFeb = mockTrade(2);
        Trade testTradeMar = mockTrade(3);

        TradeHistory testHistory = new TradeHistory(List.of(testTradeJan, testTradeFeb, testTradeMar));
        List<Trade> collectedTrades = testHistory.getTrades();

        assertTrue(collectedTrades.containsAll(List.of(testTradeJan, testTradeFeb, testTradeMar)));
    }
}
