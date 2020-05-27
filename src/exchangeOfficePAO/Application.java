package exchangeOfficePAO;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.models.*;
import exchangeOfficePAO.utils.clientUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args){
        ExchangeHouse exchangeHouse = new ExchangeHouse();
//        exchangeHouse.updateCurrenciesForToday(); // this is needed if this is the first time the app runs in a day
        Address addressClient1 = new Address("Izvorului", 9);
        Client client1 = new Client("Gigel", "Ion", addressClient1, "1000128256874");


        Address addressClient2 = new Address("Izvorului", 9, 45, 2, 25);
        Client client2 = new Client("Gigica", "Lavinia", addressClient2, "2010128256874");


        Address addressClient3 = new Address("Metalurgiei", 9);
        Client client3 = new Client("Gigica", "Dorel", addressClient3, "1001168258875");

//        if(clientUtils.wantExchange(client1)){
//            if(clientUtils.createTransaction(0, 1, 200, client1, exchangeHouse) == null){
//                System.out.println("Nu se poate executa schimbul!");
//            }else{
//                System.out.println("Tranzactia a fost reusitea!");
//            }
//        }else{
//            System.out.println(client1.toString() + " nu are datele valide!");
//        }


//        if(clientUtils.wantExchange(client2)){
//            if(clientUtils.createTransaction(0, 1, 600, client2, exchangeHouse) == null){
//                System.out.println("Nu se poate executa schimbul!");
//            }else{
//                System.out.println("Tranzactia a fost reusitea!");
//            }
//        }else{
//            System.out.println(client1.toString() + " nu are datele valide!");
//        }

//        if(clientUtils.wantExchange(client3)){
//            if(clientUtils.createTransaction(3, 1, 1000, client3, exchangeHouse) == null){
//                System.out.println("Nu se poate executa schimbul!");
//            }else{
//                System.out.println("Tranzactia a fost reusitea!");
//            }
//        }else{
//            System.out.println(client1.toString() + " nu are datele valide!");
//        }

        //afisam angajatii
        System.out.println(exchangeHouse.getEmployees().toString());

        //afisam clientii
        System.out.println(exchangeHouse.getClients().toString());

        //afisam tranzactiile unui client
        List<Transaction> transactionList = exchangeHouse.getTransactionHistory().getTransactionsAfterCNP("1000128256874");
        if(transactionList == null) //clientul nu exista
            System.out.println("Clientul cu cnp-ul " + "1000128256874" + "nu exista\n");
        else {
            System.out.println("Clientul cautat are urmatoarele tranzactii: \n");
            transactionList.forEach(System.out::println);
        }

        //afisam toate tranzactiile din istoric
        System.out.println(exchangeHouse.getTransactionHistory().toString());

        //afisam toate tranzactiile dintr-o zi
        List<Transaction> transactionList1 = exchangeHouse.getTransactionHistory().getTransactionsAfterDate(LocalDate.now());
        if(transactionList1 == null){
            System.out.println("Nu exista tranzactii pentru ziua ceruta!\n");
        }else{
            System.out.println("In ziua " + LocalDate.now() + " au avut loc urmatoare tranzactii: \n");
            transactionList1.forEach(System.out::println);
        }


        //cautam cursul ce l-a avut o valuta dupa id si dupa zi
        Currency currency = exchangeHouse.getCurrencies().findCurrencyAfterIdDate(0, LocalDate.of(2020, 5, 26));
        if(currency == null){
            System.out.println("Nu exista date referitoare la cursul valutei in ziua ceruta!\n");
        }else{
            System.out.println("Cursul avut de moneda ceruta la date ceruta: \n" + currency);
        }

        //facem update la pretul de vanzare/cumparare sau la suma disponibila a unei valute pentru ziua curenta
        exchangeHouse.getDatabaseConnection().updateBuyPriceCurrency(0.45, 4);
//        exchangeHouse.getDatabaseConnection().updateSellPriceCurrency(0.53, 4);
//        exchangeHouse.getDatabaseConnection().updateAmountCurrency(50000, 4);
    }

}
