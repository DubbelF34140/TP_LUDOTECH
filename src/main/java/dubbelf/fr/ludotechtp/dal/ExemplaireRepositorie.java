package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Exemplaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExemplaireRepositorie extends JpaRepository<Exemplaire, Integer> {
}
