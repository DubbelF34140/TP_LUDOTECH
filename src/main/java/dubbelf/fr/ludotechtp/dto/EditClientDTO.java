package dubbelf.fr.ludotechtp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditClientDTO {
    private String nom;
    private String prenom;
    private String email;
    private String noTelephone;
}
