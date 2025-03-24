package dubbelf.fr.ludotechtp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExemplaireDTO {
    private String codebarre;
    private Boolean louable;
    private String jeuTitre; // On met le titre du jeu au lieu de l'objet Jeu
}
