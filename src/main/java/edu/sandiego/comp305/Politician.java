package edu.sandiego.comp305;

/**
 *
 */
public class Politician {

    private final String name;
    private final int idNumber;
    private final String party;
    private final TradeHistory tradeHistory;

    /**
     *
     * @param name
     * @param idNumber
     * @param party
     * @param tradeHistory
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
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     *
     * @return
     */
    public String getParty() {
        return party;
    }

    /**
     *
     * @return
     */
    public TradeHistory getTradeHistory() {
        return tradeHistory;
    }
}