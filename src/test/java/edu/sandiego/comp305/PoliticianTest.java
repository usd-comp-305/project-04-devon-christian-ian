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
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        final Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals("Nancy Pelosi", politician.getName());
    }

    @Test
    void constructor_setsIdNumber() {
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        final Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals(1, politician.getIdNumber());
    }

    @Test
    void constructor_setsParty() {
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        final Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals("DEM", politician.getParty());
    }

    @Test
    void constructor_setsTradeHistory() {
        final TradeHistory tradeHistory = mock(TradeHistory.class);

        final Politician politician = new Politician(
                "Nancy Pelosi",
                1,
                "DEM",
                tradeHistory
        );

        assertEquals(tradeHistory, politician.getTradeHistory());
    }
}



