package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.LocationService;
import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Location;
import dubbelf.fr.ludotechtp.dto.RetourLocationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    //S025 - Créer une location depuis un scan de code-barres
    @PostMapping
    public ResponseEntity<Location> creerLocation(@RequestBody Map<String, Object> payload) {
        Integer clientId = ((Integer) payload.get("clientId"));
        String codebarre = (String) payload.get("codebarre");
        double tarifJour = ((Number) payload.get("tarifJour")).doubleValue();

        Location location = locationService.creerLocation(clientId, codebarre, tarifJour);
        return ResponseEntity.ok(location);
    }

    //S026 - Enregistrer le retour d'une ou plusieurs locations et générer une facture
    @PostMapping("/retour")
    public ResponseEntity<RetourLocationResponseDTO> retournerExemplaires(@RequestBody List<String> codesBarres) {
        RetourLocationResponseDTO retourResponse = locationService.retournerExemplaires(codesBarres);
        return ResponseEntity.ok(retourResponse);
    }
}
