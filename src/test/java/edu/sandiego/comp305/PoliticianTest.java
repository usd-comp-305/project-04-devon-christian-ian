package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Tests for the Politician class.
 */
class PoliticianTest {

    @Test
    void constructor_setsName() {
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals("Nancy Pelosi", politician.getName());
    }

    @Test
    void constructor_setsIdNumber() {
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals(1, politician.getIdNumber());
    }

    @Test
    void constructor_setsParty() {
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals("DEM", politician.getParty());
    }

    @Test
    void constructor_setsTradeHistory() {
        TradeHistory tradeHistory = mock(TradeHistory.class);

        Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals(tradeHistory, politician.getTradeHistory());
    }
}