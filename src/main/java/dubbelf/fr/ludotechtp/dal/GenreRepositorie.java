package dubbelf.fr.ludotechtp.dal;

import dubbelf.fr.ludotechtp.bo.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepositorie extends JpaRepository<Genre, Integer> {

    Genre findAllByLibelle(String nom);

}
