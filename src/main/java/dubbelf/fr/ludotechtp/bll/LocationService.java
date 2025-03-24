package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.*;
import dubbelf.fr.ludotechtp.dal.ClientRepositorie;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.FactureRepositorie;
import dubbelf.fr.ludotechtp.dal.LocationRepositorie;
import dubbelf.fr.ludotechtp.dto.RetourLocationResponseDTO;
import dubbelf.fr.ludotechtp.mapper.RetourLocationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {

    final private LocationRepositorie locationRepositorie;
    final private ExemplaireRepositorie exemplaireRepositorie;
    final private FactureRepositorie factureRepositorie;
    final private ClientRepositorie clientRepositorie;

    public Location creerLocation(Integer clientId, String codebarre, double tarifJour) {
        Client client = clientRepositorie.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client introuvable"));

        Exemplaire exemplaire = exemplaireRepositorie.findByCodebarre(codebarre)
                .orElseThrow(() -> new IllegalArgumentException("Exemplaire introuvable"));

        if (!exemplaire.getLouable()) {
            throw new IllegalStateException("Exemplaire déjà loué");
        }

        // Marquer l'exemplaire comme non louable
        exemplaire.setLouable(false);
        exemplaireRepositorie.save(exemplaire);

        // Créer la location
        Location location = new Location(null, LocalDate.now(), null, tarifJour, client, exemplaire);
        return locationRepositorie.save(location);
    }

    public RetourLocationResponseDTO retournerExemplaires(List<String> codesBarres) {
        List<Location> locations = locationRepositorie.findByExemplaire_CodebarreIn(codesBarres);

        if (locations.isEmpty()) {
            throw new IllegalArgumentException("Aucune location trouvée pour ces codes-barres");
        }

        // Mettre à jour les locations et rendre les exemplaires louables
        locations.forEach(location -> {
            location.setDateRetour(LocalDate.now());
            location.getExemplaire().setLouable(true);
            exemplaireRepositorie.save(location.getExemplaire());
            locationRepositorie.save(location);
        });

        // Générer la facture
        Facture facture = new Facture(null, LocalDate.now(), false, locations.get(0));
        factureRepositorie.save(facture);

        // Transformer les entités en DTO pour la réponse
        return RetourLocationMapper.toResponseDTO(facture, locations);
    }

}
