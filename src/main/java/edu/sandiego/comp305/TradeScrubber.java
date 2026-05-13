package edu.sandiego.comp305;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Converts CSV rows into Trade objects.
 */
public class TradeScrubber {

    //added to debug date issues
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("d MMM yyyy");

    /**
     * Splits CSV lines into columns, and columns into Trade objects.
     *
     * @param path The CSV file to access
     * @return A list of trades for TradeHistory,
     * to build into Politician profiles
     */
    public List<Trade> buildTradesFromCSV(final String path) {
        final List<String[]> csvRows = extractCsvRows(path);
        return buildTrades(csvRows);
    }

    /**
     * Reads CSV, skips header, ignores blank lines, splits lines into columns.
     *
     * @param csvPath The CSV file to access
     * @return A list of String arrays, one for each row
     */
    private List<String[]> extractCsvRows(final String csvPath) {
        final List<String[]> csvRows = new ArrayList<>();

        try (BufferedReader reader =
                     Files.newBufferedReader(
                             Path.of(csvPath),
                             StandardCharsets.UTF_8)) {

            reader.readLine();
            String line;

            while ((line = reader.readLine()) != null) {
                if (!line.isBlank()) {
                    csvRows.add(parseCSVLine(line));
                }
            }

        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

        return csvRows;
    }

    /**
     * CSV splitter, using a regex to handle quoted commas.
     *
     * @param line The CSV row to handle
     * @return String array of CSV columns for buildTrades
     */
    private String[] parseCSVLine(final String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    /**
     * Converts each CSV row into a Trade object.
     *
     * @param csvRows The array of strings to convert, from extractCSVRows()
     * @return List of all trades in the CSV
     */
    private List<Trade> buildTrades(final List<String[]> csvRows) {
        final List<Trade> trades = new ArrayList<>();

        for (final String[] column : csvRows) {

            // check that might be necessary for debugging
            if (column.length < 13) {
                continue;
            }

            final String politicianName = column[0].trim();
            final String party = column[1].trim();

            final LocalDate date = parseDate(column[7]);

            final String ticker = column[5].trim();
            final double price = parsePrice(column[12]);
            final double estimatedAmount = estimateAmount(column[11]);
            final TradeType type = parseType(column[10]);

            trades.add(new Trade(
                    date,
                    ticker,
                    price,
                    estimatedAmount,
                    type,
                    politicianName,
                    party));
        }

        return trades;
    }

    /**
     * Helper method to handle additional date formats.
     *
     * @param dateString the column entry for the date
     * @return LocalDate version to normalize all formats
     */
    private LocalDate parseDate(final String dateString) {
        final String normalizedDate = dateString.trim()
                .replace("Sept", "Sep");

        return LocalDate.parse(normalizedDate, DATE_FMT);
    }

    /**
     * Converts buy/sell into the TradeType enum. 
     *
     * @param type the column entry
     * @return enum matching the string entry
     */
    private TradeType parseType(final String type) {
        if (type.trim().equalsIgnoreCase("buy")) {
            return TradeType.BUY;
        }

        return TradeType.SELL;
    }

    /**
     * Parses the String price entry into usable double.
     *
     * @param priceString the column entry
     * @return The double required for trade calculations
     */
    private double parsePrice(final String priceString) {
        if (priceString == null
                || priceString.isBlank()
                || priceString.equalsIgnoreCase("N/A")) {
            return 0.0;
        }

        return Double.parseDouble(
                priceString.replace("$", "")
                        .replace(",", "")
                        .trim());
    }

    /**
     * Parses the trade range entry.
     *
     * @param range The initial string entry for the column
     * @return The converted double, needed for TradeHistory
     */
    private double estimateAmount(final String range) {
        if (range == null || range.isBlank()) {
            return 0.0;
        }

        final String normalizedRange = range.replace("–", "-");

        if (!normalizedRange.contains("-")) {
            return 0.0;
        }

        final String[] parts = normalizedRange.split("-");
        if (parts.length != 2) {
            return 0.0;
        }

        try {
            final double low =
                    Double.parseDouble(parts[0].replace("K", "").trim()) * 1000;
            final double high =
                    Double.parseDouble(parts[1].replace("K", "").trim()) * 1000;
            return (low + high) / 2.0;
        } catch (NumberFormatException exception) {
            return 0.0;
        }
    }
}
