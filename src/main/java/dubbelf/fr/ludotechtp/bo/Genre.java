package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noGenre;

    @Column(nullable = false, length = 50, unique = true)
    @NonNull
    private String libelle;

    @ManyToMany(mappedBy = "genres")
    private Set<Jeu> jeux;
}
