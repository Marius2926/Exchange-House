package exchangeOfficePAO.repository;

import exchangeOfficePAO.models.Transaction;
import exchangeOfficePAO.service.AuditService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionRepository {
    private List<Transaction> transactions;

    public TransactionRepository() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction t){
        transactions.add(t);
    }

    public List<Transaction> getTransactions(){
        return  transactions;
    }

    public List<Transaction> getTransactionsAfterCNP(String cnp){
        List<Transaction> specificTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            if(transaction.getCNP() == cnp){
                specificTransactions.add(new Transaction(transaction));
            }
        }
        return specificTransactions;
    }

    public List<Transaction> getTransactionsAfterDate(Date date){
        List<Transaction> specificTransactions = new ArrayList<Transaction>();
        for(Transaction transaction : transactions){
            if(transaction.getDate() == date){
                specificTransactions.add(new Transaction(transaction));
            }
        }
        return specificTransactions;
    }
}
