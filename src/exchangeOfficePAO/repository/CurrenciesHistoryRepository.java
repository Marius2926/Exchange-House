package exchangeOfficePAO.repository;


import exchangeOfficePAO.models.Currency;

import java.time.LocalDate;
import java.util.*;

public class CurrenciesHistoryRepository {
    private Map<LocalDate, List<Currency>> currenciesMap;

    public CurrenciesHistoryRepository() {
        this.currenciesMap = new HashMap<>();
    }

    public void addCurrenciesInHistory(LocalDate date, List<Currency> currencyList) {
        List<Currency> currencyList1 = new ArrayList<>(currencyList);
        currenciesMap.put(date, currencyList1);
    }

    public Map<LocalDate, List<Currency>> getAllHistory(){
        return currenciesMap;
    }

    public List<Currency> getHistoryDay(LocalDate date){
        return currenciesMap.getOrDefault(date, null);
    }
}
