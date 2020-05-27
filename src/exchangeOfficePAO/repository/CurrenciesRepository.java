package exchangeOfficePAO.repository;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.models.Currency;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CurrenciesRepository {

    public CurrenciesRepository(){}

    public List<Currency> getCurrencyList(LocalDate date) {
        return DatabaseConnection.getInstance().getCurrencyForDate(date);
    }

    public void addCurrency(Currency c){
        DatabaseConnection.getInstance().insertCurrency(c);
    }

    public Currency findCurrencyAfterIdDate(int id, LocalDate date){
        return DatabaseConnection.getInstance().getCurrencyAfterIdDate(id, date);
    }
}
