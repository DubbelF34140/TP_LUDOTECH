package dubbelf.fr.ludotechtp.dal;
import dubbelf.fr.ludotechtp.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepositorie extends JpaRepository<Client, Integer>{
    List<Client> findByNomContainingIgnoreCase(String nom);
}
