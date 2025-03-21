package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.*;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.FactureRepositorie;
import dubbelf.fr.ludotechtp.dal.LocationRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepositorie locationRepositorie;
    @Autowired
    private ExemplaireRepositorie exemplaireRepositorie;
    @Autowired
    private FactureRepositorie factureRepositorie;

    public Location creerLocation(Client client, Exemplaire exemplaire, double tarifJour) {
        if (!exemplaire.getLouable()) {
            throw new IllegalStateException("L'exemplaire n'est pas disponible.");
        }

        exemplaire.setLouable(false);
        exemplaireRepositorie.save(exemplaire);

        Location location = new Location(null, LocalDate.now(), null, tarifJour, client, exemplaire);
        locationRepositorie.save(location);

        return location;
    }

    public Location retournerExemplaire(Integer locationId) {
        Optional<Location> locationOpt = locationRepositorie.findById(locationId);
        if (locationOpt.isEmpty()) {
            throw new IllegalArgumentException("Location introuvable.");
        }

        Location location = locationOpt.get();
        location.setDateRetour(LocalDate.now());
        location.getExemplaire().setLouable(true);
        exemplaireRepositorie.save(location.getExemplaire());
        factureRepositorie.save(new Facture(null, LocalDate.now(), true, location));

        locationRepositorie.save(location);
        return location;
    }
}
