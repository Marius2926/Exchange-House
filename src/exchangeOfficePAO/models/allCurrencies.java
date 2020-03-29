package exchangeOfficePAO.models;

import java.util.ArrayList;
import java.util.List;

public class allCurrencies {
    private List<Currency> currencyList;

    public allCurrencies(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public allCurrencies() {
        this.currencyList = new ArrayList<Currency>();
    }

    public allCurrencies(allCurrencies currenciesGiven) {
        this.currencyList = new ArrayList<Currency>();
        for(Currency currency : currenciesGiven.getCurrencyList()){
            this.currencyList.add(currency);
        }
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void setCurrencyList(List<Currency> currencyList) {
        this.currencyList = new ArrayList<Currency>(currencyList);
    }

    @Override
    public String toString() {
        String result = "\t\t\t";
        for(Currency c : currencyList){
            result += c.getId() + "\t";
            result += c.getName();
            result += "\t Sell price: " + c.getSellPrice();
            result += "\t Buy price: " + c.getBuyPrice();
            result += "\t Available: " + c.getAvailable();
            result += "\n\t\t\t";
        }
        return result;
    }
}
