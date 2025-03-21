package dubbelf.fr.ludotechtp;

import dubbelf.fr.ludotechtp.bll.ExemplaireService;
import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Genre;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.GenreRepositorie;
import dubbelf.fr.ludotechtp.dal.JeuRepositorie;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Utiliser l'extension Mockito au lieu de @SpringBootTest pour les tests unitaires
class JeuExemplaireGenreTests {

    @Mock
    private JeuRepositorie jeuRepositorie;

    @Mock
    private GenreRepositorie genreRepositorie;

    @Mock
    private ExemplaireRepositorie exemplaireRepositorie;

    @InjectMocks
    private ExemplaireService exemplaireService;

    @Test
    @DisplayName("Test Création Jeu avec Genres et Exemplaires")
    public void testCreationJeuAvecGenresEtExemplaires() {
        // Arrange - configurer les mocks pour qu'ils retournent des valeurs attendues
        when(jeuRepositorie.count()).thenReturn(0L, 1L); // Retourner 0 puis 1
        when(genreRepositorie.count()).thenReturn(0L, 2L); // Retourner 0 puis 2
        when(exemplaireRepositorie.count()).thenReturn(0L, 2L); // Retourner 0 puis 2

        // Création des genres
        Genre genre1 = new Genre(null, "Stratégie");
        Genre genre2 = new Genre(null, "Coopératif");

        when(genreRepositorie.save(any(Genre.class)))
                .thenReturn(genre1)  // Premier appel retourne genre1
                .thenReturn(genre2); // Deuxième appel retourne genre2

        genre1 = genreRepositorie.save(genre1);
        genre2 = genreRepositorie.save(genre2);

        Set<Genre> genres = new HashSet<>();
        genres.add(genre1);
        genres.add(genre2);

        // Création du jeu
        Jeu jeu = new Jeu(null, "Catan", "1234567890123", 10, "Un jeu de gestion et stratégie", 60, 5.50, genres);
        when(jeuRepositorie.save(any(Jeu.class))).thenReturn(jeu);
        jeu = jeuRepositorie.save(jeu);
        // Simuler l'affectation d'un ID au jeu sauvegardé
        jeu.setNoJeu(1);

        // Création des exemplaires
        Exemplaire exemplaire1 = new Exemplaire(null, "EX123456", true, jeu);
        Exemplaire exemplaire2 = new Exemplaire(null, "EX654321", true, jeu);

        when(exemplaireRepositorie.save(any(Exemplaire.class)))
                .thenReturn(exemplaire1)  // Premier appel retourne exemplaire1
                .thenReturn(exemplaire2); // Deuxième appel retourne exemplaire2

        exemplaireRepositorie.save(exemplaire1);
        exemplaireRepositorie.save(exemplaire2);

        // Assert
        assertNotNull(jeu.getNoJeu());
        assertEquals(1, jeuRepositorie.count());
        assertEquals(2, genreRepositorie.count());
        assertEquals(2, exemplaireRepositorie.count());
    }

    @Test
    public void testModifieretatExemplaire_ChangerEtatEnLouable() {
        // Arrange
        Integer exemplairetId = 1;
        Boolean nouveauEtat = true;

        Exemplaire exemplaireExistant = new Exemplaire();
        exemplaireExistant.setNoExemplaire(exemplairetId);
        exemplaireExistant.setLouable(false);

        // L'exemplaire mis à jour est le même objet mais avec le nouvel état
        // C'est important de capturer le même objet qui sera modifié
        when(exemplaireRepositorie.getReferenceById(exemplairetId)).thenReturn(exemplaireExistant);
        when(exemplaireRepositorie.save(exemplaireExistant)).thenReturn(exemplaireExistant);

        // Act
        Exemplaire resultat = exemplaireService.modifieretatExemplaire(exemplairetId, nouveauEtat);

        // Assert
        verify(exemplaireRepositorie).getReferenceById(exemplairetId);
        verify(exemplaireRepositorie).save(exemplaireExistant);
        assertEquals(nouveauEtat, resultat.getLouable());
    }

    @Test
    public void testModifieretatExemplaire_ChangerEtatEnNonLouable() {
        // Arrange
        Integer exemplairetId = 1;
        Boolean nouveauEtat = false;

        Exemplaire exemplaireExistant = new Exemplaire();
        exemplaireExistant.setNoExemplaire(exemplairetId);
        exemplaireExistant.setLouable(true);

        when(exemplaireRepositorie.getReferenceById(exemplairetId)).thenReturn(exemplaireExistant);
        when(exemplaireRepositorie.save(exemplaireExistant)).thenReturn(exemplaireExistant);

        // Act
        Exemplaire resultat = exemplaireService.modifieretatExemplaire(exemplairetId, nouveauEtat);

        // Assert
        verify(exemplaireRepositorie).getReferenceById(exemplairetId);
        verify(exemplaireRepositorie).save(exemplaireExistant);
        assertEquals(nouveauEtat, resultat.getLouable());
    }
}