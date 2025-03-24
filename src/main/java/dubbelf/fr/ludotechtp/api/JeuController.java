package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.JeuService;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dto.CreateJeuDTO;
import dubbelf.fr.ludotechtp.dto.JeuDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jeux")
public class JeuController {

    private final JeuService jeuService;

    public JeuController(JeuService jeuService) {
        this.jeuService = jeuService;
    }

    @PostMapping
    public ResponseEntity<Jeu> ajouterJeu(@RequestBody CreateJeuDTO jeu) {
        Jeu nouveauJeu = jeuService.ajouterJeu(jeu);
        return ResponseEntity.ok(nouveauJeu);
    }

    @GetMapping
    public ResponseEntity<List<JeuDTO>> trouverTousLesJeux(
            @RequestParam(required = false) String titre,
            @RequestParam(required = false) Integer genreId) {
        return ResponseEntity.ok(jeuService.consulterJeux(titre, genreId));
    }
}
