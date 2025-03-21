package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Adresse;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.dal.ClientRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositorie clientRepositorie;

    public Client ajouterClient(Client client) {
        return clientRepositorie.save(client);
    }

    public List<Client> trouverClientsParNom(String nom) {
        return clientRepositorie.findByNomContainingIgnoreCase(nom);
    }

    public Client modifierClient(Integer id, Client clientDetails) {
        return clientRepositorie.findById(id).map(client -> {
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setEmail(clientDetails.getEmail());
            client.setNoTelephone(clientDetails.getNoTelephone());
            client.setAdresse(clientDetails.getAdresse());
            return clientRepositorie.save(client);
        }).orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public Client modifierAdresseClient(Integer id, Adresse nouvelleAdresse) {
        return clientRepositorie.findById(id).map(client -> {
            client.setAdresse(nouvelleAdresse);
            return clientRepositorie.save(client);
        }).orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }
}
