package dubbelf.fr.ludotechtp.dal;
import dubbelf.fr.ludotechtp.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositorie extends JpaRepository<Client, Integer>{
}
