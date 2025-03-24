package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.*;
import dubbelf.fr.ludotechtp.dal.*;
import dubbelf.fr.ludotechtp.dto.CreateJeuDTO;
import dubbelf.fr.ludotechtp.dto.JeuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JeuService {
    private final JeuRepositorie jeuRepositorie;
    private final GenreRepositorie genreRepositorie;
    private final ExemplaireRepositorie exemplaireRepositorie;

    public List<JeuDTO> consulterJeux(String titre, Integer genreId) {
        List<Jeu> jeux;

        if (titre != null && genreId != null) {
            jeux = jeuRepositorie.findByTitreContainingAndGenres_NoGenre(titre, genreId);
        } else if (titre != null) {
            jeux = jeuRepositorie.findByTitreContaining(titre);
        } else if (genreId != null) {
            jeux = jeuRepositorie.findJeuByNoJeu(genreId);
        } else {
            jeux = jeuRepositorie.findAll();
        }

        return jeux.stream().map(jeu -> new JeuDTO(
                jeu.getNoJeu(),
                jeu.getTitre(),
                jeu.getReference(),
                jeu.getTarifJour(),
                exemplaireRepositorie.countByJeu(jeu)
        )).collect(Collectors.toList());
    }

    public Jeu ajouterJeu(CreateJeuDTO jeu) {
        // Récupérer les genres depuis la base de données par leurs libellés
        Set<Genre> genreList = new HashSet<>();
        for (String libelle : jeu.getGenres()) {
            Genre genre = genreRepositorie.findAllByLibelle(libelle);
            if (genre != null) {
                genreList.add(genre);
            }
        }

        // Créer une nouvelle entité Jeu à partir du DTO
        Jeu nouveauJeu = new Jeu();
        nouveauJeu.setTitre(jeu.getTitre());
        nouveauJeu.setDescription(jeu.getDescription());
        nouveauJeu.setDuree(jeu.getDuree());
        nouveauJeu.setReference(jeu.getReference());
        nouveauJeu.setAgeMin(jeu.getAgeMin());
        nouveauJeu.setTarifJour(jeu.getTarifJour());
        // Associer les genres au jeu
        nouveauJeu.setGenres(genreList);

        // Sauvegarder et retourner le jeu
        return jeuRepositorie.save(nouveauJeu);
    }
}
