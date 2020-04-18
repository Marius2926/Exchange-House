package exchangeOfficePAO;

import exchangeOfficePAO.models.*;
import exchangeOfficePAO.utils.clientUtils;

public class Application {
    public static void main(String[] args){
        final int deskNumbers = 8;
        ExchangeHouse exchangeHouse = new ExchangeHouse(deskNumbers);
        exchangeHouse.startDay(); // this method will read the existing files and update the exchange house
//        Client client1 = new Client("Ioana", "Zmeu", new Address("Soarelui", 32), "4860222333264");
//        Client client2 = new Client("Smaranda", "Grigorescu", new Address("Morilor", 2, 5, 3, 25), "2960222333254");
//        if(clientUtils.wantExchange(client1)){
//            Transaction transactionClient1 = clientUtils.createTransaction(0, 2, 34000, client1, exchangeHouse);
//            if(transactionClient1 != null)
//                System.out.println(client1.toString() + " made the transaction " + transactionClient1.toString());
//            else{
//                System.out.println("Exchange house doesn't have the funds.");
//            }
//        }
//        else
//            System.out.println(client1.toString() + " ARE DATELE INVALIDE! \n");
//        if(clientUtils.wantExchange(client2)){
//            Transaction transactionClient2 = clientUtils.createTransaction(1, 7, 20, client2, exchangeHouse);
//            if(transactionClient2 != null)
//                System.out.println(client2.toString() + " made the transaction " + transactionClient2.toString());
//            else{
//                System.out.println("Exchange house doesn't have the funds.");
//            }
//        }
//        else
//            System.out.println(client2.toString() + " ARE DATELE INVALIDE! \n");
        exchangeHouse.closeDay(); // this method will save the new data to the files
    }
}
