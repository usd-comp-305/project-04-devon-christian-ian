package edu.sandiego.comp305;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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
    public List<Trade> buildTradeHistoryFromCSV(String path) {
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

        return List.of();
    }


}
