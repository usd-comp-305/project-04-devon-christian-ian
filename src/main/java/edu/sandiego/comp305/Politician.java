package edu.sandiego.comp305;

public class Politician {

    private String name;
    private int idNumber;
    private String party;
    private TradeHistory tradeHistory;

    public Politician(String name, int idNumber, String party, TradeHistory tradeHistory) {
        this.name = name;
        this.idNumber = idNumber;
        this.party = party;
        this.tradeHistory = tradeHistory;
    }

    public String getName() {
        return name;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getParty() {
        return party;
    }

    public TradeHistory getTradeHistory() {
        return tradeHistory;
    }
}