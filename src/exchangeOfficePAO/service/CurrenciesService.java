package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.repository.CurrenciesRepository;

import java.util.List;

public class CurrenciesService {
    private CurrenciesRepository currenciesRepository;

    public CurrenciesService() {
        this.currenciesRepository = new CurrenciesRepository();
    }

    public void addCurrency(Currency c) {
        currenciesRepository.addCurrency(c);
    }

    public List<Currency> getCurrenciesRepository() {
        return currenciesRepository.getCurrencyList();
    }

    public Currency findCurrencyAfterID(int id) {
        return currenciesRepository.findCurrencyAfterID(id);
    }

    @Override
    public String toString() {
        String result =  "currencies{\n ";
        for(Currency c : this.getCurrenciesRepository())
            result += c.toString() + "\n";
        result += "\n}";
        return result;
    }
}