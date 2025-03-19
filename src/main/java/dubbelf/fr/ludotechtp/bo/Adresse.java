package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "adresse")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noAdresse;

    @Column(nullable = false, length = 50)
    @NonNull
    private String rue;

    @Column(nullable = false, length = 50)
    @NonNull
    private String codePostal;

    @Column(nullable = false, length = 50)
    @NonNull
    private String ville;
}

