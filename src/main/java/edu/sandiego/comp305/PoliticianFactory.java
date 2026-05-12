package edu.sandiego.comp305;

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
        return null;
    }

    /**
     * Builds Politician objects from grouped trades.
     *
     * @param groupedTrades map of politician name to their trades
     * @return list of Politician objects
     */
    private List<Politician> buildEachPolitician(
            final Map<String, List<Trade>> groupedTrades) {
        return null;
    }
}
