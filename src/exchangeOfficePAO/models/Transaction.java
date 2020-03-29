package exchangeOfficePAO.models;

import java.util.Date;

public class Transaction {
    static private int numberOfTransactions = 0;
    //tip 0 means the client sold currency, 1 means he bought currency
    private int tip;
    private String CNP;
    private int currencyId;
    private double value;
    private Date date;
    private int id;

    public Transaction(int tip, String CNP, int currencyId, double value, Date date) {
        this.tip = tip;
        this.CNP = CNP;
        this.currencyId = currencyId;
        this.value = value;
        this.date = date;
        this.id = ++numberOfTransactions;
    }

    public Transaction(Transaction transaction) {
        this.tip = transaction.tip;
        this.CNP = transaction.CNP;
        this.currencyId = transaction.currencyId;
        this.value = transaction.value;
        this.date = new Date(transaction.date.getTime());
    }

    public String getCNP() {
        return CNP;
    }

    public int getcurrencyId() {
        return currencyId;
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "tip='" + tip + '\'' +
                ", CNP='" + CNP + '\'' +
                ", currencyId=" + currencyId +
                ", value=" + value +
                ", date=" + date +
                "}\n";
    }
}
