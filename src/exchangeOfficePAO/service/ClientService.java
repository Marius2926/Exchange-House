package exchangeOfficePAO.service;

import exchangeOfficePAO.models.Client;
import exchangeOfficePAO.repository.ClientRepository;

import java.util.List;

public class ClientService {
    private ClientRepository clientRepository;

    public ClientService() {
        this.clientRepository = new ClientRepository();
    }

    public void addClient(Client c) {
        clientRepository.addClient(c);
    }

    public List<Client> getClienti() {
        return clientRepository.getClienti();
    }

    public Client getClientAfterCNP(String cnp) {
        return clientRepository.getClientAfterCNP(cnp);
    }

    @Override
    public String toString() {
        String result = "Clients { \n";
        for(Client c : this.getClienti())
            result += c.toString() + "\n";
        result += "\n}";
        return result;
    }
}
