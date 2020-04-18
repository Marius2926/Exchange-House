package exchangeOfficePAO.repository;

import exchangeOfficePAO.models.Currency;

import java.util.ArrayList;
import java.util.List;

public class CurrenciesRepository {
    private List<Currency> currencyList;

    public CurrenciesRepository() {
        this.currencyList  = new ArrayList<>();
    }

    public CurrenciesRepository(CurrenciesRepository currenciesRepository) {
        this.currencyList = new ArrayList<>(currenciesRepository.getCurrencyList());
    }

    public CurrenciesRepository(List<Currency> currencyList){
        this.currencyList = new ArrayList<>(currencyList);
    }

    public List<Currency> getCurrencyList() {
        return currencyList;
    }

    public void addCurrency(Currency c){
        currencyList.add(c);
    }

    public Currency findCurrencyAfterID(int id){
        for(Currency currency : currencyList){
            if(currency.getId() == id)
                return currency;
        }
        return null;
    }
}
