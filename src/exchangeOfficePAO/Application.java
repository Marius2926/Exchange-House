package exchangeOfficePAO;

import exchangeOfficePAO.models.*;
import exchangeOfficePAO.models.Currency;
import exchangeOfficePAO.utils.*;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        final int numberEmployees = 9, deskNumbers = 8;
        //defining the exchange house
        Currency euro = new Currency("euro", 4.91, 4.77, 300);
        Currency dolarAmerican = new Currency("dolar american", 4.51, 4.3, 200);
        Currency liraSterlina = new Currency("lira sterlina", 5.5, 5.25, 250);
        Currency francElvetian = new Currency("franc elvetian", 4.62, 4.48, 400);
        Currency coronaSuedeze = new Currency("corona suedeza", 0.46, 0.42, 300);
        Currency dolarCanadian = new Currency("dolar canadian", 3.19, 3.05, 372);
        Currency coronaCeha = new Currency("corona ceha", 0.19, 0.165, 300);
        Currency zlotPolonez = new Currency("zlot polonez", 1.1, 1.04, 120);
        Currency leuMoldovenesc = new Currency("leu moldovenesc", 0.265, 0.23, 126);
        allCurrencies currencies = new allCurrencies();
        allCurrenciesUtils.addCurrency(currencies, euro);
        allCurrenciesUtils.addCurrency(currencies, dolarAmerican);
        allCurrenciesUtils.addCurrency(currencies, liraSterlina);
        allCurrenciesUtils.addCurrency(currencies, francElvetian);
        allCurrenciesUtils.addCurrency(currencies, coronaSuedeze);
        allCurrenciesUtils.addCurrency(currencies, dolarCanadian);
        allCurrenciesUtils.addCurrency(currencies, coronaCeha);
        allCurrenciesUtils.addCurrency(currencies, zlotPolonez);
        allCurrenciesUtils.addCurrency(currencies, leuMoldovenesc);
        List<Employee> employeesList = new ArrayList<Employee>();
        employeesList.add(new Cashier("Ana1", "Popescu", 3, 1));
        employeesList.add(new Cashier("Ana2", "Popeasca", 2, 2));
        employeesList.add(new Cashier("Ana3", "Ionescu", 1, 3));
        employeesList.add(new Cashier("Ana4", "Ioneasca", 1, 4));
        employeesList.add(new Cashier("Ana5", "Zamfirescu", 5, 5));
        employeesList.add(new Cashier("Ana6", "Zamfireasca", 6, 6));
        employeesList.add(new Cashier("Ana7", "Florescu", 0, 7));
        employeesList.add(new Cashier("Ana8", "Floreasca", 0, 8));
        employeesList.add(new Janitor("Lora","Viorescu", 3));
        employeesList.add(new Guardian("Dorel","Dorescu",3));
        employeesList.add(new Promoter("Aurica","Marcela",0));
        employeesList.add(new Manager("John","Arthur",13));
        employeesList.add(new Supervisor("Ionel","Hritcu",5));
        exchangeHouse exchangeHouse1 = new exchangeHouse(numberEmployees, deskNumbers, currencies, employeesList);
        //now we have the structure of the exchange house
        System.out.println(exchangeHouse1.toString());


        //now we have our first clients
        Client client1 = new Client("Client1", "Zaicescu", new Address("Zorilor", 32), "1960222333264");
        Client client2 = new Client("Client2", "Cernescu", new Address("Zorilor", 2, 4, 3, 25), "2960222333254");
        if(clientUtils.wantExchange(client1)){
            Transaction transactionClient1 = clientUtils.createTransaction(0, 2, 34000, client1, exchangeHouse1);
            if(transactionClient1 != null)
                System.out.println(client1.toString() + " made the transaction " + transactionClient1.toString());
            else{
                System.out.println("Exchange house doesn't have the funds.");
            }
        }
        else
            System.out.println(client1.toString() + " ARE DATELE INVALIDE! \n");
        if(clientUtils.wantExchange(client2)){
            Transaction transactionClient2 = clientUtils.createTransaction(1, 4, 20, client2, exchangeHouse1);
            if(transactionClient2 != null)
                System.out.println(client2.toString() + " made the transaction " + transactionClient2.toString());
            else{
                System.out.println("Exchange house doesn't have the funds.");
            }
        }
        else
            System.out.println(client2.toString() + " ARE DATELE INVALIDE! \n");

        Client client3 = new Client("Client3", "Cristea", new Address("Laleaua", 34, 4, 5, 37), "2960222333256");
        if(clientUtils.wantExchange(client3)){
            clientUtils.createTransaction(1, 4, 20, client3, exchangeHouse1);
            clientUtils.createTransaction(1, 6, 40, client3, exchangeHouse1);
            clientUtils.createTransaction(1, 8, 50, client3, exchangeHouse1);
            //bad id on purpose
            clientUtils.createTransaction(0, 24, 79, client3, exchangeHouse1);
            clientUtils.createTransaction(0, 7, 64, client3, exchangeHouse1);
        }
        else
            System.out.println(client3.toString() + " ARE DATELE INVALIDE! \n");


        System.out.println("\n####################################\nTRANSACTION HISTORY:");
        System.out.println(exchangeHouse1.getTransactionHistory().toString());

        System.out.println("\n####################################\nClients of the Exchange House: \n");
        System.out.println(exchangeHouse1.getClients());

        String CNPSearched = "2960222333256";
        //here i use sort
        List<Transaction> clientSearchedTransactions = transactionHistoryUtils.findTransactionsAfterCNP(exchangeHouse1.getTransactionHistory(), CNPSearched);
        Collections.sort(clientSearchedTransactions, new valueTransactionsComparator());
        System.out.println("\n####################################\nGet client transactions:");
        System.out.println(clientSearchedTransactions.toString());

        currenciesHistoryUtils.addCurrenciesHist(exchangeHouse1.getCurrenciesHistory(), new Date(), exchangeHouse1.getCurrencies());
        System.out.println("\n####################################\nGet currencies history:");
        System.out.println(exchangeHouse1.getCurrenciesHistory().getCurrenciesMap());

        Collections.sort(exchangeHouse1.getEmployees(), new salaryEmployeesComparator());
        System.out.println("\n####################################\nGet employees ordered by salary desc:");
        System.out.println(exchangeHouse1.getEmployees());
    }
}
