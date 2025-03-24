package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JeuRepositorie extends JpaRepository<Jeu, Integer> {
    List<Jeu> findByTitreContainingAndGenres_NoGenre(String titre, Integer noGenre);

    List<Jeu> findByTitreContaining(String s);

    List<Jeu> findJeuByNoJeu(Integer aLong);

    Jeu findByTitre(String jeuTitre);
}
