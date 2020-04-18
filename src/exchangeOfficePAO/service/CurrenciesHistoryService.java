package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.repository.CurrenciesHistoryRepository;
import exchangeOfficePAO.repository.CurrenciesRepository;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class CurrenciesHistoryService {
    private CurrenciesHistoryRepository currenciesHistoryRepository;

    public CurrenciesHistoryService() {
        this.currenciesHistoryRepository = new CurrenciesHistoryRepository();
    }

    public void addCurrenciesInHistory(Date date, List<Currency> currencyList){
        currenciesHistoryRepository.addCurrenciesInHistory(date, currencyList);
    }

    public Map<Date, CurrenciesRepository> getAllHistory(){
        return currenciesHistoryRepository.getAllHistory();
    }

    public CurrenciesRepository getHistoryDay(Date date){
        return currenciesHistoryRepository.getHistoryDay(date);
    }

    @Override
    public String toString() {
        String result = "Currences history { \n";
        CurrenciesRepository currenciesRepository;
        for(Date date : this.getAllHistory().keySet()){
            currenciesRepository = this.getHistoryDay(date);
            result += "{ " + date + "\n{";
            for(Currency c : currenciesRepository.getCurrencyList())
                result += "\t" + c.toString() + "\n";
            result += "}\n";
        }
        result += "}\n";
        return result;
    }
}
