package dubbelf.fr.ludotechtp.dal;
import dubbelf.fr.ludotechtp.bo.Facture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactureRepositorie extends JpaRepository<Facture, Integer>{
}
