package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Client;
import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.models.ExchangeHouse;
import exchangeOfficePAO.models.Currency;

import java.io.IOException;
import java.time.LocalDate;

public class clientUtils {
    public static boolean wantExchange(Client client){
        if(client.checkValidity())
            return true;
        else
            return false;
    }

    //tip 0 means client sell currency, tip 1 means client buy currency
    public static Transaction createTransaction(int tip, int currencyId, int value, Client client, ExchangeHouse exchangeHouse){
        Currency currency = exchangeHouse.getCurrencies().findCurrencyAfterIdDate(currencyId, LocalDate.now());
        Transaction result;
        if(currency == null)
            return null;
        if(tip == 0){
            currency.buyCurrency(value);
            result = new Transaction(0, client.getCNP(), currencyId, value, LocalDate.now());
        }
        else{
            if(currency.getAvailable() >= value)
                currency.sellCurrency(value);
            else
                return null;
            result = new Transaction(1, client.getCNP(), currencyId, value, LocalDate.now());
        }
        exchangeHouse.getTransactionHistory().addTransaction(result, client);
        return result;
    }
}
