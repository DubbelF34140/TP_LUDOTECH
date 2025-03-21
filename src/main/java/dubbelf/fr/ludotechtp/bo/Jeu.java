package dubbelf.fr.ludotechtp.bo;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "jeux")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jeu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer noJeu;

    @Column(nullable = false, length = 100)
    @NonNull
    private String titre;

    @Column(nullable = false, length = 13, unique = true)
    @NonNull
    private String reference;

    private Integer ageMin;
    private String description;
    private Integer duree;

    @Column(nullable = false)
    private Double tarifJour;

    @ManyToMany
    @JoinTable(
            name = "jeu_genre",
            joinColumns = @JoinColumn(name = "no_jeu"),
            inverseJoinColumns = @JoinColumn(name = "no_genre")
    )
    private Set<Genre> genres;
}