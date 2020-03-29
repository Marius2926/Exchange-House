package exchangeOfficePAO.utils;

import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.models.transactionHistory;

import java.util.ArrayList;
import java.util.List;

public class transactionHistoryUtils {
    public static List<Transaction> findTransactionsAfterCNP(transactionHistory transactions, String cnp){
        List<Transaction> specificTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions.getTransactions()){
            if(transaction.getCNP() == cnp){
                specificTransactions.add(new Transaction(transaction));
            }
        }
        return specificTransactions;
    }
}
