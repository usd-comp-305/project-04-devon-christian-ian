package edu.sandiego.comp305;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests PoliticianFactory only through the public method
 * while still indirectly testing the private helpers
 */
public class PoliticianFactoryTest {

    private PoliticianFactory factory;

    @BeforeEach
    public void testSetup() {
        TradeScrubber mockScrubber = mock(TradeScrubber.class);

        List<Trade> sampleTrades = List.of(
                new Trade(
                        LocalDate.of(2025, 1, 1),
                        "AAPL",
                        100.0,
                        5000.0,
                        TradeType.BUY,
                        "Alice",
                        "Democrat"
                ),
                new Trade(
                        LocalDate.of(2025, 1, 2),
                        "MSFT",
                        200.0,
                        7000.0,
                        TradeType.SELL,
                        "Bob",
                        "Republican"
                ),
                new Trade(
                        LocalDate.of(2025, 1, 3),
                        "TSLA",
                        300.0,
                        9000.0,
                        TradeType.BUY,
                        "Alice",
                        "Democrat"
                )
        );

        when(mockScrubber.buildTradesFromCSV("ignored.csv"))
                .thenReturn(sampleTrades);

        factory = new PoliticianFactory(mockScrubber);
    }

    @Test
    public void testTotalPoliticiansCreated() {
        List<Politician> result =
                factory.createPoliticianList("ignored.csv");

        assertEquals(2, result.size());
    }

    @Test
    public void testTradeAssignmentByName() {
        List<Politician> result =
                factory.createPoliticianList("ignored.csv");

        Politician alice = null;

        for (Politician p : result) {
            if (p.getName().equals("Alice")) {
                alice = p;
                break;
            }
        }

        assert alice != null;
        assertEquals(2,
                alice.getTradeHistory().getTrades().size());
    }

    @Test
    public void testPoliticianNotDuplicated() {
        List<Politician> result =
                factory.createPoliticianList("ignored.csv");

        Politician bob = null;

        for (Politician p : result) {
            if (p.getName().equals("Bob")) {
                bob = p;
                break;
            }
        }

        assert bob != null;
        assertEquals(1,
                bob.getTradeHistory().getTrades().size());
    }

    @Test
    public void testPartyAssignment() {
        List<Politician> result =
                factory.createPoliticianList("ignored.csv");

        Politician alice = null;

        for (Politician p : result) {
            if (p.getName().equals("Alice")) {
                alice = p;
                break;
            }
        }

        assertNotNull(alice);
        assertEquals("Democrat", alice.getParty());
    }

    @Test
    public void testIdAssignment() {
        List<Politician> result =
                factory.createPoliticianList("ignored.csv");

        assertEquals(1, result.get(0).getIdNumber());
        assertEquals(2, result.get(1).getIdNumber());
    }
}