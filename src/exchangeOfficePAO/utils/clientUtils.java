package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Client;
import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.models.exchangeHouse;
import exchangeOfficePAO.models.Currency;

import java.util.Date;

public class clientUtils {
    public static boolean wantExchange(Client client){
        if(client.checkValidity())
            return true;
        else
            return false;
    }

    //tip 0 means client sell currency, tip 1 means client buy currency
    public static Transaction createTransaction(int tip, int currencyId, int value, Client client, exchangeHouse exchangeHouse){
        Currency currency = exchangeHouse.getCurrencies().findCurrencyAfterID(currencyId);
        Transaction result;
        if(currency == null)
            return null;
        if(tip == 0){
            currency.buyCurrency(value);
            result = new Transaction(0, client.getCNP(), currencyId, value, new Date());
        }
        else{
            if(currency.getAvailable() >= value)
                currency.sellCurrency(value);
            else
                return null;
            result = new Transaction(1, client.getCNP(), currencyId, value, new Date());
        }
        exchangeHouse.getTransactionHistory().addTransaction(result);
        if(exchangeHouse.getClients().getClientAfterCNP(client.getCNP()) == null)
            exchangeHouse.getClients().addClient(client);
        return result;
    }
}
