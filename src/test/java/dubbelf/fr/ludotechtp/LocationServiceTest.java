package dubbelf.fr.ludotechtp;

import dubbelf.fr.ludotechtp.bll.*;
import dubbelf.fr.ludotechtp.dal.*;
import dubbelf.fr.ludotechtp.bo.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class LocationServiceTest {

    @Mock
    private LocationRepositorie locationRepositorie;

    @Mock
    private ExemplaireRepositorie exemplaireRepositorie;

    @Mock
    private FactureRepositorie factureRepositorie;

    @InjectMocks
    private LocationService locationService;

    @Test
    @DisplayName("Test de création d'une location")
    void testCreerLocation() {
        Client client = new Client(1, "Martin", "Paul", "paul@email.com", "0123456789", null);
        Exemplaire exemplaire = new Exemplaire(1, "0001ABC", true, null);

        Mockito.when(locationRepositorie.save(any(Location.class)))
                .thenReturn(new Location(1, LocalDate.now(), null, 2.5, client, exemplaire));

        Mockito.when(exemplaireRepositorie.save(any(Exemplaire.class))).thenReturn(exemplaire);

        Location location = locationService.creerLocation(client, exemplaire, 2.5);

        assertNotNull(location);
        assertFalse(exemplaire.getLouable());
    }



    @Test
    @DisplayName("Test du retour d'un exemplaire")
    void testRetournerExemplaire() {
        Exemplaire exemplaire = new Exemplaire(1, "0001ABC", false, null);
        Location location = new Location(1, LocalDate.now(), null, 2.5, null, exemplaire);

        Mockito.when(locationRepositorie.findById(1)).thenReturn(Optional.of(location));
        Mockito.when(locationRepositorie.save(any(Location.class))).thenReturn(location);

        Location retour = locationService.retournerExemplaire(1);

        assertNotNull(retour);
        assertNotNull(retour.getDateRetour());
        assertTrue(exemplaire.getLouable());
    }
}
