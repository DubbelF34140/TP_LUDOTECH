package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.AdresseService;
import dubbelf.fr.ludotechtp.bo.Adresse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/adresses")
public class AdresseController {

    private final AdresseService adresseService;

    public AdresseController(AdresseService adresseService) {
        this.adresseService = adresseService;
    }

    @PostMapping
    public ResponseEntity<Adresse> ajouterAdresse(@RequestBody Adresse adresse) {
        Adresse nouvelleAdresse = adresseService.ajouterAdresse(adresse);
        return ResponseEntity.ok(nouvelleAdresse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAdresse(@PathVariable Integer id) {
        adresseService.supprimerAdresse(id);
        return ResponseEntity.noContent().build(); // HTTP 204 (No Content)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adresse> modifierAdresse(@PathVariable Integer id, @RequestBody Adresse adresse) {
        Adresse adresseModifiee = adresseService.modifierAdresse(id, adresse);
        return ResponseEntity.ok(adresseModifiee);
    }

    @GetMapping
    public ResponseEntity<List<Adresse>> trouverToutesLesAdresses() {
        return ResponseEntity.ok(adresseService.trouverToutesLesAdresses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adresse> trouverAdresseParId(@PathVariable Integer id) {
        Optional<Adresse> adresse = adresseService.trouverAdresseParId(id);
        return adresse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}