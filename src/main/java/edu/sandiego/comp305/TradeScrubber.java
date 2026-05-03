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
     * Opens the CSV
     * Skips header row, ignores blank lines
     * Splits each remaining line into columns
     *
     * @param path The CSV file to access
     * @return A list of String arrays, one for each row
     */
    public List<Trade> buildTradesFromCSV(String path) {
        List<String[]> csvRows = extractCsvRows(path);
        return buildTrades(csvRows);
    }


    /**
     * Opens the CSV file, skips header row, ignores blank lines
     * Splits each remaining line into columns
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

    private String[] parseCSVLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
    }

    private List<Trade> buildTrades(List<String[]> csvRows) {
        List<Trade> trades = new ArrayList<>();

        for (String[] column : csvRows) {
            LocalDate date = LocalDate.parse(column[7].trim(), DATE_FMT);
            String ticker = column[5].trim();
            double price = parsePrice(column[12]);
            double estimatedAmount = estimateAmount(column[11]);
            TradeType type = parseType(column[10]);

            trades.add(new Trade(date, ticker, price, estimatedAmount, type));
        }
        return trades;
    }

private TradeType parseType(String type) {
    return type.trim().equalsIgnoreCase("buy") ? TradeType.BUY : TradeType.SELL;
}

private double parsePrice(String priceString) {
    if (priceString == null || priceString.isBlank() || priceString.equalsIgnoreCase("N/A"))
        return 0.0;
    return Double.parseDouble(priceString.replace("$","").trim());
}
private double estimateAmount(String range) {
    if (range == null || !range.contains("-"))
        return 0.0;

    String[] amountParts = range.split("-");
    double low = Double.parseDouble(amountParts[0].replace("K", "").trim()) * 1000;
    double high = Double.parseDouble(amountParts[1].replace("K", "").trim()) * 1000;

    return (low + high) / 2.0;
}





}
