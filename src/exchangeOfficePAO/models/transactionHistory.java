package exchangeOfficePAO.models;

import java.util.ArrayList;
import java.util.List;

public class transactionHistory {
    private static List<Transaction> transactions;
    private static transactionHistory transactionHistory;
    private transactionHistory(){
        transactions = new ArrayList<Transaction>();
    }
    public static transactionHistory getTransactionHistory(){
        if(transactionHistory == null)
            transactionHistory = new transactionHistory();
        return transactionHistory;
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    @Override
    public String toString() {
        String result = "";
        for(Transaction x : transactions)
            result += x.toString();
        return result;
    }
}
