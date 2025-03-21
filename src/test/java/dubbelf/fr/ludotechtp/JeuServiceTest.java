package dubbelf.fr.ludotechtp;

import dubbelf.fr.ludotechtp.bll.JeuService;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.JeuRepositorie;
import dubbelf.fr.ludotechtp.dto.JeuDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class JeuServiceTest {

    @Mock
    private JeuRepositorie jeuRepositorie;

    @Mock
    private ExemplaireRepositorie exemplaireRepositorie;

    @InjectMocks
    private JeuService jeuService;

    private Jeu jeu1;
    private Jeu jeu2;

    @BeforeEach
    void setup() {
        jeu1 = new Jeu(1, "Catan", "1234567890123", 10, "Jeu de stratégie", 60, 2.5, null);
        jeu2 = new Jeu(2, "Pandemic", "9876543210987", 8, "Jeu coopératif", 45, 3.0, null);
    }

    @Test
    @DisplayName("Test récupération de tous les jeux avec nombre d'exemplaires")
    void testRecuperationTousLesJeux() {
        Mockito.when(jeuRepositorie.findAll()).thenReturn(Arrays.asList(jeu1, jeu2));
        Mockito.when(exemplaireRepositorie.countByJeu(jeu1)).thenReturn(3);
        Mockito.when(exemplaireRepositorie.countByJeu(jeu2)).thenReturn(5);

        List<JeuDTO> jeux = jeuService.consulterJeux(null, null);

        assertEquals(2, jeux.size());
        assertEquals(3, jeux.get(0).getNombreExemplaires());
        assertEquals(5, jeux.get(1).getNombreExemplaires());
    }

    @Test
    @DisplayName("Test récupération d'un jeu par titre avec nombre d'exemplaires")
    void testRecuperationParTitre() {
        Mockito.when(jeuRepositorie.findByTitreContaining("Catan")).thenReturn(List.of(jeu1));
        Mockito.when(exemplaireRepositorie.countByJeu(jeu1)).thenReturn(3);

        List<JeuDTO> jeux = jeuService.consulterJeux("Catan", null);

        assertEquals(1, jeux.size());
        assertEquals(3, jeux.get(0).getNombreExemplaires());
    }
}
