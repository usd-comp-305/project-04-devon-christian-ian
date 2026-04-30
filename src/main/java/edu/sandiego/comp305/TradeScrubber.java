package edu.sandiego.comp305;

import java.util.List;

/**
 *
 */
public class TradeScrubber {

    /**
     * Opens the CSV
     * Skips header row, ignores blank lines
     * Splits each remaining line into columns
     *
     * @param path The CSV file to access
     * @return A list of String arrays, one for each row
     */
    public List<Politician> buildTradesFromCSV(String path) {
        List<String[]> csvRows = parseLines(path);
        List<Trade> allTrades = buildTrades(csvRows);
        return addTradeHistorytoPoliticians(csvRows, allTrades);
    }

    /**
     * Opens the CSV
     * Skips header row, ignores blank lines
     * Splits each remaining line into columns
     *
     * @param path The CSV file to access
     */
    private void parseLines(String path) {
        return;
    }
    private List<Trade> buildTrades(List<String[]> csvRows) {
        return List.of();
    }

    private List<Politician> addTradeHistorytoPoliticians(List<String[]> csvRows, List<Trade> allTrades) {
        return List.of();
    }


}
