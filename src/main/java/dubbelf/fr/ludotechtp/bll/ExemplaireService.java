package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.bo.Jeu;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.FactureRepositorie;
import dubbelf.fr.ludotechtp.dal.JeuRepositorie;
import dubbelf.fr.ludotechtp.dto.ExemplaireDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExemplaireService {

    private final ExemplaireRepositorie exemplaireRepositorie;
    private final JeuRepositorie jeuRepositorie;

    public Exemplaire créerExemplaire(Exemplaire exemplaire) {
        return exemplaireRepositorie.save(exemplaire);
    }

    public Exemplaire modifieretatExemplaire(Integer exemplaireID, Boolean louable) {
        Exemplaire exemplaire = exemplaireRepositorie.getReferenceById(exemplaireID);
        exemplaire.setLouable(louable);
        return exemplaireRepositorie.save(exemplaire);
    }


    public Exemplaire ajouterExemplaire(ExemplaireDTO exemplaireDTO) {
        // Chercher le jeu par son titre
        Jeu jeu = jeuRepositorie.findByTitre(exemplaireDTO.getJeuTitre());

        // Créer l'exemplaire
        Exemplaire exemplaire = new Exemplaire();
        exemplaire.setCodebarre(exemplaireDTO.getCodebarre());
        exemplaire.setLouable(exemplaireDTO.getLouable());
        exemplaire.setJeu(jeu);

        return exemplaireRepositorie.save(exemplaire);
    }
}
