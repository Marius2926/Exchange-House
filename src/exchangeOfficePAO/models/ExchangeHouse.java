package exchangeOfficePAO.models;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.service.*;

import java.util.ArrayList;
import java.util.List;


public class ExchangeHouse {
    private int hiredEmployees, DeskNumbers = 8;
    private CurrenciesService currencies;
    private ClientService clients;
    private EmployeeService employees;
    private CurrenciesHistoryService currenciesHistory;
    private TransactionService transactionHistory;

    public ExchangeHouse(int hiredEmployees, int deskNumbers, CurrenciesService currencies, EmployeeService employees) {
        this.hiredEmployees = hiredEmployees;
        this.DeskNumbers = deskNumbers;
        this.currencies = currencies;
        this.employees = employees;
        this.clients = new ClientService();
        this.currenciesHistory = new CurrenciesHistoryService();
        this.transactionHistory = new TransactionService();
    }

    public DatabaseConnection getDatabaseConnection() {
        return DatabaseConnection.getInstance();
    }

    public ExchangeHouse() {
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

    public void updateCurrenciesForToday(){
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Currency("euro", 4.95, 4.76, 5000, 0));
        currencyList.add(new Currency("dolar american", 4.55, 4.32, 5000, 1));
        currencyList.add(new Currency("lira sterlina", 5.52, 5.20, 5000, 2));
        currencyList.add(new Currency("franc elvetian", 4.65, 4.45, 5000, 3));
        currencyList.add(new Currency("corona suedeza", 0.5, 0.43, 5000, 4));
        currencyList.add(new Currency("dolar canadian", 3.25, 3.09, 5000, 5));
        currencyList.add(new Currency("corona ceha", 0.2, 0.14, 5000, 6));
        currencyList.add(new Currency("zlot polonez", 1.2, 1.03, 5000, 7));
        currencyList.add(new Currency("leu moldovenesc", 0.23, 0.20, 5000, 8));
        currencyList.forEach(this.getDatabaseConnection()::insertCurrency);
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
