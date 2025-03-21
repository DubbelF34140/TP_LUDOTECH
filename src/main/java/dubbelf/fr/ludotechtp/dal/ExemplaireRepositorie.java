package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepositorie extends JpaRepository<Exemplaire, Integer> {
    int countByJeu(Jeu jeu);
}
