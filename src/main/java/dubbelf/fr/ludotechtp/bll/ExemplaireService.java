package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Client;
import dubbelf.fr.ludotechtp.bo.Exemplaire;
import dubbelf.fr.ludotechtp.dal.ExemplaireRepositorie;
import dubbelf.fr.ludotechtp.dal.FactureRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExemplaireService {

    private final ExemplaireRepositorie exemplaireRepositorie;

    @Autowired
    public ExemplaireService(ExemplaireRepositorie exemplaireRepositorie) {
        this.exemplaireRepositorie = exemplaireRepositorie;
    }

    public Exemplaire saveExemplaire(Exemplaire exemplaire) {
        return exemplaireRepositorie.save(exemplaire);
    }

    public Exemplaire modifieretatExemplaire(Integer exemplaireID, Boolean louable) {
        Exemplaire exemplaire = exemplaireRepositorie.getReferenceById(exemplaireID);
        exemplaire.setLouable(louable);
        return exemplaireRepositorie.save(exemplaire);
    }






}
