package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.*;
import dubbelf.fr.ludotechtp.dal.*;
import dubbelf.fr.ludotechtp.dto.JeuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JeuService {
    private final JeuRepositorie jeuRepositorie;
    private final ExemplaireRepositorie exemplaireRepositorie;

    @Autowired
    public JeuService(JeuRepositorie jeuRepositorie, ExemplaireRepositorie exemplaireRepositorie) {
        this.jeuRepositorie = jeuRepositorie;
        this.exemplaireRepositorie = exemplaireRepositorie;
    }

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
}
