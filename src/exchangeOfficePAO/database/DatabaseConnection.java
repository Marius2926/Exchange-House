package exchangeOfficePAO.database;

import exchangeOfficePAO.models.*;
import exchangeOfficePAO.service.AuditService;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection  extends DatabaseOperations{
    private static DatabaseConnection databaseConnection = null;
    private static int threadsNumber = 0;
    private DatabaseConnection(){}

    static public DatabaseConnection getInstance(){
        if(databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    public void insertEmployee(Employee employee){
        new Thread(() -> {
            try {
                super.insertEmployee(employee);
                AuditService.getInstance().writeAction("New employee hired " + employee.getLastName(), new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());}, "" + threadsNumber).start();
        threadsNumber++;
    }

    public void insertCurrency(Currency c){
        new Thread(() -> {
            try {
                super.insertCurrency(c);
                AuditService.getInstance().writeAction("Insert currency" + c.getName(), new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());}, "" + threadsNumber).start();
        threadsNumber++;
    }

//    public void insertClient(Client c){
//       new Thread(() -> {super.insertClient(c);
//           System.out.println(Thread.currentThread().getName());}, "" + threadsNumber).start();
//       threadsNumber++;
//    }

    public void insertTransaction(Transaction t, Client c){ // it is supposed that the currency and the amount required exist
        Thread insertTransaction = new Thread(() -> {
            try {
                super.insertTransaction(t, c);
                AuditService.getInstance().writeAction("New Transaction " + c.getCNP(), new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        insertTransaction.start();
        try {
            insertTransaction.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Address getAddressAfterId(int address_id) {
        final Address[] address = new Address[1];
        Thread getAddres = new Thread(() -> {
            try {
                address[0] = super.getAddressAfterId(address_id);
                if(address[0] == null){
                 AuditService.getInstance().writeAction("Tried to get address that doesn't exist - id = " + address_id, new java.util.Date().getTime(), Thread.currentThread().getName());
                }else {
                    AuditService.getInstance().writeAction("Get address " + address_id, new java.util.Date().getTime(), Thread.currentThread().getName());
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getAddres.start();
        try {
            getAddres.join();
            return address[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Client getClientAfterCNP(String cnp){
        final Client[] client = new Client[1];
        Thread getClient = new Thread(() -> {
            try {
                client[0] = super.getClientAfterCNP(cnp);
                if(client[0] != null) {
                    AuditService.getInstance().writeAction("Get client " + cnp, new java.util.Date().getTime(), Thread.currentThread().getName());
                }else {
                    AuditService.getInstance().writeAction("Tried to get client that doesn't exist - cnp =  " + cnp, new java.util.Date().getTime(), Thread.currentThread().getName());
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getClient.start();
        try {
            getClient.join();
            return client[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactionsAfterCNP(String cnp){
        var ref = new Object(){
            List<Transaction> transactionList;
        };
        Thread getTrans = new Thread(() -> {
            try {
                ref.transactionList = super.getTransactionsAfterCNP(cnp);
                AuditService.getInstance().writeAction("Get transactions for client " + cnp, new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getTrans.start();
        try {
            getTrans.join();
            return ref.transactionList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Currency getCurrencyAfterIdDate(int currency_id, LocalDate date){
        final Currency[] currency = new Currency[1];
        Thread getCurr = new Thread(() -> {
            try {
                currency[0] = super.getCurrencyAfterIdDate(currency_id, date);
                if(currency[0] != null) {
                    AuditService.getInstance().writeAction("Get currency after id = " + currency_id + " and date = " + date, new java.util.Date().getTime(), Thread.currentThread().getName());
                }else {
                    AuditService.getInstance().writeAction("Tried to get currency that doesn't exist - id = " + currency_id + " and date = " + date, new java.util.Date().getTime(), Thread.currentThread().getName());
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getCurr.start();
        try {
            getCurr.join();
            return currency[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Currency> getCurrencyForDate(LocalDate date){
        var ref = new Object(){
            List<Currency> currencyList;
        };
        Thread getCurrs = new Thread(() -> {
            try {
                ref.currencyList = super.getCurrencyForDate(date);
                AuditService.getInstance().writeAction("Get all currencies for date " + date, new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getCurrs.start();
        try {
            getCurrs.join();
            return ref.currencyList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Client> getClients(){
        var ref = new Object(){
            List<Client> clientList;
        };
        Thread getClients = new Thread(() -> {
            try {
                ref.clientList = super.getClients();
                AuditService.getInstance().writeAction("Get all clients", new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getClients.start();
        try {
            getClients.join();
            return ref.clientList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getEmployees() {
        var ref = new Object(){
            List<Employee> employeeList;
        };
        Thread getEmps = new Thread(() -> {
            try {
                ref.employeeList = super.getEmployees();
                AuditService.getInstance().writeAction("Get all employees", new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getEmps.start();
        try {
            getEmps.join();
            return ref.employeeList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Employee getEmployeeAfterId(int employeeId) {
        final Employee[] employee = new Employee[1];
        Thread getEmp = new Thread(() -> {
            try {
                employee[0] = super.getEmployeeAfterId(employeeId);
                if(employee[0] != null) {
                    AuditService.getInstance().writeAction("Get employee " + employeeId, new java.util.Date().getTime(), Thread.currentThread().getName());
                }else{
                    AuditService.getInstance().writeAction("Tried to get employee that doesn't exist - id = " + employeeId, new java.util.Date().getTime(), Thread.currentThread().getName());
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getEmp.start();
        try {
            getEmp.join();
            return employee[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactionsAfterDate(LocalDate date) {
        var ref = new Object() {
            List<Transaction> transactionList;
        };
        Thread getTrans = new Thread(() -> {
            try {
                ref.transactionList = super.getTransactionsAfterDate(date);
                AuditService.getInstance().writeAction("Get transaction after date " + date.toString(), new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getTrans.start();
        try {
            getTrans.join();
            return ref.transactionList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Transaction getTransactionsAfterId(int id) {
        final Transaction[] transaction = new Transaction[1];
        Thread getTran = new Thread(() -> {
            try {
                transaction[0] = super.getTransactionsAfterId(id);
                if(transaction[0] != null) {
                    AuditService.getInstance().writeAction("Get transaction " + id, new java.util.Date().getTime(), Thread.currentThread().getName());
                }else{
                    AuditService.getInstance().writeAction("Tried to get transaction that doesn't exist - id = " + id, new java.util.Date().getTime(), Thread.currentThread().getName());
                }
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getTran.start();
        try {
            getTran.join();
            return transaction[0];
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactions() {
        var ref = new Object() {
            List<Transaction> transactionList;
        };
        Thread getTrans = new Thread(() -> {
            try {
                ref.transactionList = super.getTransactions();
                AuditService.getInstance().writeAction("Get all transactions", new java.util.Date().getTime(), Thread.currentThread().getName());
            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }, "" + threadsNumber);
        threadsNumber++;
        getTrans.start();
        try {
            getTrans.join();
            return ref.transactionList;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateSellPriceCurrency(double sellPrice, int currencyId){
        super.updateSellPriceCurrency(sellPrice, currencyId);
    }

    public void updateBuyPriceCurrency(double buyPrice, int currencyId){
        super.updateBuyPriceCurrency(buyPrice, currencyId);
    }

    public void updateAmountCurrency(double available, int currencyId){
        super.updateAmountCurrency(available, currencyId);
    }

    public void removeTransaction(int idTransaction){
        super.removeTransaction(idTransaction);
    }

    public void fireEmployee(int employeeId){
        super.fireEmployee(employeeId);
    }

}
