package exchangeOfficePAO.models;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    //tip 0 means the client sold currency, 1 means he bought currency
    private int tip;
    private String CNP;
    private int currencyId;
    private double value;
    private LocalDate date;
    private int id;

    public Transaction(int tip, String CNP, int currencyId, double value, LocalDate date) {
        this.tip = tip;
        this.CNP = CNP;
        this.currencyId = currencyId;
        this.value = value;
        this.date = date;
    }

    public Transaction(int tip, String CNP, int currencyId, double value, LocalDate date, int id) {
        this.tip = tip;
        this.CNP = CNP;
        this.currencyId = currencyId;
        this.value = value;
        this.date = date;
        this.id = id;
    }

    public Transaction(Transaction transaction) {
        this.tip = transaction.tip;
        this.CNP = transaction.CNP;
        this.currencyId = transaction.currencyId;
        this.value = transaction.value;
        this.date = LocalDate.from(transaction.date);
        this.id = transaction.id;
    }

    public String getCNP() {
        return CNP;
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getId() {
        return id;
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
