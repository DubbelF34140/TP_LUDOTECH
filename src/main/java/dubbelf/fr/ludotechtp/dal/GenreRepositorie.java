package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepositorie extends JpaRepository<Genre, Integer> {
}
