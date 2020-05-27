package exchangeOfficePAO.repository;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.models.Client;
import exchangeOfficePAO.models.Transaction;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionRepository {

    public TransactionRepository(){
    }

    public void addTransaction(Transaction t, Client c){
        DatabaseConnection.getInstance().insertTransaction(t, c);
    }

    public List<Transaction> getTransactions(){
        return  DatabaseConnection.getInstance().getTransactions();
    }

    public List<Transaction> getTransactionsAfterCNP(String cnp){
        return DatabaseConnection.getInstance().getTransactionsAfterCNP(cnp);
    }

    public List<Transaction> getTransactionsAfterDate(LocalDate date){
        return DatabaseConnection.getInstance().getTransactionsAfterDate(date);
    }
}
