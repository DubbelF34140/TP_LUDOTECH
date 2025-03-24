package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.ExemplaireService;
import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dto.ExemplaireDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exemplaires")
public class ExemplaireController {

    private final ExemplaireService exemplaireService;

    public ExemplaireController(ExemplaireService exemplaireService) {
        this.exemplaireService = exemplaireService;
    }

    @PostMapping
    public ResponseEntity<Exemplaire> ajouterExemplaire(@RequestBody ExemplaireDTO exemplaireDTO) {
        Exemplaire nouvelExemplaire = exemplaireService.ajouterExemplaire(exemplaireDTO);
        return ResponseEntity.ok(nouvelExemplaire);
    }
}
