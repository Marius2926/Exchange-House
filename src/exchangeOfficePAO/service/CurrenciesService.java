package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.repository.CurrenciesRepository;

import java.time.LocalDate;
import java.util.List;

public class CurrenciesService {
    private CurrenciesRepository currenciesRepository;

    public CurrenciesService() {
        this.currenciesRepository = new CurrenciesRepository();
    }

    public void addCurrency(Currency c) {
        currenciesRepository.addCurrency(c);
    }

    public List<Currency> getCurrenciesRepository(LocalDate date) {
        return currenciesRepository.getCurrencyList(date);
    }

    public Currency findCurrencyAfterIdDate(int id, LocalDate date) {
        return currenciesRepository.findCurrencyAfterIdDate(id, date);
    }

    @Override
    public String toString() {
        String result =  "currencies{\n ";
        for(Currency c : this.getCurrenciesRepository(LocalDate.now()))
            result += c.toString() + "\n";
        result += "\n}";
        return result;
    }
}