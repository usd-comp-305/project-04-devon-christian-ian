package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TradeScrubberTest {

    private static final String TEST_TRADE_CSV = """
        Politician,Party,Chamber,State,Traded Issuer,Ticker,Published,Trade Date,Days Filed After,Owner,Type,Size Range,Price
        John Doe,Democrat,Senate,CA,Apple,AAPL,1 Jan 2025,1 Jan 2025,0,Self,buy,1K–15K,$150.00
        John Doe,Democrat,Senate,CA,Microsoft,MSFT,2 Jan 2025,2 Jan 2025,0,Self,sell,15K–50K,$300.00
        Jane Smith,Republican,Senate,TX,Amazon,AMZN,3 Jan 2025,3 Jan 2025,0,Spouse,sell,50K–100K,$200.00
    """;

    private Path writeTestCSV() throws Exception {
        Path tempPath = Files.createTempFile("trades", ".csv");
        Files.writeString(tempPath, TradeScrubberTest.TEST_TRADE_CSV);
        return tempPath;
    }

    @Test
    void buildTradesFromCSVParsesPricesCorrectly() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber scrubber = new TradeScrubber();

        List<Trade> trades = scrubber.buildTradesFromCSV(testPath.toString());

        assertEquals(150.00, trades.getFirst().getPrice());
    }

    @Test
    void buildTradesFromCSVParsesDatesCorrectly() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber scrubber = new TradeScrubber();

        List<Trade> trades = scrubber.buildTradesFromCSV(testPath.toString());

        assertEquals(LocalDate.of(2025, 1, 1), trades.getFirst().getDate());
    }

    @Test
    void buildTradesFromCSVParsesTradeTypesCorrectly() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber scrubber = new TradeScrubber();

        List<Trade> trades = scrubber.buildTradesFromCSV(testPath.toString());

        assertEquals(TradeType.BUY, trades.getFirst().getType());
    }

    @Test
    void buildTradesFromCSVParsesSizeRangesCorrectly() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber scrubber = new TradeScrubber();

        List<Trade> trades = scrubber.buildTradesFromCSV(testPath.toString());

        assertEquals(8000.0, trades.get(0).getEstimatedAmount());     // midpoint of 1K–15K
        assertEquals(32500.0, trades.get(1).getEstimatedAmount());    // midpoint of 15K–50K
        assertEquals(75000.0, trades.get(2).getEstimatedAmount());    // midpoint of 50K–100K
    }




    @Test
    void buildTradesFromCSVCorrectSize() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber testScrubber = new TradeScrubber();

        List<Trade> outputList = testScrubber.buildTradesFromCSV(testPath.toString());

        assertEquals(3, outputList.size());
    }

    @Test
    void buildTradesFromCSVCorrectTicker() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber testScrubber = new TradeScrubber();

        List<Trade> outputList = testScrubber.buildTradesFromCSV(testPath.toString());

        assertEquals("AAPL", outputList.getFirst().getTicker());
    }



}
