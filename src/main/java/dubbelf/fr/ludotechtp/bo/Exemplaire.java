package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exemplaires")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exemplaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noExemplaire;

    @Column(nullable = false, unique = true, length = 50)
    @NonNull
    private String codebarre;

    @Column(nullable = false)
    private Boolean louable;

    @ManyToOne
    @JoinColumn(name = "no_jeu", referencedColumnName = "noJeu", nullable = false)
    private Jeu jeu;
}
