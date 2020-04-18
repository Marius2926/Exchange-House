package exchangeOfficePAO.repository;


import exchangeOfficePAO.models.Currency;

import java.util.*;

public class CurrenciesHistoryRepository {
    private Map<Date, CurrenciesRepository> currenciesMap;

    public CurrenciesHistoryRepository() {
        this.currenciesMap = new HashMap<>();
    }

    public void addCurrenciesInHistory(Date date, List<Currency> currencyList) {
        CurrenciesRepository currencyRepository = new CurrenciesRepository(currencyList);
        CurrenciesRepository newCurrencyRepository = new CurrenciesRepository(currencyRepository);
        currenciesMap.put(date, newCurrencyRepository);
    }

    public Map<Date, CurrenciesRepository> getAllHistory(){
        return currenciesMap;
    }

    public CurrenciesRepository getHistoryDay(Date date){
        if(currenciesMap.containsKey(date)){
            return currenciesMap.get(date);
        }
        else
            return null;
    }
}
