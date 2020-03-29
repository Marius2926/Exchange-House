package exchangeOfficePAO.interfaces;

import exchangeOfficePAO.models.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface transactionsHistory {
    List<Transaction> transactions = new ArrayList<Transaction>();
    void addTransaction(Transaction transaction);
    void findTransaction(int idTransaction);
    void findTransactionByDate(Date date);
}
