package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Adresse;
import dubbelf.fr.ludotechtp.dal.AdresseRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdresseService {
    private final AdresseRepositorie adresseRepositorie;


    public Adresse ajouterAdresse(Adresse adresse) {
        return adresseRepositorie.save(adresse);
    }

    public void supprimerAdresse(Integer id) {
        adresseRepositorie.deleteById(id);
    }

    public Adresse modifierAdresse(Integer id, Adresse nouvelleAdresse) {
        return adresseRepositorie.findById(id).map(adresse -> {
            adresse.setRue(nouvelleAdresse.getRue());
            adresse.setCodePostal(nouvelleAdresse.getCodePostal());
            adresse.setVille(nouvelleAdresse.getVille());
            return adresseRepositorie.save(adresse);
        }).orElseThrow(() -> new IllegalArgumentException("Adresse introuvable"));
    }

    public List<Adresse> trouverToutesLesAdresses() {
        return adresseRepositorie.findAll();
    }

    public Optional<Adresse> trouverAdresseParId(Integer id) {
        return adresseRepositorie.findById(id);
    }
}
