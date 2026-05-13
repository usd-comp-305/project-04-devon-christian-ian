package edu.sandiego.comp305;

/**
 * Represents a politician that can be drafted onto a team.
 */
public class Politician {

    private final String name;

    private final int idNumber;

    private final String party;

    private final TradeHistory tradeHistory;

    /**
     * Creates a politician.
     *
     * @param name politician name
     * @param idNumber politician ID number
     * @param party politician party
     * @param tradeHistory politician trade history
     */
    public Politician(
            final String name,
            final int idNumber,
            final String party,
            final TradeHistory tradeHistory) {
        this.name = name;
        this.idNumber = idNumber;
        this.party = party;
        this.tradeHistory = tradeHistory;
    }

    /**
     * Gets politician name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets politician ID number.
     *
     * @return ID number
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * Gets politician party.
     *
     * @return party
     */
    public String getParty() {
        return party;
    }

    /**
     * Gets politician trade history.
     *
     * @return trade history
     */
    public TradeHistory getTradeHistory() {
        return tradeHistory;
    }
}
