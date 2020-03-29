package exchangeOfficePAO.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class exchangeHouse {
    private int hiredEmployees, DeskNumbers;
    private allCurrencies currencies;
    private Set<Client> clients = new HashSet<Client>();
    private List<Employee> employees;
    private currenciesHistory currenciesHistory = exchangeOfficePAO.models.currenciesHistory.getCurrenciesHistory();
    private transactionHistory transactionHistory = exchangeOfficePAO.models.transactionHistory.getTransactionHistory();

    public exchangeHouse(int hiredEmployees, int deskNumbers, allCurrencies currencies, List<Employee> employees) {
        this.hiredEmployees = hiredEmployees;
        this.DeskNumbers = deskNumbers;
        this.currencies = currencies;
        this.employees = employees;
    }

    public int getHiredEmployees() {
        return hiredEmployees;
    }

    public void setHiredEmployees(int hiredEmployees) {
        this.hiredEmployees = hiredEmployees;
    }

    public int getDeskNumbers() {
        return DeskNumbers;
    }

    public void setDeskNumbers(int deskNumbers) {
        DeskNumbers = deskNumbers;
    }

    public allCurrencies getCurrencies() {
        return currencies;
    }

    public void setCurrencies(allCurrencies currencies) {
        this.currencies = currencies;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public currenciesHistory getCurrenciesHistory() {
        return currenciesHistory;
    }

    public transactionHistory getTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Employees: " + this.hiredEmployees + " Number of desks: " + this.DeskNumbers + "\n";
        result += "Employees: \n";
        for(Employee x : employees){
            result += x.toString();
        }
        result += "\n Currencies: \n";
        result += this.currencies.toString();
        return result;
    }
}
