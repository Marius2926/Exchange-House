package exchangeOfficePAO.models;

import java.util.List;

public class allCurrencies {
    List<Currency> currencyList;

    public allCurrencies(List<Currency> currencyList) {
        this.currencyList = currencyList;
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    @Override
    public String toString() {
        String result = "List of currencies: ";
        for(Currency c : currencyList){
            result += c.getName();
            result += "Sell price: " + c.getSellPrice();
            result += "Buy price: " + c.getBuyPrice();
            result += "\n\t";
        }
        return result;
    }
}
