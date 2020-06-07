package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.repository.CurrenciesHistoryRepository;
import exchangeOfficePAO.repository.CurrenciesRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CurrenciesHistoryService {
    private CurrenciesHistoryRepository currenciesHistoryRepository;

    public CurrenciesHistoryService() {
        this.currenciesHistoryRepository = new CurrenciesHistoryRepository();
    }

    public void addCurrenciesInHistory(LocalDate date, List<Currency> currencyList){
        currenciesHistoryRepository.addCurrenciesInHistory(date, currencyList);
    }

    public Map<LocalDate,  List<Currency>> getAllHistory(){
        return currenciesHistoryRepository.getAllHistory();
    }

    public  List<Currency> getHistoryDay(LocalDate date){
        return currenciesHistoryRepository.getHistoryDay(date);
    }

    @Override
    public String toString() {
        String result = "Currences history { \n";
        CurrenciesRepository currenciesRepository;
        for(LocalDate date : this.getAllHistory().keySet()){
            List<Currency> currencyList = this.getHistoryDay(date);
            result += "{ " + date + "\n{";
            for(Currency c : currencyList)
                result += "\t" + c.toString() + "\n";
            result += "}\n";
        }
        result += "}\n";
        return result;
    }
}
