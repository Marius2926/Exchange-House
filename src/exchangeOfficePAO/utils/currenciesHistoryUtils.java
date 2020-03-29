package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.allCurrencies;
import exchangeOfficePAO.models.currenciesHistory;

import java.util.Date;

public class currenciesHistoryUtils {
    public static void addCurrenciesHist(currenciesHistory currenciesHistory, Date date, allCurrencies currenciesAtDate){
        currenciesHistory.getCurrenciesMap().put(date, new allCurrencies(currenciesAtDate));
    }
}
