package dubbelf.fr.ludotechtp.api;

import dubbelf.fr.ludotechtp.bll.GenreService;
import dubbelf.fr.ludotechtp.bo.Genre;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @PostMapping
    public ResponseEntity<Genre> ajouterAdresse(@RequestBody Genre genre) {
        Genre nouveaugenre = genreService.ajouterGenre(genre);
        return ResponseEntity.ok(nouveaugenre);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerAdresse(@PathVariable Integer id) {
        genreService.supprimerGenre(id);
        return ResponseEntity.noContent().build(); // HTTP 204 (No Content)
    }

    @PutMapping("/{id}")
    public ResponseEntity<Genre> modifierGenre(@PathVariable Integer id, @RequestBody Genre genre) {
        Genre adresseModifiee = genreService.modifierGenre(id, genre);
        return ResponseEntity.ok(adresseModifiee);
    }

    @GetMapping
    public ResponseEntity<List<Genre>> trouverToutesLesAdresses() {
        return ResponseEntity.ok(genreService.trouverToutesLesGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Genre> trouverAdresseParId(@PathVariable Integer id) {
        Optional<Genre> genre = genreService.trouverGenreParId(id);
        return genre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}