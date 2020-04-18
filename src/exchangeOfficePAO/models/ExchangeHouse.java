package exchangeOfficePAO.models;

import exchangeOfficePAO.exceptions.JobNotFoundException;
import exchangeOfficePAO.service.*;

import java.io.File;
import java.io.IOException;

public class ExchangeHouse {
    private int hiredEmployees, DeskNumbers;
    private CurrenciesService currencies;
    private ClientService clients;
    private EmployeeService employees;
    private CurrenciesHistoryService currenciesHistory;
    private TransactionService transactionHistory;
    private static String[] filePaths = {"./Files/currencies.csv", "./Files/employees.csv", "./Files/transactions.csv", "./Files/currenciesHistory.csv", "./Files/clients.csv"};

    public ExchangeHouse(int hiredEmployees, int deskNumbers, CurrenciesService currencies, EmployeeService employees) {
        this.hiredEmployees = hiredEmployees;
        this.DeskNumbers = deskNumbers;
        this.currencies = currencies;
        this.employees = employees;
        this.clients = new ClientService();
        this.currenciesHistory = new CurrenciesHistoryService();
        this.transactionHistory = new TransactionService();
    }

    public ExchangeHouse(int deskNumbers) {
        this.DeskNumbers = deskNumbers;
        this.currencies = new CurrenciesService();
        this.clients = new ClientService();
        this.employees = new EmployeeService();
        this.currenciesHistory = new CurrenciesHistoryService();
        this.transactionHistory = new TransactionService();
    }

    public CurrenciesService getCurrencies() {
        return currencies;
    }

    public void setCurrencies(CurrenciesService currencies) {
        this.currencies = currencies;
    }

    public ClientService getClients() {
        return clients;
    }

    public void setClients(ClientService clients) {
        this.clients = clients;
    }

    public EmployeeService getEmployees() {
        return employees;
    }

    public void setEmployees(EmployeeService employees) {
        this.employees = employees;
    }

    public CurrenciesHistoryService getCurrenciesHistory() {
        return currenciesHistory;
    }

    public void setCurrenciesHistory(CurrenciesHistoryService currenciesHistory) {
        this.currenciesHistory = currenciesHistory;
    }

    public TransactionService getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(TransactionService transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public void startDay(){
        IOService ioService = IOService.getInstance();
        try {
            this.currencies = ioService.readCurrencies(filePaths[0]);
            this.employees = ioService.readEmployees(filePaths[1]);
            File file = new File(filePaths[2]);
            if((file.exists() && file.isFile()) || file.length() != 0)
                this.transactionHistory = ioService.readTransactions(filePaths[2]);
            file = new File(filePaths[3]);
            if((file.exists() && file.isFile()) || file.length() != 0)
                this.currenciesHistory = ioService.readCurrenciesHistory(filePaths[3]);
            file = new File(filePaths[4]);
            if((file.exists() && file.isFile()) || file.length() != 0)
                this.clients = ioService.readClients(filePaths[4]);
            this.hiredEmployees = this.employees.getEmployeeRepository().size();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JobNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void closeDay(){
        IOService ioService = IOService.getInstance();
        try {
            ioService.writeCurrencies(filePaths[0], this.getCurrencies());
            ioService.writeEmployees(filePaths[1], this.getEmployees());
            ioService.writeTransactions(filePaths[2], this.getTransactionHistory());
            ioService.writeCurrenciesHistory(filePaths[3], this.getCurrenciesHistory());
            ioService.writeClients(filePaths[4], this.getClients());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String result = "";
        result += "Employees: " + this.hiredEmployees + " Number of desks: " + this.DeskNumbers + "\n";
        result += "Employees: \n";
        for(Employee x : employees.getEmployeeRepository()){
            result += x.toString();
        }
        result += "\n Currencies: \n";
        result += this.currencies.toString();
        return result;
    }
}
