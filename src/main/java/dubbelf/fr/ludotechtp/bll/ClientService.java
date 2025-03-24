package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Adresse;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.dal.AdresseRepositorie;
import dubbelf.fr.ludotechtp.dal.ClientRepositorie;
import dubbelf.fr.ludotechtp.dto.ClientDTO;
import dubbelf.fr.ludotechtp.dto.EditClientDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepositorie clientRepositorie;

    private final AdresseRepositorie adresseRepositorie;

    public Client ajouterClient(ClientDTO clientDTO) {
        // Création de l'adresse
        Adresse adresse = new Adresse();
        adresse.setRue(clientDTO.getRue());
        adresse.setCodePostal(clientDTO.getCodePostal());
        adresse.setVille(clientDTO.getVille());
        adresse = adresseRepositorie.save(adresse); // Sauvegarde en BD

        // Création du client avec l'adresse
        Client client = new Client();
        client.setNom(clientDTO.getNom());
        client.setPrenom(clientDTO.getPrenom());
        client.setEmail(clientDTO.getEmail());
        client.setNoTelephone(clientDTO.getNoTelephone());
        client.setAdresse(adresse); // Association de l'adresse

        return clientRepositorie.save(client);
    }

    public List<Client> trouverClientsParNom(String nom) {
        return clientRepositorie.findByNomContainingIgnoreCase(nom);
    }

    public Client modifierClient(Integer id, EditClientDTO clientDetails) {
        return clientRepositorie.findById(id).map(client -> {
            client.setNom(clientDetails.getNom());
            client.setPrenom(clientDetails.getPrenom());
            client.setEmail(clientDetails.getEmail());
            client.setNoTelephone(clientDetails.getNoTelephone());
            return clientRepositorie.save(client);
        }).orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public Client modifierAdresseClient(Integer id, Adresse nouvelleAdresse) {
        return clientRepositorie.findById(id).map(client -> {
            client.setAdresse(nouvelleAdresse);
            return clientRepositorie.save(client);
        }).orElseThrow(() -> new RuntimeException("Client non trouvé"));
    }

    public Optional<Client> trouverClientParId(int id) {
        return clientRepositorie.findById(id);
    }

    public Optional<List<Client>> trouverClientParName(String name) {
        return Optional.ofNullable(clientRepositorie.findByNomContainingIgnoreCase(name));

    }

    public void supprimerClient(int id) {
        clientRepositorie.deleteById(id);
    }

    public List<Client> trouvertoutlesclients() {
        return clientRepositorie.findAll();
    }
}
