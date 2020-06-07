package exchangeOfficePAO.repository;

import exchangeOfficePAO.database.DatabaseConnection;
import exchangeOfficePAO.models.Client;

import java.util.*;

public class ClientRepository {

    public ClientRepository(){}

    public void addClient(Client c){
        DatabaseConnection.getInstance().insertClient(c);
    }

    public List<Client> getClienti(){
        return DatabaseConnection.getInstance().getClients();
    }

    public Client getClientAfterCNP(String cnp){
        for(Client client : this.getClienti()){
            if(client.getCNP() == cnp)
                return client;
        }
        return null;
    }
}
