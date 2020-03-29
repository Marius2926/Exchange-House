package exchangeOfficePAO.models;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//singletone
public class currenciesHistory {
    private static Map<Date, allCurrencies> currenciesMap;
    private static currenciesHistory currenciesHistory;
    private currenciesHistory(){
        currenciesMap  = new HashMap<Date, allCurrencies>();
    }
    public static currenciesHistory getCurrenciesHistory(){
        if(currenciesHistory == null){
            currenciesHistory = new currenciesHistory();
        }
        return currenciesHistory;
    }
    public static Map<Date, allCurrencies> getCurrenciesMap() {
        return currenciesMap;
    }
}
