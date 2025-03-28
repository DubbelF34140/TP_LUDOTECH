package dubbelf.fr.ludotechtp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class JeuDTO {
    private Integer noJeu;
    private String titre;
    private String reference;
    private double tarifJour;
    private int nombreExemplaires;
}
