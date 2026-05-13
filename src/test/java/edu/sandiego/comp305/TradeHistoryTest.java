package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TradeHistoryTest {

    private Trade mockTrade(final int month) {
        final Trade trade = mock(Trade.class);
        final LocalDate date = mock(LocalDate.class);

        when(date.getMonthValue()).thenReturn(month);
        when(trade.getDate()).thenReturn(date);

        return trade;
    }

    @Test
    void tradesPlacedInCorrectWeek() {
        final Trade janTestTrade = mockTrade(1);
        final Trade febTestTrade = mockTrade(2);

        final TradeHistory testHistory =
                new TradeHistory(List.of(janTestTrade, febTestTrade));

        assertEquals(List.of(febTestTrade), testHistory.getWeeklyTrades(2));
    }

    @Test
    void emptyListReturnedForInvalidWeek() {
        final Trade janTestTrade = mockTrade(1);

        final TradeHistory testHistory =
                new TradeHistory(List.of(janTestTrade));

        assertTrue(testHistory.getWeeklyTrades(0).isEmpty());
    }

    @Test
    void getTradesReturnsMultipleTrades() {
        final Trade testTradeJan = mockTrade(1);
        final Trade testTradeFeb = mockTrade(2);
        final Trade testTradeMar = mockTrade(3);

        final TradeHistory testHistory =
                new TradeHistory(
                        List.of(testTradeJan, testTradeFeb, testTradeMar)
                );

        final List<Trade> collectedTrades = testHistory.getTrades();

        assertTrue(
                collectedTrades.containsAll(
                        List.of(testTradeJan, testTradeFeb, testTradeMar)
                )
        );
    }
}
