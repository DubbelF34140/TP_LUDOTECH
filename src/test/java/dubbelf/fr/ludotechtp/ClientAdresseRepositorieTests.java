package dubbelf.fr.ludotechtp;

import dubbelf.fr.ludotechtp.bo.Adresse;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.dal.AdresseRepositorie;
import dubbelf.fr.ludotechtp.dal.ClientRepositorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientAdresseRepositorieTests {

    private final ClientRepositorie clientRepositorie;
    private final AdresseRepositorie adresseRepositorie;

    @Autowired
    public ClientAdresseRepositorieTests(ClientRepositorie clientRepositorie, AdresseRepositorie adresseRepositorie) {
        this.clientRepositorie = clientRepositorie;
        this.adresseRepositorie = adresseRepositorie;
    }

    @Test
    @DisplayName("Test Création Adresse")
    public void testCreationAdresse() {
        long nombreAdresses = adresseRepositorie.count();
        Adresse adresse = new Adresse(null, "12 Rue des Jeux", "44000", "Nantes");
        adresseRepositorie.save(adresse);
        assertNotNull(adresse.getNoAdresse());
        assertEquals(nombreAdresses + 1, adresseRepositorie.count());
    }

    @Test
    @DisplayName("Test Création Client avec Adresse")
    public void testCreationClientAvecAdresse() {
        long nombreClients = clientRepositorie.count();
        Adresse adresse = new Adresse(null, "5 Avenue des Joueurs", "75000", "Paris");
        adresse = adresseRepositorie.save(adresse);
        Client client = new Client(null, "Durand", "Pierre", "pierre.durand@email.com", "0123456789", adresse);
        clientRepositorie.save(client);
        assertNotNull(client.getNoClient());
        assertEquals(nombreClients + 1, clientRepositorie.count());
    }
}
