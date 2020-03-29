package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Client;
import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.models.exchangeHouse;
import exchangeOfficePAO.models.Currency;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

public class clientUtils {
    public static boolean wantExchange(Client client){
        if(client.checkValidity())
            return true;
        else
            return false;
    }
    //tip 0 means client sell currency, tip 1 means client buy currency
    public static Transaction createTransaction(int tip, int currencyId, int value,Client client, exchangeHouse exchangeHouse){
        Currency currency = allCurrenciesUtils.findCurrencyAfterID(exchangeHouse.getCurrencies(), currencyId);
        if(tip == 0){
            if(currency != null){
                currency.buyCurrency(value);
            }
            else
                return null;
            Transaction result = new Transaction(0, client.getCNP(), currencyId, value, new Date());
            exchangeHouse.getTransactionHistory().getTransactions().add(result);
            if(!isAlreadyClient(client, exchangeHouse))
                addClient(client, exchangeHouse);
            return result;
        }
        else{
            if(currency != null && currency.getAvailable() >= value){
                currency.sellCurrency(value);
            }
            else
                return null;
            Transaction result = new Transaction(1, client.getCNP(), currencyId, value, new Date());
            exchangeHouse.getTransactionHistory().getTransactions().add(result);
            if(!isAlreadyClient(client, exchangeHouse))
                addClient(client, exchangeHouse);
            return result;
        }
    }

    public static boolean isAlreadyClient(Client c, exchangeHouse exchangeHouse){
        Iterator<Client> i = exchangeHouse.getClients().iterator();
        while (i.hasNext()) {
            if (i.equals(c))
                return true;
            i.next();
        }
        return false;
    }

    public static void addClient(Client c, exchangeHouse exchangeHouse){
        exchangeHouse.getClients().add(c);
    }
}
