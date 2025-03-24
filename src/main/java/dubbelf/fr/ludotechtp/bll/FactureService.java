package dubbelf.fr.ludotechtp.bll;

import dubbelf.fr.ludotechtp.bo.Facture;
import dubbelf.fr.ludotechtp.bo.Location;
import dubbelf.fr.ludotechtp.dal.FactureRepositorie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class FactureService {
    private final FactureRepositorie factureRepositorie;

    public Facture creerFacture(Location location) {
        Facture facture = new Facture(null, null,false, location);
        return factureRepositorie.save(facture);
    }

    public Facture marquerCommePayee(Facture facture) {
        facture.setDatePaiement(LocalDate.now());
        return factureRepositorie.save(facture);
    }
}
