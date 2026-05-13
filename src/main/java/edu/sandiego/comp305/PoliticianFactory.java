package edu.sandiego.comp305;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Builds Politician objects from the scrubbed csv by
 * gathering all trades using TradeScrubber,
 * groups them by politician name
 * constructs a TradeHistory object for each politician
 * and returns a list of Politicians for drafting.
 *
 */
public class PoliticianFactory {

    private final TradeScrubber scrubbedCsv;

    /**
     * Constructor for PoliticianFactory
     *
     * @param scrubbedCsv the parsed CSV
     */
    public PoliticianFactory(final TradeScrubber scrubbedCsv) {
        this.scrubbedCsv = scrubbedCsv;
    }

    /**
     * Loads all politicians from a CSV file.
     *
     * @param csvPath path to the CSV file containing trade data
     * @return list of constructed politicians
     */
    public List<Politician> createPoliticianList(final String csvPath) {
        final List<Trade> allTrades =
                scrubbedCsv.buildTradesFromCSV(csvPath);

        final Map<String, List<Trade>> groupedCsv =
                groupTradesByName(allTrades);

        return buildEachPolitician(groupedCsv);
    }

    /**
     * Groups trades by politician name.
     *
     * @param allTrades list of all trades
     * @return map of politician name to their trades
     */
    private Map<String, List<Trade>> groupTradesByName(
            final List<Trade> allTrades) {

        final Map<String, List<Trade>> groupedTrades = new HashMap<>();

        for (final Trade indTrade : allTrades) {
            final String name = indTrade.politicianName();

            if (!groupedTrades.containsKey(name)) {
                groupedTrades.put(name, new ArrayList<>());
            }

            groupedTrades.get(name).add(indTrade);
        }

        return groupedTrades;
    }

    /**
     * Builds Politician objects from grouped trades
     * by adding their TradeHistory
     *
     * @param groupedTrades map of politician name to their trades
     * @return list of Politician objects
     */
    private List<Politician> buildEachPolitician(
            final Map<String, List<Trade>> groupedTrades) {

        final List<Politician> politicianList = new ArrayList<>();
        int idToBeAssigned = 1;

        for (final Map.Entry<String, List<Trade>>
                entry : groupedTrades.entrySet()) {
            final String name = entry.getKey();
            final List<Trade> namedTrades = entry.getValue();

            final TradeHistory history = new TradeHistory(namedTrades);

            final String party = namedTrades.get(0).party();

            final Politician constructedPolitician = new Politician(
                    name,
                    idToBeAssigned,
                    party,
                    history
            );

            politicianList.add(constructedPolitician);
            idToBeAssigned++;
        }

        return politicianList;
    }
}
