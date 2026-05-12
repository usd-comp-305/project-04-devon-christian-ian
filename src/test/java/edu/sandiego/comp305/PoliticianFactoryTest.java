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
        final TradeScrubber mockScrubber = mock(TradeScrubber.class);

        final List<Trade> sampleTrades = List.of(
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
        final List<Politician> testResult =
                factory.createPoliticianList("ignored.csv");

        assertEquals(2, testResult.size());
    }

    @Test
    public void testTradeAssignmentByName() {
        final List<Politician> testResult =
                factory.createPoliticianList("ignored.csv");

        Politician alice = null;

        for (Politician p : testResult) {
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
        final List<Politician> testResult =
                factory.createPoliticianList("ignored.csv");

        Politician bob = null;

        for (Politician p : testResult) {
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
        final List<Politician> testResult =
                factory.createPoliticianList("ignored.csv");

        Politician alice = null;

        for (Politician p : testResult) {
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
        final List<Politician> testResult =
                factory.createPoliticianList("ignored.csv");

        assertEquals(2, testResult.get(1).getIdNumber());
    }
}
