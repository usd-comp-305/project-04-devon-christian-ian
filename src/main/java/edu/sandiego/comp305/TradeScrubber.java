package edu.sandiego.comp305;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 */
public class TradeScrubber {

    public List<Politician> buildTradesFromCSV(String path) {
        List<Politician> politicians = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");

                int idNumber = Integer.parseInt(values[0].trim());
                String name = values[1].trim();
                String party = values[2].trim();
                LocalDate date = LocalDate.parse(values[3].trim());
                String ticker = values[4].trim();
                double price = Double.parseDouble(values[5].trim());
                double estimatedAmount = Double.parseDouble(values[6].trim());
                TradeType type = TradeType.valueOf(values[7].trim().toUpperCase());

                Trade trade = new Trade(date, ticker, price, estimatedAmount, type);

                Politician politician = findPoliticianById(politicians, idNumber);

                if (politician == null) {
                    TradeHistory tradeHistory = new TradeHistory();
                    tradeHistory.addTrade(trade);

                    politician = new Politician(name, idNumber, party, tradeHistory);
                    politicians.add(politician);
                } else {
                    politician.getTradeHistory().addTrade(trade);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        }

        return politicians;
    }

    private Politician findPoliticianById(List<Politician> politicians, int idNumber) {
        for (Politician politician : politicians) {
            if (politician.getIdNumber() == idNumber) {
                return politician;
            }
        }

        return null;
    }
}
