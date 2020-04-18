package exchangeOfficePAO.models;

import exchangeOfficePAO.service.*;

public class exchangeHouse {
    private int hiredEmployees, DeskNumbers;
    private CurrenciesService currencies;
    private ClientService clients;
    private EmployeeService employees;
    private CurrenciesHistoryService currenciesHistory;
    private TransactionService transactionHistory;

    public exchangeHouse(int hiredEmployees, int deskNumbers, CurrenciesService currencies, EmployeeService employees) {
        this.hiredEmployees = hiredEmployees;
        this.DeskNumbers = deskNumbers;
        this.currencies = currencies;
        this.employees = employees;
        this.clients = new ClientService();
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
