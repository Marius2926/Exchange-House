package exchangeOfficePAO.repository;


import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.service.AuditService;

import java.io.IOException;
import java.util.*;

public class CurrenciesHistoryRepository {
    private Map<Date, CurrenciesRepository> currenciesMap;

    public CurrenciesHistoryRepository() {
        this.currenciesMap = new HashMap<>();
    }

    public void addCurrenciesInHistory(Date date, List<Currency> currencyList) {
        CurrenciesRepository currencyRepository = new CurrenciesRepository(currencyList);
        currenciesMap.put(date, currencyRepository);
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
