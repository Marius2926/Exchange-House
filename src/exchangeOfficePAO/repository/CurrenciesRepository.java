package exchangeOfficePAO.repository;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.service.AuditService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CurrenciesRepository {
    private List<Currency> currencyList;

    public CurrenciesRepository() {
        this.currencyList  = new ArrayList<>();
    }

    public CurrenciesRepository(CurrenciesRepository currenciesRepository) {
        this.currencyList = new ArrayList<>();
        List<Currency> otherList = currenciesRepository.getCurrencyList();
        for(int i = 0; i < otherList.size(); i++)
            this.currencyList.add(otherList.get(i));
    }

    public CurrenciesRepository(List<Currency> currencyList){
        this.currencyList = new ArrayList<>();
        for(int i = 0; i < currencyList.size(); i++)
            this.currencyList.add(currencyList.get(i));
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
