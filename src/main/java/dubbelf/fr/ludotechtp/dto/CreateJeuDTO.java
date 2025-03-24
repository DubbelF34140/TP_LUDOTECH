package dubbelf.fr.ludotechtp.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
public class CreateJeuDTO {
    private Integer noJeu;
    private String titre;
    private String reference;
    private String description;
    private Integer ageMin;
    private Integer duree;
    private List<String> genres;
    private double tarifJour;
    private int nombreExemplaires;

}
