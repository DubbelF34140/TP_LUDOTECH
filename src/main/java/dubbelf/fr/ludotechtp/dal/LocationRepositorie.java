package dubbelf.fr.ludotechtp.dal;
import dubbelf.fr.ludotechtp.bo.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepositorie extends JpaRepository<Location, Integer>{
    List<Location> findByExemplaire_CodebarreIn(List<String> codesBarres);
}
