package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Jeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JeuRepositorie extends JpaRepository<Jeu, Integer> {
}
