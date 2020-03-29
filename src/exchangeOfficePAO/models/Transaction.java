package exchangeOfficePAO.models;

import java.util.Date;

public class Transaction {
    static private int numberOfTransactions = 0;
    private String CNP;
    private int currencySoldId, CurrencyBoughtId;
    private double valueSold, valueBought;
    private Date date;
    private int id;

    public Transaction(String CNP, int currencySoldId, int CurrencyBoughtId, double valueSold, double valueBought, Date date) {
        this.CNP = CNP;
        this.currencySoldId = currencySoldId;
        this.CurrencyBoughtId = CurrencyBoughtId;
        this.valueSold = valueSold;
        this.valueBought = valueBought;
        this.date = date;
        this.id = ++numberOfTransactions;
    }

    public String getCNP() {
        return CNP;
    }

    public int getcurrencySoldId() {
        return currencySoldId;
    }

    public int getCurrencyBoughtId() {
        return CurrencyBoughtId;
    }

    public double getValueSold() {
        return valueSold;
    }

    public void setValueSold(double valueSold) {
        this.valueSold = valueSold;
    }

    public double getValueBought() {
        return valueBought;
    }

    public void setValueBought(double valueBought) {
        this.valueBought = valueBought;
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
                "CNP='" + CNP + '\'' +
                ", currencySoldId=" + currencySoldId +
                ", CurrencyBoughtId=" + CurrencyBoughtId +
                ", valueSold=" + valueSold +
                ", valueBought=" + valueBought +
                ", date=" + date +
                '}';
    }
}
