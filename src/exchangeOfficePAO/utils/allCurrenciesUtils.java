package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.models.allCurrencies;

import javax.swing.plaf.ColorUIResource;
import java.util.List;

public class allCurrenciesUtils {
    public static void addCurrency(allCurrencies currencies, Currency newCurency){
        List<Currency> listaCurrencies = currencies.getCurrencyList();
        listaCurrencies.add(newCurency);
        currencies.setCurrencyList(listaCurrencies);
    }

    public static Currency findCurrencyAfterID(allCurrencies currencies, int id){
        List<Currency> listaCurrencies = currencies.getCurrencyList();
        for(Currency currency : listaCurrencies){
            if(currency.getId() == id)
                return currency;
        }
        return null;
    }
}
