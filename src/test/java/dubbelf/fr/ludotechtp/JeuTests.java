package dubbelf.fr.ludotechtp;

import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Genre;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.GenreRepositorie;
import dubbelf.fr.ludotechtp.dal.JeuRepositorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JeuExemplaireGenreTests {

    private final JeuRepositorie jeuRepositorie;
    private final GenreRepositorie genreRepositorie;
    private final ExemplaireRepositorie exemplaireRepositorie;

    @Autowired
    public JeuExemplaireGenreTests(JeuRepositorie jeuRepositorie, GenreRepositorie genreRepositorie, ExemplaireRepositorie exemplaireRepositorie) {
        this.jeuRepositorie = jeuRepositorie;
        this.genreRepositorie = genreRepositorie;
        this.exemplaireRepositorie = exemplaireRepositorie;
    }

    @Test
    @DisplayName("Test Création Jeu avec Genres et Exemplaires")
    public void testCreationJeuAvecGenresEtExemplaires() {
        long nombreJeux = jeuRepositorie.count();
        long nombreGenres = genreRepositorie.count();
        long nombreExemplaires = exemplaireRepositorie.count();

        // Création des genres
        Genre genre1 = new Genre(null, "Stratégie");
        Genre genre2 = new Genre(null, "Coopératif");
        genre1 = genreRepositorie.save(genre1);
        genre2 = genreRepositorie.save(genre2);

        Set<Genre> genres = new HashSet<>();
        genres.add(genre1);
        genres.add(genre2);

        // Création du jeu
        Jeu jeu = new Jeu(null, "Catan", "1234567890123", 10, "Un jeu de gestion et stratégie", 60, 5.50, genres);
        jeu = jeuRepositorie.save(jeu);

        // Création des exemplaires
        Exemplaire exemplaire1 = new Exemplaire(null, "EX123456", true, jeu);
        Exemplaire exemplaire2 = new Exemplaire(null, "EX654321", true, jeu);
        exemplaireRepositorie.save(exemplaire1);
        exemplaireRepositorie.save(exemplaire2);

        assertNotNull(jeu.getNoJeu());
        assertEquals(nombreJeux + 1, jeuRepositorie.count());
        assertEquals(nombreGenres + 2, genreRepositorie.count());
        assertEquals(nombreExemplaires + 2, exemplaireRepositorie.count());
    }
}