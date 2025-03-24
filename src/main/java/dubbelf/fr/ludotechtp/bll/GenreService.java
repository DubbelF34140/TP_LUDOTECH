package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Genre;
import dubbelf.fr.ludotechtp.dal.GenreRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {
    private final GenreRepositorie genreRepositorie;


    public Genre ajouterGenre(Genre Genre) {
        return genreRepositorie.save(Genre);
    }

    public void supprimerGenre(Integer id) {
        genreRepositorie.deleteById(id);
    }

    public Genre modifierGenre(Integer id, Genre nouveauGenre) {
        Genre genre = genreRepositorie.getById(id);
        genre.setLibelle(nouveauGenre.getLibelle());

        return genreRepositorie.save(genre);
    }

    public List<Genre> trouverToutesLesGenres() {
        return genreRepositorie.findAll();
    }

    public Optional<Genre> trouverGenreParId(Integer id) {
        return genreRepositorie.findById(id);
    }
}
