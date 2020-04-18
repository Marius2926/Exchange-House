package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.repository.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionService {
    private TransactionRepository transactionRepository;

    public TransactionService() {
        this.transactionRepository = new TransactionRepository();
    }

    public void addTransaction(Transaction t){
        transactionRepository.addTransaction(t);
    }

    public List<Transaction> getTransactionRepository(){
        return transactionRepository.getTransactions();
    }

    public List<Transaction> getTransactionsAfterCNP(String cnp){
        return transactionRepository.getTransactionsAfterCNP(cnp);
    }

    public List<Transaction> getTransactionsAfterDate(Date date){
        return transactionRepository.getTransactionsAfterDate(date);
    }

    @Override
    public String toString() {
        String result = "Transactions { \n";
        for(Transaction t : this.getTransactionRepository())
            result += t.toString() + "\n";
        result += "\n}";
        return result;
    }
}
