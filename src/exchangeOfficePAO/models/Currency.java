package exchangeOfficePAO.models;

import exchangeOfficePAO.interfaces.Transactional;
import exchangeOfficePAO.interfaces.changebleRates;

public class Currency implements changebleRates, Transactional {
    private static int numberOfCurrencies = 0;
    private String name;
    private double sellPrice, buyPrice, available;
    private int id;

    public Currency(String name, double sellPrice, double buyPrice, double available) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
        this.available = available;
        this.id = numberOfCurrencies;
        numberOfCurrencies++;
    }

    @Override
    public void setNewSellPrice(double newSellPrice) {
        this.sellPrice = newSellPrice;
    }

    @Override
    public void setNewBuyPrice(double newBuyPrice) {
        this.buyPrice = newBuyPrice;
    }

    public static int getNumberOfCurrencies() {
        return numberOfCurrencies;
    }

    @Override
    public void buyCurrency(int value) {
        this.available = this.available + value;
    }

    @Override
    public void sellCurrency(int value) {
        this.available = this.available - value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getAvailable() {
        return available;
    }

    public void setAvailable(double available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

}
