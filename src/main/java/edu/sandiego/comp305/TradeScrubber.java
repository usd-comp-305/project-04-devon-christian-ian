package edu.sandiego.comp305;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TradeScrubber {

    //added to debug date issues
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Splits CSV lines into columns, and columns into Trade objects
     *
     * @param path The CSV file to access
     * @return A list of Trades for TradeHistory to build into Politician profiles
     */
    public List<Trade> buildTradesFromCSV(String path) {
        List<String[]> csvRows = extractCsvRows(path);
        return buildTrades(csvRows);
    }


    /**
     * Reads CSV, skips header, ignores blank lines, splits lines into columns
     *
     * @param csvPath The CSV file to access
     * @return A list of String arrays, one for each row
     */
    private List<String[]> extractCsvRows(String csvPath) {
        List<String[]> csvRows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvPath))) {
            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    csvRows.add(parseCSVLine(line));
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return csvRows;
    }

    /**
     * CSV splitter, using a regex to handle quoted commas
     * @param line The CSV row to handle
     * @return String array of CSV columns for buildTrades
     */
    private String[] parseCSVLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    /**
     * Converts each CSV row into a Trade object
     * @param csvRows The array of strings to convert, from extractCSVRows()
     * @return List of all trades in the CSV
     */
    private List<Trade> buildTrades(List<String[]> csvRows) {
        List<Trade> trades = new ArrayList<>();

        for (String[] column : csvRows) {

            // check that might be necessary for debugging
            if (column.length < 13) continue;

            LocalDate date = LocalDate.parse(column[7].trim(), DATE_FMT);
            String ticker = column[5].trim();
            double price = parsePrice(column[12]);
            double estimatedAmount = estimateAmount(column[11]);
            TradeType type = parseType(column[10]);

            trades.add(new Trade(date, ticker, price, estimatedAmount, type));
        }
        return trades;
    }

    /**
     * Converts buy/sell into the TradeType enum
     * @param type the column entry
     * @return enum matching the string entry
     */
    private TradeType parseType(String type) {
        return type.trim().equalsIgnoreCase("buy") ? TradeType.BUY : TradeType.SELL;
    }

    /**
     * Parses the String price entry into useable double
     * @param priceString the column entry
     * @return The double required for trade calculations
     */
    private double parsePrice(String priceString) {
        if (priceString == null || priceString.isBlank() || priceString.equalsIgnoreCase("N/A"))
            return 0.0;
        return Double.parseDouble(priceString.replace("$","")
                        .replace(",", "")
                        .trim());
    }

    /**
     * Parses the trade range entry
     * @param range The initial string entry for the column
     * @return The converted double, needed for TradeHistory
     */
    private double estimateAmount(String range) {
        if (range == null || range.isBlank())
            return 0.0;

        // Normalize en dash to hyphen
        range = range.replace("–", "-");

        if (!range.contains("-"))
            return 0.0;

        String[] parts = range.split("-");
        if (parts.length != 2)
            return 0.0;

        try {
            double low = Double.parseDouble(parts[0].replace("K", "").trim()) * 1000;
            double high = Double.parseDouble(parts[1].replace("K", "").trim()) * 1000;
            return (low + high) / 2.0;
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
