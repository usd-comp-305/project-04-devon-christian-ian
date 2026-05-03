package edu.sandiego.comp305;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
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
    void parseLinesReadsRows() throws Exception {
        Path testPath = writeTestCSV();
        TradeScrubber scrubber = new TradeScrubber();

        var method = TradeScrubber.class.getDeclaredMethod("parseCSVLine", String.class);
        method.setAccessible(true);

        @SuppressWarnings("unchecked")
        List<String[]> rows = (List<String[]>) method.invoke(scrubber, testPath.toString());

        assertEquals(3, rows.size());
        assertEquals("John Doe", rows.get(0)[0]);
        assertEquals("AAPL", rows.get(0)[5]);
        assertEquals("Jane Smith", rows.get(2)[0]);
        assertEquals(13, rows.get(0).length);
    }




}
