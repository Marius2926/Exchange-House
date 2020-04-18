package exchangeOfficePAO.repository;

import exchangeOfficePAO.models.Client;

import java.util.*;

public class ClientRepository {
    Set<Client> clientSet;

    public ClientRepository() {
        this.clientSet = new HashSet<>();
    }

    public void addClient(Client c){
        clientSet.add(c);
    }

    public List<Client> getClienti(){
        return new ArrayList<>(clientSet);
    }

    public Client getClientAfterCNP(String cnp){
        for(Client client : clientSet){
            if(client.getCNP() == cnp)
                return client;
        }
        return null;
    }
}
