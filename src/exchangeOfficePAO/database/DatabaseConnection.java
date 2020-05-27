package exchangeOfficePAO.database;

import exchangeOfficePAO.models.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/exchangehouse";
    private static final String username = "root";
    private static final String password = "";
    private static DatabaseConnection databaseConnection = null;

    private DatabaseConnection(){}

    synchronized static public DatabaseConnection getInstance(){
        if(databaseConnection == null){
            databaseConnection = new DatabaseConnection();
        }
        return databaseConnection;
    }

    synchronized public void insertEmployee(Employee employee){
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            String query;
            if (employee.getType() == 0) {
                query = "INSERT INTO `exchangehouse`.`employee`\n" +
                        "(`first_name`,\n" +
                        "`last_name`,\n" +
                        "`salary`,\n" +
                        "`hire_date`,\n" +
                        "`desk_number`,\n" +
                        "`job_id`)\n" +
                        "VALUES\n" +
                        "( '" +
                        employee.getFirstName() + "' , '" +
                        employee.getLastName() + "' ," +
                        employee.getSalary() + ", '" +
                        employee.getHireDate() + "' ," +
                        ((Cashier) employee).getDeskNo() + "," +
                        employee.getType() + ");";
            } else {
                query = "INSERT INTO `exchangehouse`.`employee`\n" +
                        "(`first_name`,\n" +
                        "`last_name`,\n" +
                        "`salary`,\n" +
                        "`hire_date`,\n" +
                        "`job_id`)\n" +
                        "VALUES\n" +
                        "('" +
                        employee.getFirstName() + "' , '" +
                        employee.getLastName() + "' ," +
                        employee.getSalary() + ", '" +
                        employee.getHireDate() + "' ," +
                        employee.getType() + ");";
            }
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    synchronized public void insertCurrency(Currency c){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            LocalDate now = LocalDate.now();
            String query = "INSERT INTO `exchangehouse`.`currency`\n" +
                    "(`currency_id`,\n" +
                    "`date`,\n" +
                    "`name`,\n" +
                    "`sell_price`,\n" +
                    "`buy_price`,\n" +
                    "`available`)\n" +
                    "VALUES\n" +
                    "( " +
                    c.getId()  + ",\n'" +
                    now + "',\n'" +
                    c.getName() + "', \n" +
                    c.getSellPrice() + ",\n" +
                    c.getBuyPrice() + ",\n" +
                    c.getAvailable() + ");\n";
            statement.executeUpdate(query);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private int getNewAddressId(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select coalesce(max(address_id), -1) 'id' from exchangehouse.address;";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()) {
                return resultSet.getInt("id") + 1;
            }else{
                return -1;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    synchronized private int insertClientAddress(Address a){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query;
            int availableId = getNewAddressId();
            if(availableId >= 0) {
                if (a.getTip() == 0) {
                    query = "INSERT INTO `exchangehouse`.`address`\n" +
                            "(`address_id`,\n" +
                            "`type`,\n" +
                            "`street`,\n" +
                            "`street_number`)\n" +
                            "VALUES\n(" +
                            availableId + ",\n" +
                            a.getTip() + ",\n'" +
                            a.getStreet() + "',\n" +
                            a.getNumberStreet() + ");";
                } else {
                    query = "INSERT INTO `exchangehouse`.`address`\n" +
                            "(`address_id`,\n" +
                            "`type`,\n" +
                            "`street`,\n" +
                            "`street_number`,\n" +
                            "`flat_number`,\n" +
                            "`floor`,\n" +
                            "`building_number`)\n" +
                            "VALUES\n(" +
                            availableId + ",\n" +
                            a.getTip() + ",\n'" +
                            a.getStreet() + "',\n" +
                            a.getNumberStreet() + ",\n" +
                            a.getNumberApartment() + ",\n" +
                            a.getFloor() + ",\n" +
                            a.getNumberBloc() + ");";
                }
                statement.executeUpdate(query);
                return availableId;
            }else{
                System.out.println("Addres insertion failed!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return -1;
    }


    synchronized public void insertClient(Client c){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            int address_id;
            if((address_id = insertClientAddress(c.getAddress())) >= 0) {
                c.getAddress().setAddressId(address_id);
                String query = "INSERT INTO `exchangehouse`.`client`\n" +
                        "(`cnp`,\n" +
                        "`address_id`,\n" +
                        "`first_name`,\n" +
                        "`last_name`)\n" +
                        "VALUES\n" +
                        "('" + c.getCNP() + "',\n" +
                        c.getAddress().getAddressId() + ",\n'" +
                        c.getFirstName() + "',\n'" +
                        c.getLastName() + "');";
                statement.executeUpdate(query);
            }else{
                System.out.println("Client insertion failed!");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    synchronized public void insertTransaction(Transaction t, Client c){ // it is supposed that the currency and the amount required exist
        Client client = getClientAfterCNP(t.getCNP());
        if(client == null){ //if it is a new client
            insertClient(c);
        }
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "INSERT INTO `exchangehouse`.`transaction`\n" +
                    "(`transaction_id`,\n" +
                    "`currency_id`,\n" +
                    "`date`,\n" +
                    "`cnp`,\n" +
                    "`type`,\n" +
                    "`value`)\n" +
                    "VALUES\n(" +
                    t.getId() + ",\n" +
                    t.getCurrencyId() + ",\n'" +
                    t.getDate() + "',\n'" +
                    t.getCNP() + "',\n" +
                    t.getTip() + ",\n" +
                    t.getValue() + ");\n";
            statement.executeUpdate(query);
            Currency currency = getCurrencyAfterIdDate(t.getCurrencyId(), LocalDate.now());
            if(t.getTip() == 0){  // the client sold the currency, so the exchange house buy it
                currency.setAvailable(currency.getAvailable() + t.getValue());
            }else{
                currency.setAvailable(currency.getAvailable() - t.getValue());
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Address getAddressAfterId(int address_id) {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.address where address_id = " + address_id + ";";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Address address = null;
                int type = resultSet.getInt("type");
                if(type == 0){
                    address = new Address(resultSet.getString("street"), resultSet.getInt("street_number"));
                }else{
                    address = new Address(resultSet.getString("street"), resultSet.getInt("street_number"),
                                          resultSet.getInt("building_number"), resultSet.getInt("floor"),
                                          resultSet.getInt("flat_number"));
                }
                return address;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Client getClientAfterCNP(String cnp){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.client where cnp = '" + cnp + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Address address = getAddressAfterId(resultSet.getInt("address_id"));
                Client client = new Client(resultSet.getString("first_name"), resultSet.getString("last_name"),
                                           address, resultSet.getString("cnp"));
                return client;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactionsAfterCNP(String cnp){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.transaction where cnp = '" + cnp + "';";
            ResultSet resultSet = statement.executeQuery(query);
            List<Transaction> transactionList = new ArrayList<>();
            if(resultSet.next()){
                LocalDate date;
                Transaction transaction;
                do{
                    date = new java.sql.Date(resultSet.getDate("date").getTime()).toLocalDate();
                    transaction = new Transaction(resultSet.getInt("type"), resultSet.getString("cnp"), resultSet.getInt("currency_id"), resultSet.getDouble("value"), date, resultSet.getInt("transaction_id"));
                    transactionList.add(transaction);
                }while (resultSet.next());
            }else{
                return null;
            }
            return transactionList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public Currency getCurrencyAfterIdDate(int currency_id, LocalDate date){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.currency where currency_id = " + currency_id + " and date = '" + date + "';";
            ResultSet resultSet = statement.executeQuery(query);
            if(resultSet.next()){
                Currency currency = new Currency(resultSet.getString("name"), resultSet.getDouble("sell_price"), resultSet.getDouble("buy_price"), resultSet.getDouble("available"), resultSet.getInt("currency_id"));
                return currency;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Currency> getCurrencyForDate(LocalDate date){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.currency where date = '" + date + "';";
            ResultSet resultSet = statement.executeQuery(query);
            List<Currency> currencyList = new ArrayList<>();
            if(resultSet.next()){
                Currency currency;
                do{
                    currency = new Currency(resultSet.getString("name"), resultSet.getDouble("sell_price"), resultSet.getDouble("buy_price"), resultSet.getDouble("available"), resultSet.getInt("currency_id"));
                    currencyList.add(currency);
                }while (resultSet.next());
            }else{
                return null;
            }
            return currencyList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    synchronized public void updateSellPriceCurrency(double sellPrice, int currencyId){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            LocalDate now = LocalDate.now();
            Statement statement = connection.createStatement();
            String query = "update exchangehouse.currency set sell_price = " + sellPrice + "where currency_id = " + currencyId + " and date = '" + now + "';";
            statement.executeUpdate(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    synchronized public void updateBuyPriceCurrency(double buyPrice, int currencyId){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            LocalDate now = LocalDate.now();
            Statement statement = connection.createStatement();
            String query = "update exchangehouse.currency set buy_price = " + buyPrice + "where currency_id = " + currencyId + " and date = '" + now + "';";
            statement.executeUpdate(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    synchronized public void updateAmountCurrency(double available, int currencyId){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            LocalDate now = LocalDate.now();
            Statement statement = connection.createStatement();
            String query = "update exchangehouse.currency set available = " + available + "where currency_id = " + currencyId + " and date = '" + now + "';";
            statement.executeUpdate(query);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Client> getClients(){
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.client cl join exchangehouse.address ad on cl.address_id = ad.address_id;";
            ResultSet resultSet = statement.executeQuery(query);
            List<Client> clientList = new ArrayList<>();
            if(resultSet.next()){
                Client client;
                Address address;
                do{
                    if(resultSet.getInt("type") == 0) {
                        address = new Address(resultSet.getString("street"), resultSet.getInt("street_number"));
                    }else{
                        address = new Address(resultSet.getString("street"), resultSet.getInt("street_number"),
                                              resultSet.getInt("building_number"), resultSet.getInt("floor"),
                                              resultSet.getInt("flat_number"));
                    }
                    address.setAddressId(resultSet.getInt("ad.address_id"));
                    client = new Client(resultSet.getString("first_name"), resultSet.getString("last_name"), address, resultSet.getString("cnp"));
                    clientList.add(client);
                }while (resultSet.next());
                return clientList;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getEmployees() {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.employee;";
            ResultSet resultSet = statement.executeQuery(query);
            List<Employee> employeeList = new ArrayList<>();
            int job_id;
            if(resultSet.next()){
                Employee employee;
                do{
                    employee = null;
                    job_id = resultSet.getInt("job_id");
                    switch (job_id){
                        case 0:
                            employee = new Cashier(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate(), resultSet.getInt("desk_number"));
                            break;
                        case 1:
                            employee = new Guardian(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate());
                            break;
                        case 2:
                            employee = new Janitor(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate());
                            break;
                        case 3:
                            employee = new Manager(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate());
                            break;
                        case 4:
                            employee = new Promoter(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate());
                            break;
                        case 5:
                            employee = new Supervisor(resultSet.getString("first_name"), resultSet.getString("last_name"), new java.sql.Date(resultSet.getDate("hire_date").getTime()).toLocalDate());
                            break;
                        default:
                            System.out.println("Unknown job_id found in database");
                    }
                    if(employee != null){
                        employeeList.add(employee);
                    }
                }while (resultSet.next());
                return employeeList;
            }else{
                return null;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactionsAfterDate(LocalDate date) {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.transaction where date = '" + date + "';";
            ResultSet resultSet = statement.executeQuery(query);
            List<Transaction> transactionList = new ArrayList<>();
            if(resultSet.next()){
                Transaction transaction;
                do{
                    transaction = new Transaction(resultSet.getInt("type"), resultSet.getString("cnp"), resultSet.getInt("currency_id"), resultSet.getDouble("value"), date, resultSet.getInt("transaction_id"));
                    transactionList.add(transaction);
                }while (resultSet.next());
            }else{
                return null;
            }
            return transactionList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<Transaction> getTransactions() {
        try(Connection connection = DriverManager.getConnection(url, username, password)){
            Statement statement = connection.createStatement();
            String query = "select * from exchangehouse.transaction;";
            ResultSet resultSet = statement.executeQuery(query);
            List<Transaction> transactionList = new ArrayList<>();
            if(resultSet.next()){
                LocalDate date;
                Transaction transaction;
                do{
                    date = new java.sql.Date(resultSet.getDate("date").getTime()).toLocalDate();
                    transaction = new Transaction(resultSet.getInt("type"), resultSet.getString("cnp"), resultSet.getInt("currency_id"), resultSet.getDouble("value"), date, resultSet.getInt("transaction_id"));
                    transactionList.add(transaction);
                }while (resultSet.next());
            }else{
                return null;
            }
            return transactionList;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
